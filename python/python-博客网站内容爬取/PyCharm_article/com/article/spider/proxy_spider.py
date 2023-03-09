import socket

import requests
from bs4 import BeautifulSoup
import urllib.request
import os
import random
import time
import sql_operation
import _thread
import threading
import baidudetector


def get_html(i):
    print("------------------------------------------------第" + str(i) + "页内容")
    url = 'https://www.xicidaili.com/nn/' + str(i)
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
    req = requests.get(url=url, headers=headers)
    html_doc = req.text
    soup = BeautifulSoup(html_doc, "html.parser")
    tds = soup.select('tr.odd > td')
    row = []
    rows = []
    count = 0
    for i in range(0, len(tds)):
        count += 1
        row.append(tds[i].get_text())
        if count >= 10:
            count = 0
            rows.append(row)
            row = []
    for temp in rows:
        temp[0] = temp[1]
        temp[1] = temp[2]
        temp[2] = temp[4]
        temp[4] = temp[3].replace('\n', '')
        temp[3] = temp[5]
        temp[5] = temp[8]
        temp[6] = temp[9]
        del temp[9]
        del temp[8]
        del temp[7]
    iptosql(rows)


def iptosql(rows):
    db = sql_operation.getcon()
    for row in rows:
        replace_sql = "REPLACE INTO `t_proxy_info` " \
                      "(`ip`, `port`, `anonymous`, `type`, `location`, `speed`, `last_verify_time`) " \
                      "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')" \
                      % (row[0], row[1], row[2], row[3], row[4], row[5], row[6])
        sql_operation.baseoperation(db, replace_sql)
    sql_operation.closecon(db)


def sqltofile():
    db = sql_operation.getcon()
    select_sql = "SELECT ip,`port`,type FROM t_proxy_info Where type = 'HTTPS' Order By rand() Limit 5000"
    results = sql_operation.baseselect(db, select_sql)
    proxys = []
    for tur in results:
        item_list = []
        for item in tur:
            item_list.append(item)
        proxys.append(item_list)
    sql_operation.closecon(db)
    print(proxys)
    thread(proxys)


# 多线程验证
def thread(proxys):
    threads = []
    for i in range(len(proxys)):
        thread = threading.Thread(target=verify, args=(proxys, i))
        threads.append(thread)
        thread.start()
    # 阻塞主进程，等待所有子线程结束
    for thread in threads:
        thread.join()


# 验证
def verify(proxys, i):
    item = proxys[i]
    socket.setdefaulttimeout(10)  # 设置全局超时时间
    lock = threading.Lock()  # 建立一个锁
    url = "https://www.baidu.com/"  # 打算爬取的网址 https://fanyi.baidu.com/transapi  https://www.javatpoint.com  https://stackoverflow.com
    host = item[0] + ':' + item[1]
    proxy = {item[2]: host}
    # proxy = {"HTTPS": host}
    try:
        proxy_support = urllib.request.ProxyHandler(proxy)
        opener = urllib.request.build_opener(proxy_support)
        opener.addheaders = [("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64)")]
        urllib.request.install_opener(opener)
        res = urllib.request.urlopen(url).read()
        lock.acquire()  # 获得锁
        print(proxy, 'is OK')
        proxy_file = open('proxy_verify.txt', 'a')
        proxy_file.write('%s\n' % str(proxy))  # 写入该代理IP
        proxy_file.close()
        lock.release()  # 释放锁
    except Exception as e:
        lock.acquire()
        print(proxy, e)
        lock.release()
    pass


def timer(n):
    '''''
    每n秒执行一次
    '''
    i = 1
    while True:
        print(time.strftime('%Y-%m-%d %X', time.localtime()))
        i += 1
        get_html(i)  # 此处为要执行的任务
        time.sleep(n)


if __name__ == '__main__':
    timer(3)
    # proxys = [['118.182.33.6', '42801', 'HTTP'],['118.182.33.6', '42801', 'HTTP'],['183.154.223.211', '9000', 'HTTP'],['115.46.97.2', '8123', 'HTTPS']]
    # thread(proxys)
    # # verify(proxys, 0)
    # sqltofile()
