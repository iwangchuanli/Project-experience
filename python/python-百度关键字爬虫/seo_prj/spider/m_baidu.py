# -*- coding: utf-8 -*-

import socket
import threading
import jieba
import requests
from bs4 import BeautifulSoup
import urllib.request
import random
import time
import mysql_operation
import ast
import sys
import codecs


def page_html(keyword):
    # print('\n当前关键字 : ' + keyword)
    socket.setdefaulttimeout(10)  # 设置全局超时时间 学会php可以干什么 php是什么
    url = "http://m.baidu.com/s?word=" + keyword
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
        req = requests.get(url=url, headers=headers)
        html_doc = req.text
        soup = BeautifulSoup(html_doc, "html.parser")
        titles = soup.select('article > header > div > a')
        links = soup.select('span.c-color-gray')
        # links2 = soup.select('div.c-showurl c-line-clamp1')
        # print("网页解析完毕")
        page_list = []
        php_list = ['php中文网', 'PHP中文网', 'm.php.cn']
        for temp in titles:
            # print(temp.get('aria-roledescription'), temp.get_text())
            page_list.append(temp.get_text())
        for temp in links:
            page_list.append(temp.get_text())
        # print(page_list)
        for flag in php_list:
            for temp in page_list:
                if temp.find(flag) != -1:
                    print(keyword + "        " + temp, '           ++++++++++++++++++++++++++++')
                    return False
        return True
    except Exception as e:
        print(e)
        print("重新进行尝试连接")
        page_html(keyword)


def readfile():
    fileio = open('keyword_relevant.txt', 'r+', encoding='utf-8')
    keywords = fileio.read().split('\n')
    php_list = []
    fileio.seek(0, 0)
    for keyword in keywords:
        if page_html(keyword):
            fileio.write(keyword + '\n')
        else:
            php_list.append(keyword)
    if len(php_list) > 0:
        for temp in php_list:
            fileio.write(temp + '            php中文网已有\n')
    fileio.close()


def readsql():
    start_time = time.time()
    db = mysql_operation.getcon()
    select_sql = "select `tail_word` from `tail_word_20190529` where extr_flag = '0' limit 20"
    update_sql = "update `tail_word_20190529` set extr_flag = '1' where extr_flag = '0' limit 20"
    results = mysql_operation.baseselect(db, select_sql)
    mysql_operation.baseoperation(db, update_sql)
    file = open('keyword_relevant.txt', 'a', encoding='utf-8')
    search_list = []
    php_list = []
    if len(results) > 0:  # 查询结果
        for row in results:
            search_list.append(row[0])
            if page_html(row[0]):
                file.write(row[0] + '\n')
            else:
                php_list.append(row[0])
        # if len(php_list) > 0:
        #     for temp in php_list:
        #         file.write(temp + '            php中文网已有\n')
    file.close()
    mysql_operation.closecon(db)


# 修改word_exec表格
def check_php():
    db = mysql_operation.getcon()
    select_sql = "select `tail_word` from `word_exec` where flag = '0' limit 100"
    update_sql = "update `word_exec` set flag = '1' where flag = '0'  limit 100"
    results = mysql_operation.baseselect(db, select_sql)
    mysql_operation.baseoperation(db, update_sql)
    full_threads = []
    for row in results:
        thread = threading.Thread(target=thread_handler, args=[row[0]])
        full_threads.append(thread)
        thread.start()
    for thread in full_threads:
        thread.join()
    mysql_operation.closecon(db)


# word_exec 数据爬取处理
def thread_handler(keyword):
    db = mysql_operation.getcon()
    print(keyword)
    if page_html(keyword):
        pass
    else:
        update_sql = "update `word_exec` set flag = 'php中文网' where `tail_word`='%s'" % (keyword)
        mysql_operation.baseoperation(db, update_sql)
        print(update_sql)
    mysql_operation.closecon(db)


def wipe_handler():
    print("去除无关项")
    file = open('wipe_word.txt', 'r', encoding='utf-8')
    wipe_list = file.read().split('\n')
    file.close()
    db = mysql_operation.getcon()
    for word in wipe_list:
        if len(word) > 0:
            word = "%" + word + "%"
            delete_sql = "DELETE FROM word_exec WHERE id in ( SELECT id FROM( " \
                         "select id,tail_word from word_exec where tail_word LIKE '%s') a)" % (word)
            mysql_operation.baseoperation(db, delete_sql)
    mysql_operation.closecon(db)


def timer(n):
    i = 0
    while i < 200:
        print(time.strftime('%Y-%m-%d %X', time.localtime()))
        start_time = time.time()
        print('\033[1;31m' +
              "-------------------------------------------------------------------------------------------" + str(i)
              + "---------------------------------------------------------------------" + '\033[0m')
        i += 1
        # readsql()
        check_php()
        print('end one times operation')
        print(time.time() - start_time)
        time.sleep(n)


if __name__ == '__main__':  # php是什么 常用算法 m.php
    # page_html('php是什么')
    # page_html('m.php')

    # readfile()
    # readsql()
    timer(3)
    # wipe_handler()
