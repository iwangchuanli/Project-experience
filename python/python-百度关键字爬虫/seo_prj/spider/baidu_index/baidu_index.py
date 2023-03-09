# -*- coding: utf-8 -*-
import json
import threading
import time
import sys
import requests
import mysql_operation
import myutils, random
from get_index import BaiduIndex

sys.stdout = myutils.Logger('console_index.log', sys.stdout)  # 控制台输出日志
sys.stderr = myutils.Logger('console_index_error.log', sys.stderr)  # 错误输出日志


# get
def get(keyword):
    start_date = "2019-01-01"
    end_date = "2019-05-01"
    url = "http://index.baidu.com/api/SearchApi/index?word=" + keyword \
          + "&area=0&startDate=" + start_date + "&endDate=" + end_date
    headers = {
        "Accept": "application / json, text / plain, * / *",
        "Accept-Encoding": "gzip, deflate",
        "Accept-Language": "zh-CN,zh;q=0.9",
        "Connection": "keep-alive",
        "Cookie": "BAIDUID=47B9867DEBA6B652903C4975C010AB3F:FG=1; BIDUPSID=47B9867DEBA6B652903C4975C010AB3F; PSTM=1554868710; BDUSS=IzekZDay1FUHJJclIxNTVVQVpuZE5-NUtBLXVWdzREdk9SRHkzc2MwSWtWLTVjSVFBQUFBJCQAAAAAAAAAAAEAAACWA4Z6gVfs4buou~DYvAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACTKxlwkysZcRl; bdshare_firstime=1556603263799; SIGNIN_UC=70a2711cf1d3d9b1a82d2f87d633bd8a03073772022; bdindexid=pbgt1q0dv1tqteqphcc3r10s96; Hm_lvt_d101ea4d2a5c67dab98251f0b5de24dc=1556593941,1556603388,1556615384,1557365405; H_WISE_SIDS=130593_125703_128701_131824_125696_130163_120129_131381_128879_131602_131906_118882_118864_131401_118838_118819_118793_130763_131649_131577_131535_131534_131530_130222_131295_131871_131390_129565_107317_131796_131392_130120_130569_131195_130350_117431_131240_129649_127027_131436_131687_131036_129838_130990_129479_129644_124802_131467_130825_110085_131767_127969_131506_123289_131749_131298_127317_128200_131552_130595_131264_131262_128604_131458_128806; BDORZ=AE84CDB3A529C0F8A2B9DCDD1D18B695; delPer=0; PSINO=5; H_PS_PSSID=1466_21117_28721_28964_28832_28585_26350_28604; ZD_ENTRY=empty; Hm_lpvt_d101ea4d2a5c67dab98251f0b5de24dc=1557367184",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36",
        "X-Requested-With": "XMLHttpRequest"
    }
    info = []
    try:
        req = requests.get(url=url, headers=headers, timeout=5)
        html_doc = req.text
        print(keyword, html_doc)
        py_json = json.loads(html_doc)
        status = py_json['status']
        if status == 0:
            word = py_json['data']['generalRatio'][0]['word']
            all = py_json['data']['generalRatio'][0]['all']
            pc = py_json['data']['generalRatio'][0]['pc']
            wise = py_json['data']['generalRatio'][0]['wise']
            info = [word, start_date, end_date, status, all['avg'], all['yoy'], all['qoq'], pc['avg'],
                    pc['yoy'],
                    pc['qoq'], wise['avg'], wise['yoy'], wise['qoq'], py_json['message'],
                    py_json['data']['uniqid']]
        else:
            info = []
            info = [keyword, start_date, end_date, status, py_json['message']]
        save_info(keyword, info)
    except Exception as e:
        print(e)
        save_info(keyword, info)  # 异常处理


