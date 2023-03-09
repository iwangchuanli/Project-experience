import random

import MySQLdb
import requests

# ! -*- encoding:utf-8 -*-

import requests

# 要访问的目标页面
targetUrl = "https://blog.csdn.net/wang978252321/article/details/95489446"
# targetUrl = "http://proxy.abuyun.com/switch-ip"
# targetUrl = "http://proxy.abuyun.com/current-ip"

# 代理服务器
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

proxies = {
    "http": proxyMeta,
    "https": proxyMeta,
}

resp = requests.get(targetUrl, proxies=proxies)
print(proxyMeta)
print(resp.status_code)
# print(resp.text)
fo = open("proxy_test.txt", 'a', encoding='utf-8')
fo.write(proxyMeta)
fo.close()
