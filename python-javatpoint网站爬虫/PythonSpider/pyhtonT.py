#!/usr/bin/python
# -*- coding: UTF-8 -*-

import re
import cookielib
import urllib2
from bs4 import BeautifulSoup


html_doc = ''
#创建一个BeautifulSoup解析对象
soup = BeautifulSoup(html_doc,"html.parser",from_encoding="utf-8")
#获取所有的链接
links = soup.find_all('a')
print "所有的链接"
for link in links:
    print link.name,link['href'],link.get_text()

print "获取特定的URL地址"
link_node = soup.find('a',href="http://example.com/elsie")
print link_node.name,link_node['href'],link_node['class'],link_node.get_text()

print "正则表达式匹配"
link_node = soup.find('a',href=re.compile(r"ti"))
print link_node.name,link_node['href'],link_node['class'],link_node.get_text()
