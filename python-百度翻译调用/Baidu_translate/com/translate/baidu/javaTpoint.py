# -*- coding: utf-8 -*-

import requests
import urllib
import urllib.request
from bs4 import BeautifulSoup
import random
import json
import _thread
import threading
import os
import MySQLdb
import time
import ast
import socket
import re


# 获得数据库连接--------------------------------------------------------------------------------------------------------
def getcon():
    # 打开数据库连接
    db = MySQLdb.connect("127.0.0.1", "root", "123456", "javatpoint", charset='utf8')
    return db


# 关闭数据库连接
def closecon(db):
    db.close()


# 数据查询操作
def baseselect(sql):
    db = getcon()
    cursor = db.cursor()
    try:
        # 执行SQL语句
        cursor.execute(sql)
        # 获取所有记录列表
        results = cursor.fetchall()
        print('\033[1;32m' + '+++++sql查询成功！+++++' + '\033[0m')
        return results
    except Exception as e:
        print(e)
        print('\033[1;32m' + "Error: unable to fecth data" + '\033[0m')

    closecon(db)


def baseoperation(sql):
    db = getcon()
    cursor = db.cursor()
    try:
        cursor.execute(sql)
        db.commit()
        print('\033[1;32m' + '+++++sql执行成功！+++++' + '\033[0m')
    except Exception as e:
        print(e)
        print('\033[1;31m' + '-----sql执行失败！-----' + '\033[0m')
        db.rollback()
    closecon(db)


# 避免数据重复记录
def sql_repeat(title):
    selectsql = 'SELECT title FROM t_javatpoint_test'
    results = baseselect(selectsql)
    for row in results:
        if title in row:
            return False
    return True


# 无内容的单标签------------------------------------------------------------------------------------------------------
def tag(string, name):
    string = string.replace('<' + name + '/>', '')
    return string


# 无内容的双标签
def tags(string, name):
    string = string.replace('</' + name + '>', '')
    start = 0
    end = 0
    while string.find('<' + name, end) != -1:
        string = string[:string.find('<' + name, end)] + string[string.find('>', start) + 1:]
        start = string.find('<' + name)
        end = string.find('>')
    # print(string)
    return string


# 有内容的单标签
def content_tag(string, name):
    pass


# 有内容的双标签
def content_tags(string, name):
    pass


# 去除标签属性，保留标签
def remove_attribute(string, name):
    new_string = string
    start = 0
    end = 0
    while string.find('<' + name) != -1:
        string = string[:string.find('<' + name, end)] + string[string.find('>', start):]
        start = string.find('<' + name)
        end = string.find('>')
        new_string = '<' + name + string
    return new_string


# 百度翻译模块----------------------------------------------------------------------------------------------------------
def translate(word, count):
    url = "https://fanyi.baidu.com/transapi?from=auto&to=zh&query=" + word
    # print('开始翻译语句：' + word)
    if count <= 5:
        socket.setdefaulttimeout(5)  # 设置全局超时时间
        try:
            # proxy_support = urllib.request.ProxyHandler(getproxyip('proxy_ip_fanyi.txt'))
            # opener = urllib.request.build_opener(proxy_support)
            # opener.addheaders = [getheaders()]
            # urllib.request.install_opener(opener)
            # res = urllib.request.urlopen(url).read()
            headers = {
                'User-Agent': "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.7 (KHTML, like Gecko) "
                              "Ubuntu/11.04 Chromium/16.0.912.77 Chrome/16.0.912.77 Safari/535.7",
                'Accept-Encoding': 'gzip'}
            req = requests.get(url=url, headers=headers)
            html_doc = req.text
            jsons = json.loads(html_doc)
            return jsons['data'][0]['dst']
        except Exception as e:
            print(e)
            print('\033[1;31m' + '开始重新尝试语句翻译:' + '\033[0m' + word)
            count += 1
            return word
            # translate(word, count)
    else:
        return word


# 文本翻译控制
def translate_handler(string, name):
    end = 0
    while string.find('<' + name + '>', end) != -1:
        start = string.find('<' + name + '>')
        end = string.find('</' + name + '>')
        word = string[start + 3:end]
        trans_word = translate(word, 0)
        string = string[:start + 4] + trans_word + string[end:]
        # print(string)
    return string


# 随机获取代理ip-----------------------------------------------------------------------------------------------
def getproxyip(ip_file):
    fo = open(ip_file, 'r')
    proxys = fo.read().split('\n')
    proxy = ast.literal_eval(random.choice(proxys))
    print(proxy)
    fo.close()
    return proxy
    pass


