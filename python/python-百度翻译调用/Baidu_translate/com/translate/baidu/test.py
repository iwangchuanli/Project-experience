# -*- coding: utf-8 -*-
import urllib.request
import urllib
import re
import time
import random
import socket
import threading
import requests

# 抓取代理IP
ip_totle = []
for page in range(2, 6):
    # url = 'http://ip84.com/dlgn/' + str(page)
    url = 'http://www.xicidaili.com/wn/' + str(page)  # 西刺代理
    # url = "https://www.kuaidaili.com/free/inha/"+str(page)
    # headers = {"User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64)"}
    headers = {'Accept-Encoding': 'gzip',
               'Host': 'www.xicidaili.com',
               'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36'}
    # request = urllib.request.Request(url=url, headers=headers)
    # response = urllib.request.urlopen(request)
    # content = response.read().decode('utf-8')
    req = requests.get(url=url, headers=headers)
    content = req.text
    print('get page', page)
    pattern = re.compile('<td>(\d.*?)</td>')  # 截取<td>与</td>之间第一个数为数字的内容
    ip_page = re.findall(pattern, str(content))
    ip_totle.extend(ip_page)
    time.sleep(random.choice(range(1, 3)))
# 打印抓取内容
print('代理IP地址     ', '\t', '端口', '\t', '速度', '\t', '验证时间')
for i in range(0, len(ip_totle), 4):
    print(ip_totle[i], '    ', '\t', ip_totle[i + 1], '\t', ip_totle[i + 2], '\t', ip_totle[i + 3])
# 整理代理IP格式
proxys = []
for i in range(0, len(ip_totle), 4):
    proxy_host = ip_totle[i] + ':' + ip_totle[i + 1]
    proxy_temp = {"https": proxy_host}
    proxys.append(proxy_temp)

proxy_ip = open('proxy_ip_baidu.txt', 'w')  # 新建一个储存有效IP的文档
lock = threading.Lock()  # 建立一个锁


# 验证代理IP有效性的方法
def test(i):
    socket.setdefaulttimeout(10)  # 设置全局超时时间
    url = "https://www.baidu.com/"  # 打算爬取的网址 https://fanyi.baidu.com/transapi  https://www.javatpoint.com
    try:
        proxy_support = urllib.request.ProxyHandler(proxys[i])
        opener = urllib.request.build_opener(proxy_support)
        opener.addheaders = [("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64)")]
        urllib.request.install_opener(opener)
        res = urllib.request.urlopen(url).read()
        lock.acquire()  # 获得锁
        print(proxys[i], 'is OK')
        proxy_ip.write('%s\n' % str(proxys[i]))  # 写入该代理IP
        lock.release()  # 释放锁
    except Exception as e:
        lock.acquire()
        print(proxys[i], e)
        lock.release()


# 单线程验证
'''for i in range(len(proxys)):
    test(i)'''
# 多线程验证
threads = []
for i in range(len(proxys)):
    thread = threading.Thread(target=test, args=[i])
    threads.append(thread)
    thread.start()
# 阻塞主进程，等待所有子线程结束
for thread in threads:
    thread.join()

proxy_ip.close()  # 关闭文件

proxy_ip
