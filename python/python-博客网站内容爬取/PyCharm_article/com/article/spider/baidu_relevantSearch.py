# -*- coding: utf-8 -*-

import socket
import threading
import jieba
import requests
from bs4 import BeautifulSoup
import urllib.request
import random
import time
import sql_operation
import ast
import sys
import codecs

sys.stdout = codecs.getwriter("utf-8")(sys.stdout.detach())


# 关键字分词
def participle(keyWord):
    s = keyWord.encode('utf-8')  # 举个栗子是字符串s，为了匹配下文的unicode形式，所以需要解码
    # p = re.compile(ur'[\u4e00-\u9fa5]')  # 这里是精髓，[\u4e00-\u9fa5]是匹配所有中文的正则，因为是unicode形式，所以也要转为ur
    # print(p.split(s))
    # 使用re库的split切割
    jieba.add_word('html')
    seg_list = jieba.cut(s, cut_all=True)
    keyList = []
    for list in seg_list:
        if len(list) >= 2:
            keyList.append(list)
    # print(keyList)
    return keyList


def getproxyip(ip_file):
    fo = open(ip_file, 'r', encoding='utf-8')
    proxys = fo.read().split('\n')
    proxy = ast.literal_eval(random.choice(proxys))
    # print(proxy)
    fo.close()
    return proxy


def page_html(keyword):
    socket.setdefaulttimeout(10)  # 设置全局超时时间
    url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd=" + keyword + "&rsv_pq=d0e6cce80006a4d2&rsv_t=f95aLCNgxPsct8UBd2K9OjQpiKh9eIOEdto4esxyIT3EoDGNzBIiwNx3Y4s&rqlang=cn&rsv_enter=0"
    user_agents = [
        'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11',
        'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)',
        'Mozilla/5.0 (compatible; Konqueror/3.5; Linux) KHTML/3.5.5 (like Gecko) (Kubuntu)',
        'Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.0.12) Gecko/20070731 Ubuntu/dapper-security Firefox/1.5.0.12',
        "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.7 (KHTML, like Gecko) Ubuntu/11.04 Chromium/16.0.912.77 Chrome/16.0.912.77 Safari/535.7",
        "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0 "]
    user_agent = random.choice(user_agents)
    headers = {
        'User-Agent': user_agent,
        'Accept-Encoding': 'gzip'}
    try:
        # proxy_support = urllib.request.ProxyHandler(getproxyip('proxy_baidu.txt'))
        # opener = urllib.request.build_opener(proxy_support)
        # opener.addheaders = [headers]
        # urllib.request.install_opener(opener)
        # html_doc = urllib.request.urlopen(url).read()
        req = requests.get(url=url, headers=headers)
        html_doc = req.text
        soup = BeautifulSoup(html_doc, "html.parser")
        links = soup.select('th > a')
        print("网页解析完毕")
        relevant_search = []
        for a in links:
            relevant_search.append(a.get_text())
        print(relevant_search)
        if len(relevant_search) < 1:
            page_html(keyword)
        else:
            tosql(keyword, relevant_search)
    except Exception as e:
        print(e)
        print("重新进行尝试连接")
        page_html(keyword)


def tosql(keyword, relevant_search):
    db = sql_operation.getcon()
    key_list = participle(keyword)
    key_str = "---".join(key_list)
    relevant_str = "-----".join(relevant_search)
    replace_sql = "REPLACE INTO `t_relevant_search` (`keyword`,  `key_list`, `relevant_search`, `flag`) " \
                  "VALUES ('%s','%s', '%s', '0')" \
                  % (keyword, key_str, relevant_str)
    print(replace_sql)
    sql_operation.baseoperation(db, replace_sql)
    sql_operation.closecon(db)


def sql_search():
    db = sql_operation.getcon()
    select_sql = "select keyword,relevant_search from t_relevant_search where flag = '0' Limit 50"
    update_sql = "update t_relevant_search set flag = '1' where flag = '0'  Limit 50"
    results = sql_operation.baseselect(db, select_sql)
    sql_operation.baseoperation(db, update_sql)
    search_list = []
    threads = []
    for row in results:
        search_list = row[1].split("-----")
        for keyword in search_list:
            thread = threading.Thread(target=page_html, args=[keyword])
            threads.append(thread)
            thread.start()
            # 阻塞主进程，等待所有子线程结束
        for thread in threads:
            thread.join()
    sql_operation.closecon(db)


def timer(n):
    '''''
    每n秒执行一次
    '''
    i = 0
    while True:
        print(time.strftime('%Y-%m-%d %X', time.localtime()))
        print('\033[1;31m' +
              "---------------------------------------------------------------------------------------------" + str(i)
              + "---------------------------------------------------------------------" + '\033[0m')
        print('\n\n\n\n\n\n\n\n\n')
        i += 1
        sql_search()
        time.sleep(n)


# 多线程验证
def thread():
    threads = []
    for i in range(0, 5):
        thread = threading.Thread(target=timer)
        threads.append(thread)
        thread.start()
    # 阻塞主进程，等待所有子线程结束
    for thread in threads:
        thread.join()


# 数据的ETL
def sqlETL():
    db = sql_operation.getcon()
    select_sql = "SELECT id,key_list from t_relevant_search"
    select_key_sql = "SELECT `key` from t_relevant_search_key"
    results = sql_operation.baseselect(db, select_sql)
    for row in results:
        key_list = row[1].split('---')
        key_results = sql_operation.baseselect(db, select_key_sql)
        key_results_list = []
        for temp in key_results:
            key_results_list.append(temp[0])
        for key in key_list:
            if key in key_results_list:
                update_sql = "update t_relevant_search_key " \
                             "set keyword_id_list = CONCAT(keyword_id_list,'@','%s') where `key` = '%s'" \
                             % (row[0], key)
                print('-----------------')
                sql_operation.baseoperation(db, update_sql)
            else:
                insert_sql = "insert into t_relevant_search_key (`key`,keyword_id_list) value ('%s','%s')" \
                             % (key, row[0])
                sql_operation.baseoperation(db, insert_sql)
    sql_operation.closecon(db)


# 关键字提取
def key_extract():
    db = sql_operation.getcon()
    key_file = open("keyword_relevant.txt", "a", encoding='utf-8')
    select_sql = "SELECT keyword,key_list FROM t_relevant_search WHERE keyword LIKE '%么%' and keyword LIKE '%php%'"
    results = sql_operation.baseselect(db, select_sql)
    for row in results:
        # row_str = ""
        keyword = row[0]
        # key_list = row[1]
        key_file.write(keyword + '\n')
        print(keyword)
    key_file.close()
    sql_operation.closecon(db)


if __name__ == '__main__':
    key_list = ['php','python','html','sql','mysql','数组','float','函数']
    # php python java c go sql mysql html js javascript web vue ps photoshop spring mvc linux windows win mac
    # ip http https url xml rss c# c++  css net 前端 后端 后台 微信 小程序 运维 Git git thread
    # Tomcat nginx apache thinkPHP github 框架 网站
    #

    # page_html('多线程')
    # timer(10)
    # sql_search()
    key_extract()
