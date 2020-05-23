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
    user_agent = random.choice(user_agents)
    headers = {
        'User-Agent': user_agent,
        'Accept-Encoding': 'gzip'}
    req = requests.get(url=url, headers=headers)
    html_doc = req.text
    soup = BeautifulSoup(html_doc, "html.parser")
    titles = soup.select('a[id]')
    classify = soup.select("div.navtable > span > a")
    page_list = []
    for title in titles:
        item = []
        item.append(title.get_text())
        item.append(title.get('href'))
        item.append(classify[0].get_text())
        page_list.append(item)
    tosql(page_list)


# shuju
def tosql(page):
    db = sql_operation.getcon()
    for item in page:
        replace_sql = "REPLACE INTO `t_original_bytes_article` " \
                      "(`title`, `url`, `classify`, `flag`) " \
                      "VALUES ('%s', '%s', '%s', '0')" \
                      % (item[0], item[1], item[2])
        sql_operation.baseoperation(db, replace_sql)
    sql_operation.closecon(db)


if __name__ == '__main__':
    for i in range(2, 50):
        print("----------------------------------------第" + str(i) + "页内容")
        html('https://bytes.com/topic/php/' + str(i) + '/?sort=views&order=desc&daysprune=-1')