# 随机获取headers头信息
def getheaders():
    user_agents = [
        'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11',
        'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)',
        'Mozilla/5.0 (compatible; Konqueror/3.5; Linux) KHTML/3.5.5 (like Gecko) (Kubuntu)',
        'Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.0.12) Gecko/20070731 Ubuntu/dapper-security Firefox/1.5.0.12',
        "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.7 (KHTML, like Gecko) Ubuntu/11.04 Chromium/16.0.912.77 Chrome/16.0.912.77 Safari/535.7",
        "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0 "]
    user_agent = random.choice(user_agents)
    print(user_agent)
    headers = {
        'User-Agent': user_agent,
        'Accept-Encoding': 'gzip'}
    return headers


# 获取网页主体内容
def getHTML(pageAddress, count):
    print("读取当前网址内容：" + pageAddress)
    if count <= 6:
        socket.setdefaulttimeout(20)  # 设置全局超时时间
        try:
            proxy_support = urllib.request.ProxyHandler(getproxyip('proxy_ip_javatpoint.txt'))
            opener = urllib.request.build_opener(proxy_support)
            opener.addheaders = [getheaders()]
            urllib.request.install_opener(opener)
            res = urllib.request.urlopen(pageAddress).read()
            # req = requests.get(url=pageAddress, headers=getheaders())
            # res = req.text
            # 创建一个BeautifulSoup解析对象
            soup = BeautifulSoup(res, "html.parser")
            # 获取
            contents = soup.select('div#city')
            nextName = soup.select('a.next')[0]['href']
            fileName = pageAddress[pageAddress.rindex('/') + 1:]
            # 图片提取拼接？
            # images = soup.select('div#city > img')
            # 分类信息处理
            # classify_page = soup.select('#link > div > ul > a')
            # print(classify_page)
            # if len(classify_page) >= 1 :
            #     classify = classify_page[0].get_text()
            classify = 'python'
            print('解析网页，获得网页主体内容：' + fileName)
            content = "".join('%s' % id for id in contents)
            # 获取网页主要信息传输并进行处理
            page_info = [fileName, nextName, content, classify]
            content_handler(page_info)
            # 下一个网页递归
            nextPage(nextName)
        except Exception as e:
            print(e)
            print('\033[1;31m' + '开始重新尝试第' + str(count + 1) + '次网页连接' + '\033[0m')
            count += 1
            getHTML(pageAddress, count)
    else:
        proxy_ip = open('proxy_ip_javatpoint.txt', 'w')  # 清空储存有效IP的文档
        proxy_ip.close()  # 关闭文件
        print("重新抓取生成ip地址")
        get_proxyip()
        # 利用新代理重新进行网页爬取
        getHTML(pageAddress, 0)


# 寻找下一个网页
def nextPage(nextName):
    pageAddress = 'https://www.javatpoint.com/' + nextName
    getHTML(pageAddress, 0)


# 爬虫存储网页
def spider_file():
    pass


# spider控制
def spider_handle():
    pass


# 数据库控制
def sql_handler(item):
    if sql_repeat(item[0]):
        next_title = item[4]
        classify = item[5]
        update_time = time.strftime("%Y-%m-%d", time.localtime())
        replacesql = "REPLACE INTO `t_javatpoint_test` " \
                     "(`title`, `url`, `english_content`, `translate_content`, `next_title`, `classify`, `update_time`) " \
                     "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')" \
                     % (item[0], item[1], item[2], item[3], next_title, classify, update_time)
        # print(replacesql)
        baseoperation(replacesql)
    else:
        print('\033[1;31m' + '数据库中已有相同标题记录' + '\033[0m')


# 网页内容每行的原始处理处理
def content_row(row):
    # 去除注释
    while row.find('<!--') != -1:
        row = row[:row.find('<!--')] + row[row.find('-->') + 3:]
    # div标签去除
    row = tags(row, 'div')
    # ss = tags(ss, 'table')
    # ss = tags(ss, 'tr')
    # ss = tags(ss, 'td')
    row = tags(row, 'strong')
    row = tags(row, 'b')
    row = tags(row, 'B')
    row = tag(row, 'br')
    row = remove_attribute(row, 'h1')
    row = remove_attribute(row, 'h2')
    row = remove_attribute(row, 'h3')
    row = remove_attribute(row, 'h4')
    row = remove_attribute(row, 'h5')
    row = remove_attribute(row, 'a')
    row = remove_attribute(row, 'span')
    row = remove_attribute(row, 'table')
    row = remove_attribute(row, 'tr')
    row = remove_attribute(row, 'td')
    # 去除' " 方便存入
    row = row.replace('\'', '\\\'')
    row = row.replace('\"', '\\\"')
    # 翻译
    # ss = translate_handler(ss, 'p')
    trans_row = translate_handler(row, 'h2')
    trans_row = translate_handler(trans_row, 'p')
    return [row, trans_row]


