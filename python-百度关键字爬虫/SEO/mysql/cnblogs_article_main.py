# -*- coding=utf-8 -*-

import time
import requests
import random
from bs4 import BeautifulSoup
import baidudetector
import sql_operation
import cnblogs


def cnblogsSpider(index):
    # cnblogs.crawler(2, 3)
    infoLists = cnblogs.getDoc(index)
    titles = infoLists[0]
    intros = infoLists[1]
    urls = infoLists[2]
    article_from = '博客园首页'
    others = infoLists[3]
    stock_time = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
    db = sql_operation.getcon()
    for i in range(len(titles)):
        insertsql = "INSERT INTO `original_python_article` (`title`, `intro`, `url`, `from`, `other`, `stock_time`) " \
                    "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')" % \
                    (titles[i], intros[i], urls[i], article_from, others[i], stock_time)
        sql_operation.baseoperation(db, insertsql)
    sql_operation.closecon(db)


# 标题和简介 飘红检测 更新数据
def titleandintro():
    db = sql_operation.getcon()
    selectsql = "SELECT article_id,title,intro FROM original_python_article WHERE title_flag IS NULL or title_flag = 0"
    results = sql_operation.baseselect(db, selectsql)
    # print(results)
    for row in results:
        article_id = row[0]
        title = row[1]
        intro = row[2]
        if baidudetector.url(title) and baidudetector.url(intro):
            updatesql = "UPDATE `original_python_article` SET `title_flag`='0' WHERE (`article_id`='%d')" % (article_id)
        else:
            updatesql = "UPDATE `original_python_article` SET `title_flag`='1' WHERE (`article_id`='%d')" % (article_id)
        sql_operation.baseoperation(db, updatesql)
        # print(article_id, title, intro)
    sql_operation.closecon(db)


# 内容 文本 代码 飘红检测 更新数据
def content():
    db = sql_operation.getcon()
    selectsql = "SELECT article_id,url FROM original_python_article WHERE (content_flag IS NULL  or content_flag = 0) and title_flag = 0"
    results = sql_operation.baseselect(db, selectsql)
    for row in results:
        article_id = row[0]
        url = row[1]
        print('当前所处理的文章url：       ' + url)
        if circleCheck(url):
            updatesql = "UPDATE `original_python_article` SET `content_flag`='0' WHERE (`article_id`='%d')" % (article_id)
        else:
            updatesql = "UPDATE `original_python_article` SET `content_flag`='1' WHERE (`article_id`='%d')" % (article_id)
        sql_operation.baseoperation(db, updatesql)
    sql_operation.closecon(db)


# 单条记录的详细内容网页查询
def contentHTML(pageAddress):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36',
        'Accept-Encoding': 'gzip'}
    req = requests.get(url=pageAddress, headers=headers)
    html_doc = req.text
    soup = BeautifulSoup(html_doc, "html.parser")
    pres = soup.select('pre')
    ps = soup.select('p')
    count = 0
    keyWord = ' '
    preBol = True
    pBol = True
    if len(pres) >= 1:
        while (len(keyWord) <= 10):
            if count > 5:
                break
            keyWord = random.choice(pres).get_text().replace(' ', '').replace('\n', '').replace('\r', '')
            count += 1
        if (len(keyWord) > 10):
            preBol = baidudetector.url(keyWord[:38])
    keyWord = ' '
    if len(ps) >= 1:
        while (len(keyWord) <= 10):
            if count > 5:
                break
            keyWord = random.choice(ps).get_text().replace(' ', '').replace('\n', '').replace('\r', '')
            count += 1
        if (len(keyWord) > 10):
            pBol = baidudetector.url(keyWord[:38])
    if (preBol and pBol):
        return True
    else:
        return False


# 内容循环查询 避免几率性
def circleCheck(url):
    TNum = 0
    FNum = 0
    for i in range(5):
        if (contentHTML(url)):
            TNum += 1
        else:
            FNum += 1
    if ((TNum - FNum) > 0):
        return True
    else:
        return False


if __name__ == '__main__':
    for index in range(2, 200):
        cnblogsSpider(str(index))
    # titleandintro()
    # content()
