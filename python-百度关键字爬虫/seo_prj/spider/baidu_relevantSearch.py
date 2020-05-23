# -*- coding: utf-8 -*-

import socket
import threading
import time
import mysql_operation
import sys
import codecs
import myutils
import base_spider

sys.stdout = codecs.getwriter("utf-8")(sys.stdout.detach())
sys.stdout = myutils.Logger('console_relevant.log', sys.stdout)  # 控制台输出日志
sys.stderr = myutils.Logger('console_relevant_error.log', sys.stderr)  # 错误输出日志


def page_parse(keyword):
    socket.setdefaulttimeout(10)  # 设置全局超时时间
    url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd=" + keyword \
          + "&rsv_pq=d0e6cce80006a4d2&rsv_t" \
            "=f95aLCNgxPsct8UBd2K9OjQpiKh9eIOEdto4esxyIT3EoDGNzBIiwNx3Y4s&rqlang=cn&rsv_enter=0"
    try:
        soup = base_spider.normal_spider(url)
        if soup != False:
            links = soup.select('th > a')
            relevant_search = []
            for a in links:
                relevant_search.append(a.get_text())
            full_list = [keyword, relevant_search]
            # print(keyword, "---完毕")
            save_tail(full_list)
        else:
            error_word(keyword)
    except Exception as e:
        print(keyword, '--------------------------处理 失 败')
        print(e)
        error_word(keyword)
        pass


# 如果关键字处理失败数据库更新
def error_word(tail_word):
    db = mysql_operation.getcon()
    update_sql = "update t_tail_word_20190529 set flag = '0' where `tail_word` = '%s'" % (tail_word)
    mysql_operation.baseoperation(db, update_sql)
    mysql_operation.closecon(db)


# 通过分词去除无关的长尾词
def rm_word(tail_word):
    fo = open('wipe_word.txt', 'r', encoding='utf-8')
    pre_list = fo.read().split('\n')
    fo.close()
    for item in pre_list:
        if len(item) > 0 and tail_word.find(item) != -1:
            return False
    return True


# 单个关键词网页信息保存到mysql
def save_tail(full_list):
    db = mysql_operation.getcon()
    relevant_word = full_list[1]
    if 0 == len(relevant_word):
        update_sql = "update t_tail_word_20190529 set flag = '0' where `tail_word` = '%s'" % (full_list[0])
        mysql_operation.baseoperation(db, update_sql)
    else:
        tail_word = full_list[0]
        if rm_word(tail_word):
            word_split = "~~".join(myutils.participle(tail_word))
            relevant_word = "~~".join(relevant_word).replace('\'', '').replace('\"', '')
            replace_sql = "INSERT INTO `t_tail_word_20190529` (`tail_word`,  `word_split`, `relevant_word`) " \
                          "VALUES ('%s','%s', '%s')" \
                          % (tail_word, word_split, relevant_word)
            mysql_operation.baseoperation(db, replace_sql)
            print(tail_word, "---完毕")
        else:
            print("无关记录-----------------------------", tail_word)
    mysql_operation.closecon(db)


# 重复的keyword记录不处理
def no_repeat():
    db = mysql_operation.getcon()
    select_sql = "select `tail_word` from `t_tail_word_20190529`"
    results = mysql_operation.baseselect(db, select_sql)
    global tailword_list
    tailword_list = []
    for row in results:
        tailword_list.append(row[0])
    mysql_operation.closecon(db)


# 从MySQL中读取信息并启动多线程爬取
def read_tail():
    start_time = time.time()
    no_repeat()
    db = mysql_operation.getcon()
    # delete_sql = "DELETE FROM t_tail_word WHERE id in ( " \
    #              "SELECT id FROM(" \
    #              "select * from t_tail_word where flag='0' GROUP BY tail_word HAVING COUNT(tail_word) > 1" \
    #              ") alias);"
    # mysql_operation.baseoperation(db, delete_sql)  # 每次取用前先删除一次数据库中没读取过的重复数据
    select_sql = "select `tail_word`, `relevant_word` from `t_tail_word_20190529` where flag = '0' Limit 50"
    update_sql = "update `t_tail_word_20190529` set flag = '1' where flag = '0'  Limit 50"
    results = mysql_operation.baseselect(db, select_sql)
    mysql_operation.baseoperation(db, update_sql)
    full_threads = []
    for row in results:
        search_list = row[1].split("~~")
        thread = threading.Thread(target=sigl_thread, args=[search_list])
        full_threads.append(thread)
        thread.start()
    for thread in full_threads:
        thread.join()
    mysql_operation.closecon(db)
    print("本次用时 : ", time.time() - start_time)


# 单条记录的多线程 1-》9
def sigl_thread(search_list):
    threads = []
    for keyword in search_list:
        if keyword in tailword_list:
            continue
        else:
            thread = threading.Thread(target=page_parse, args=[keyword])
            threads.append(thread)
            thread.start()
    for thread in threads:
        thread.join()


# timer计时重复读取数据库的信息
def timer(n):
    i = 0
    while True:
        print(time.strftime('%Y-%m-%d %X', time.localtime()))
        print('\033[1;31m' +
              "-------------------------------------------------------------------------------------------" + str(i)
              + "---------------------------------------------------------------------" + '\033[0m')
        i += 1
        read_tail()
        print('end one times operation')
        time.sleep(n)


if __name__ == '__main__':
    timer(10)
    # page_parse("CodeIgniter")