# 数据保存
def save_info(keyword, info):
    db = mysql_operation.getcon()
    if len(info) > 0 and info[3] == 0:
        sql_opt = "INSERT INTO `word_exec` (`tail_word`, `start_date`, `end_date`, " \
                  "`status`, `all_avg`, `all_yoy`, `all_qoq`, `pc_avg`, `pc_yoy`, `pc_qoq`, " \
                  "`wise_avg`, `wise_yoy`, `wise_qoq`, " \
                  "`message`, `uniqid`) " \
                  "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s'," \
                  " '%s', '%s')" \
                  % (
                      info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], info[8], info[9],
                      info[10],
                      info[11], info[12], info[13], info[14])
    elif len(info) > 0:
        sql_opt = "INSERT INTO `word_exec` (`tail_word`, `start_date`, `end_date`, `status`,`message`)" \
                  "VALUES ('%s', '%s', '%s', '%s', '%s')" \
                  % (info[0], info[1], info[2], info[3], info[4])
    else:
        sql_opt = "update `t_tail_word` set extr_flag = '0' where tail_word = '%s'" % (keyword)
    mysql_operation.baseoperation(db, sql_opt)
    mysql_operation.closecon(db)


# 取单词
def get_word():
    db = mysql_operation.getcon()
    select_sql = "select `tail_word` from `tail_word_20190529` where extr_flag = '0' limit 50"
    update_sql = "update `tail_word_20190529` set extr_flag = '1' where extr_flag = '0' limit 50"
    results = mysql_operation.baseselect(db, select_sql)
    mysql_operation.baseoperation(db, update_sql)
    mysql_operation.closecon(db)
    threads = []
    if len(results) > 0:
        for row in results:
            # print(row[0])
            # get(row[0])
            thread = threading.Thread(target=get, args=[row[0]])
            threads.append(thread)
            thread.start()
        for thread in threads:
            thread.join()
    else:
        pass


def timer(n):
    i = 0
    while i < 200:
        start_time = time.time()
        print(time.strftime('%Y-%m-%d %X', time.localtime()))
        print('\033[1;31m' +
              "-------------------------------------------------------------------------------------------" + str(i)
              + "---------------------------------------------------------------------" + '\033[0m')
        i += 1
        get_word()
        # get_index()
        print('end one times operation')
        print(time.time() - start_time)
        time.sleep(n)


# 模拟登陆方法 非API
def get_index():
    start_time = time.time()
    db = mysql_operation.getcon()
    select_sql = "select `tail_word` from `t_tail_word` where extr_flag = '0' and tail_word like '%php%' limit 5"
    update_sql = "update `t_tail_word` set extr_flag = '1' where extr_flag = '0' and tail_word like '%php%' limit 5"
    results = mysql_operation.baseselect(db, select_sql)
    mysql_operation.baseoperation(db, update_sql)
    search_list = []
    if len(results) > 0:  # 查询结果
        for row in results:
            search_list.append(row[0])
        baidu_index = BaiduIndex(search_list, '2018-01-01', '2019-04-01')
        for item in search_list:
            print(item, baidu_index.result[item]['all'])
            if len(baidu_index.result[item]['all']) > 0:  # 搜索出来的index数量
                all_avg = index_avg(baidu_index.result[item], 'all')
                pc_avg = index_avg(baidu_index.result[item], 'pc')
                wise_avg = index_avg(baidu_index.result[item], 'wise')
                print(all_avg, pc_avg, wise_avg)
                insert_sql = "INSERT INTO `baidu_index` " \
                             "(`word`, `start_time`, `end_time`, `status`, `all_avg`, `pc_avg`, `wise_avg`) " \
                             "VALUES ('%s', '%s', '%s', '0', '%s','%s','%s')" \
                             % (item, '2018-01-01', '2019-04-01', all_avg, pc_avg, wise_avg)
            else:  # 未收录
                insert_sql = "insert into `baidu_index` (`word`, `start_time`, `end_time`, `status`)" \
                             "values ('%s', '%s', '%s', '1')" % (item, '2018-01-01', '2019-04-01')
            mysql_operation.baseoperation(db, insert_sql)
    else:
        pass
        # 数据库查询结果没有
    mysql_operation.closecon(db)
    print("times  :  ", time.time() - start_time)
    pass


# 计算平均值
def index_avg(dic, item):
    i = 0
    sum_index = 0
    while i < 30:
        i += 1
        sum_index += int(random.choices(dic[item])[0]['index'])
    avg = sum_index / 30
    return round(avg, 2)


if __name__ == '__main__':
    timer(2)
    # get("java开发工具")
    # get("python非法")
    # get_index()
