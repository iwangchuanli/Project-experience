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


def page_html(keyword):
    print('\n当前关键字 : ' + keyword)
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
        print("网页解析完毕")
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
                    print(keyword+"        "+temp, '           ++++++++++++++++++++++++++++')
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


if __name__ == '__main__':  # php是什么
    # page_html('php是什么')
    readfile()
