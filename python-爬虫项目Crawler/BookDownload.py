#!/usr/bin/python
# -*- coding: UTF-8 -*-
import re
import urllib.request
from bs4 import BeautifulSoup
#编程书籍
url = "http://mebook.cc/category/gjs/bckf/"
#获得各个书本的链接
def getbook(url):
    html_doc = urllib.request.urlopen(url).read()
    soup = BeautifulSoup(html_doc,"html.parser",from_encoding="GB18030")
    links = soup.select('#primary .img a')
    for link in links:
        str = link['href'] + link['title'] + '\n'
        print (str)
        bookfile(str)
#将各个书本的链接追加保存到txt文件（待处理）
def bookfile(str):
    fo = open("file.txt","a")
    fo.write(str)
    fo.close()
#获取所有书本链接
def test():
    getbook(url)
    for x in range(2,18):
        url = "http://mebook.cc/category/gjs/bckf/page/" + str(x)
        try:
            getbook(url)
            bookfile("第"+str(x)+"页\n")
        except UnicodeEncodeError:
            pass
        continue
# 获取各个书本的下载链接
def getDownload(id):
    url = "http://mebook.cc/download.php?id="+id
    html_doc = urllib.request.urlopen(url).read()
    soup = BeautifulSoup(html_doc,"html.parser",from_encoding="GB18030")
    links = soup.select('.list a')
    for link in links:
        print (link)
    pwds = soup.select('.desc p')
    for pwd in pwds:
        print (pwd.encode(encoding='utf-8' ,errors = 'strict'))

#test
getDownload(str(25723))