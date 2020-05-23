# -*- coding: utf-8 -*-
'''
通用工具类
'''
import time

import MySQLdb
import jieba
import ast
import random, sys

# 日志类
import requests

sys.setrecursionlimit(1000000)


class Logger(object):
    def __init__(self, filename='default.log', stream=sys.stdout):
        self.terminal = stream
        self.log = open(filename, 'a', encoding='utf-8')

    # def print(self, message):
    #     self.terminal.write(message + "\n")
    #     self.log.write(message.encode('utf-8') + b"\n")
    # def flush(self):
    #     self.terminal.flush()
    #     self.log.flush()
    # def close(self):
    #     self.log.close()
    def write(self, message):
        self.terminal.write(message)
        self.log.write(message)

    def flush(self):
        pass


# 获得本地文件代理
def getproxyip(ip_file):
    fo = open(ip_file, 'r', encoding='utf-8')
    proxys = fo.read().split('\n')
    proxy = ast.literal_eval(random.choice(proxys))
    # print(proxy)
    fo.close()
    return proxy


# 随机请求头
def randomheader():
    user_agents = [
        "Mozilla/5.0 (Windows NT 10.0; WOW64)", 'Mozilla/5.0 (Windows NT 6.3; WOW64)',
        'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11',
        'Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko',
        'Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36',
        'Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729;\
         .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; rv:11.0) like Gecko)',
        'Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/3.0.1',
        'Mozilla/5.0 (Windows; U; Windows NT 5.1) Gecko/20070309 Firefox/2.0.0.3',
        'Mozilla/5.0 (Windows; U; Windows NT 5.1) Gecko/20070803 Firefox/1.5.0.12',
        'Opera/9.27 (Windows NT 5.2; U; zh-cn)',
        'Mozilla/5.0 (Macintosh; PPC Mac OS X; U; en) Opera 8.0',
        'Opera/8.0 (Macintosh; PPC Mac OS X; U; en)',
        'Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.12) Gecko/20080219 Firefox/2.0.0.12 Navigator/9.0.0.6',
        'Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Win64; x64; Trident/4.0)',
        'Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)',
        'Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; SLCC2; .NET CLR 2.0.50727;\
         .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0C; .NET4.0E)',
        'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Maxthon/4.0.6.2000\
         Chrome/26.0.1410.43 Safari/537.1 ',
        'Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; SLCC2;\
         .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729;\
          Media Center PC 6.0; InfoPath.2; .NET4.0C; .NET4.0E; QQBrowser/7.3.9825.400)',
        'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0 ',
        'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) \
        Chrome/21.0.1180.92 Safari/537.1 LBBROWSER',
        'Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)',
        'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) \
        Chrome/20.0.1132.11 TaoBrowser/3.0 Safari/536.11'
    ]
    user_agent = random.choice(user_agents)
    headers = {
        'User-Agent': user_agent,
        'Connection': 'close',
    }
    return headers


'''
58.218.205.40:7754
221.229.196.234:6987
58.218.205.51:7038

58.218.205.57:2513
58.218.205.55:7817
58.218.205.52:5109

'''
# ip代理设置列表
ip_port = ["180.97.250.157:5147", "58.218.205.39:7893", "180.97.250.158:4107", "221.229.196.212:9311",
           "221.229.196.212:6066", "221.229.196.192:6545",
           "221.229.196.231:9975", "221.229.196.212:4953", "221.229.196.192:2133"]

# 代理服务器 阿布云
proxyHost = "http-dyn.abuyun.com"
proxyPort = "9020"
# 代理隧道验证信息
proxyUser = "HP48W550C1X873PD"
proxyPass = "FED1B0BB31CE94A3"
proxyMeta = "http://%(user)s:%(pass)s@%(host)s:%(port)s" % {
    "host": proxyHost,
    "port": proxyPort,
    "user": proxyUser,
    "pass": proxyPass,
}


# 爬虫
def spider(url, times=0):
    try:
        proxies = {
            "http": proxyMeta,
            "https": proxyMeta,
        }
        # proxies = {
        #     "https": random.choices(port_list)[0]
        # }
        requests.packages.urllib3.disable_warnings()
        # response = requests.get(url, headers=randomheader(), proxies=proxies, timeout=20, verify=False)  # 使用代理ip
        response = requests.get(url, headers=randomheader(), timeout=20, verify=False)# 不使用代理ip
        requests.adapters.DEFAULT_RETRIES = 5
        s = requests.session()
        s.keep_alive = False
        return response
    except Exception as e:
        times += 1
        print("爬虫异常:" + url + "原因-：" + str(e))
        if times > 6:
            return ""
        time.sleep(random.randint(0, 9))
        print("重新爬取:" + str(times) + "===" + url)
        spider(url, times)


# 数据库更新语句执行操作
def sql_opt(databse, sql):
    db = MySQLdb.connect("localhost", "root", "123456789", databse, charset='utf8')
    cursor = db.cursor()
    try:
        cursor.execute(sql)
        db.commit()
    except Exception as e:
        print("sql_opt语句执行异常" + str(e) + "\n" + sql)
        db.rollback()
    db.close()


if __name__ == '__main__':
    print("test")
    fo = open("proxy_ip.txt", 'r', encoding='utf-8')
    port_list = fo.read().split("\n")
    fo.close()
    proxies = {
        "https": random.choices(port_list)[0],
    }
    print(proxies)
