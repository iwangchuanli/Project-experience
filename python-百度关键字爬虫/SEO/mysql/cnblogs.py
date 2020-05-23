# -*- coding: utf-8 -*-

import requests
from bs4 import BeautifulSoup
import urllib.request
import os


# cnblogs post请求
def getDoc(index):
    print(
        '--------------------------------------------------当前爬取cnblogs第' + index + '页-----------------------------------------------')
    url = 'https://www.cnblogs.com/mvc/AggSite/PostList.aspx'
    headers = {
        'User-Agent': 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)'
    }
    values = {
        'CategoryId': '108696',
        'CategoryType': "SiteCategory",
        'ItemListActionName': "PostList",
        'PageIndex': index,
        'ParentCategoryId': '2',
        'TotalPostCount': '4000'
    }
    data = urllib.parse.urlencode(values).encode('utf-8')
    request = urllib.request.Request(url, data, headers)
    html = urllib.request.urlopen(request).read().decode('utf-8')
    return HTMLparse(html)


# 单网页爬取处理
def HTMLparse(html_doc):
    # 创建一个BeautifulSoup解析对象
    soup = BeautifulSoup(html_doc, "html.parser")
    # 获取 标题 简介 地址
    orig_titles = soup.select('h3 > a')
    orig_intros = soup.select('p.post_item_summary')
    # authors = soup.select('div.post_item_foot > a')
    orig_others = soup.select('div.post_item_foot')
    urls = []
    titles = []
    intros = []
    others = []
    for title in orig_titles:
        titles.append(title.get_text().replace(' ', ''))
        urls.append(title.get('href'))
    for intro in orig_intros:
        intros.append(intro.get_text().replace(' ', '').replace('\n', '').replace('\r', ''))
    for other in orig_others:
        others.append(other.get_text().replace(' ', '').replace('\n', '').replace('\r', ''))
    infoLists = [titles, intros, urls, others]
    return infoLists


# 多网页内容爬取
def crawler(startIndex, endIndex):
    for i in range(startIndex, endIndex):
        getDoc(str(i))


if __name__ == '__main__':
    crawler(2, 3)

'''
MySQL
values = {
        'CategoryId': '108715',
        'CategoryType': "SiteCategory",
        'ItemListActionName': "PostList",
        'PageIndex': index,
        'ParentCategoryId': '108712',
        'TotalPostCount': '1872'
    }

PHP
values = {
        'CategoryId': '106882',
        'CategoryType': "SiteCategory",
        'ItemListActionName': "PostList",
        'PageIndex': index,
        'ParentCategoryId': '2',
        'TotalPostCount': '1184'
    }
    

'''