# 网页内容整体的处理
def content_handler(content_info):
    filename = content_info[0]
    nextfilename = content_info[1]
    page_content = content_info[2]
    classify = content_info[3]
    new_content = []
    new_content_trans = []
    item = []
    content_rows = page_content.split('\n')
    for row in content_rows:
        rows = content_row(row)
        if len(rows[0]) >= 1:
            new_content.append(rows[0])
            new_content_trans.append(rows[1])
    content = "".join(new_content)
    contetn_trans = "".join(new_content_trans)
    print("网页主体处理后字节长度：" + str(len(content)))
    url = 'https://www.javatpoint.com/' + filename
    item.append(filename)
    item.append(url)
    item.append(content)
    item.append(contetn_trans)
    item.append(nextfilename)
    item.append(classify)
    print('网页信息处理结束，开始导入到数据库')
    sql_handler(item)


# 为线程定义一个函数
def print_time(threadName, delay):
    count = 0
    while count < 5:
        time.sleep(delay)
        count += 1
        print("%s: %s" % (threadName, time.ctime(time.time())))


# 多线程控制
def thread_handler():
    # 创建两个线程
    count = 0
    try:
        if count < 5:
            threading.Lock().acquire()
            _thread.start_new_thread(print_time, ("Thread-1", 2,))
        threading.Lock().release()
        _thread.start_new_thread(print_time, ("Thread-2", 4,))

    except:
        print("Error: 无法启动线程")
    while 1:
        pass


# 代理IP地址文件的生成
def get_proxyip():
    # 抓取代理IP
    ip_totle = []
    for page in range(2, 12):
        # url = 'http://ip84.com/dlgn/' + str(page)
        url = 'https://www.xicidaili.com/wn/' + str(page)  # 西刺代理
        headers = {'Accept-Encoding': 'gzip',
                   'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36'}
        req = requests.get(url=url, headers=headers)
        content = req.text
        print('获得代理ip页数：', page)
        pattern = re.compile('<td>(\d.*?)</td>')  # 截取<td>与</td>之间第一个数为数字的内容
        ip_page = re.findall(pattern, str(content))
        ip_totle.extend(ip_page)
        time.sleep(random.choice(range(1, 3)))

    # 整理代理IP格式
    proxys = []
    for i in range(0, len(ip_totle), 4):
        proxy_host = ip_totle[i] + ':' + ip_totle[i + 1]
        proxy_temp = {"https": proxy_host}
        proxys.append(proxy_temp)
    print(proxys)
    # 多线程验证
    threads = []
    for i in range(len(proxys)):
        thread = threading.Thread(target=test, args=(proxys, i))
        threads.append(thread)
        thread.start()
    # 阻塞主进程，等待所有子线程结束
    for thread in threads:
        thread.join()


# 验证代理IP有效性的方法
def test(proxys, i):
    lock = threading.Lock()  # 建立一个锁
    socket.setdefaulttimeout(10)  # 设置全局超时时间
    url = "https://www.javatpoint.com"  # 打算爬取的网址 https://fanyi.baidu.com/transapi  https://www.javatpoint.com
    try:
        proxy_support = urllib.request.ProxyHandler(proxys[i])
        opener = urllib.request.build_opener(proxy_support)
        opener.addheaders = [("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64)")]
        urllib.request.install_opener(opener)
        res = urllib.request.urlopen(url).read()
        lock.acquire()  # 获得锁
        print(proxys[i], 'is OK')
        proxy_ip = open('proxy_ip_javatpoint.txt', 'a')  # 新建一个储存有效IP的文档
        proxy_ip.write('%s\n' % str(proxys[i]))  # 写入该代理IP
        proxy_ip.close()  # 关闭文件
        lock.release()  # 释放锁
    except Exception as e:
        lock.acquire()
        print(proxys[i], e)
        lock.release()


if __name__ == '__main__':
    targe = 'https://www.javatpoint.com/numpy-string-functions'
    getHTML(targe, 0)
    # thread_handler()
