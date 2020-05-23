# -*- coding: utf-8 -*-
'''
功能：提供一个链接返回一个beautifulsoup对象

反爬虫：header随机浏览器、proxy随机
'''
import socket
import requests
from bs4 import BeautifulSoup
import urllib.request
import myutils


def normal_spider(url):
    socket.setdefaulttimeout(10)  # 设置全局超时时间
    i = 0
    while i < 5:
        try:
            req = requests.get(url=url, headers=myutils.randomheader(), timeout=5)
            html_doc = req.text
            soup = BeautifulSoup(html_doc, "html.parser")
            return soup
        except Exception as e:
            print(e)
            print("重新进行尝试连接")
            i += 1
    return False


def proxy_spider(url, proxy_file):
    try:
        proxy_support = urllib.request.ProxyHandler(myutils.getproxyip(proxy_file))
        opener = urllib.request.build_opener(proxy_support)
        opener.addheaders = [myutils.randomheader()]
        urllib.request.install_opener(opener)
        html_doc = urllib.request.urlopen(url).read()
        soup = BeautifulSoup(html_doc, "html.parser")
        print("网页解析完毕")
        return soup
    except Exception as e:
        print(e)
        print("重新进行尝试连接")
        proxy_spider(url)
