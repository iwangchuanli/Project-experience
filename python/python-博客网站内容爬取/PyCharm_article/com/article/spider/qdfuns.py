# -*- coding: utf-8 -*-

import requests
from bs4 import BeautifulSoup
import urllib.request
import os
import random
import time
import sql_operation
import baidudetector


def html(url):
    user_agents = [
        'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11',
        'Opera/9.25 (Windows NT 5.1; U; en)',
        'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)',
        'Mozilla/5.0 (compatible; Konqueror/3.5; Linux) KHTML/3.5.5 (like Gecko) (Kubuntu)',
        'Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.0.12) Gecko/20070731 Ubuntu/dapper-security Firefox/1.5.0.12',
        'Lynx/2.8.5rel.1 libwww-FM/2.14 SSL-MM/1.4.1 GNUTLS/1.2.9',
        "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.7 (KHTML, like Gecko) Ubuntu/11.04 Chromium/16.0.912.77 Chrome/16.0.912.77 Safari/535.7",
        "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0 "]
    # use proxy ip
    # ips_list = ['60.220.204.2:63000','123.150.92.91:80','121.248.150.107:8080','61.185.21.175:8080','222.216.109.114:3128','118.144.54.190:8118',
    #           '1.50.235.82:80','203.80.144.4:80']
    # ip = random.choice(ips_list)
    # print '使用的代理ip地址： ' + ip
    # proxy_support = urllib2.ProxyHandler({'http':'http://'+ip})
    # opener = urllib2.build_opener(proxy_support)
    # urllib2.install_opener(opener)
    user_agent = random.choice(user_agents)
    headers = {
        'User-Agent': user_agent,
        'Accept-Encoding': 'gzip'}
    req = requests.get(url=url, headers=headers)
    html_doc = req.text
    soup = BeautifulSoup(html_doc, "html.parser")
    titles = soup.select('h3 > a')
    spans = soup.select('div.info_content > span')
    # print(len(titles), len(spans))
    sql_titles = []
    sql_urls = []
    sql_subtimes = []
    for title in titles:
        sql_title = title.get_text().replace(' ', '')
        sql_titles.append(sql_title)
        sql_url = "https://www.qdfuns.com" + title.get('href')
        sql_urls.append(sql_url)
    for time in spans:
        sql_subtime = time.get_text()
        sql_subtimes.append(sql_subtime)
    tosql([sql_titles, sql_urls, sql_subtimes])


def tosql(infoLists):
    db = sql_operation.getcon()
    titles = infoLists[0]
    urls = infoLists[1]
    subtimes = infoLists[2]
    stock_time = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
    for i in range(len(titles)):
        insertsql = "INSERT INTO `original_qdfuns_article` (`title`, `url`, `submit_time`, `stock_time`) " \
                    "VALUES ('%s', '%s', '%s', '%s')" % \
                    (titles[i], urls[i], subtimes[i], stock_time)
        # print(insertsql)
        sql_operation.baseoperation(db, insertsql)
    sql_operation.closecon(db)


def spiderhandler(start, end):
    for i in range(start, end):
        print("------------------------------------------------------当前爬取第" + str(
            i) + "页内容-------------------------------")
        url = "https://www.qdfuns.com/article/list/hotest/page/" + str(i) + ".html"
        html(url)


def title():
    db = sql_operation.getcon()
    selectsql = "SELECT article_id,title FROM original_qdfuns_article WHERE title_flag IS NULL"
    results = sql_operation.baseselect(db, selectsql)
    for row in results:
        id = row[0]
        title = row[1]
        if baidudetector.url(title):
            updatesql = "UPDATE original_qdfuns_article SET title_flag = '0' WHERE (`article_id`='%d')" % (id)
        else:
            updatesql = "UPDATE original_qdfuns_article SET title_flag = '1' WHERE (`article_id`='%d')" % (id)
        sql_operation.baseoperation(db, updatesql)
    sql_operation.closecon(db)


if __name__ == '__main__':
    # spiderhandler(706, 720)
    title()
