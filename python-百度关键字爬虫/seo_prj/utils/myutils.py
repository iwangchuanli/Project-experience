# -*- coding: utf-8 -*-
'''
通用工具类


'''
import jieba
import ast
import random, sys


# 关键字分词
def participle(self):
    s = self.encode('utf-8')  # 举个栗子是字符串s，为了匹配下文的unicode形式，所以需要解码
    # p = re.compile(ur'[\u4e00-\u9fa5]')  # 这里是精髓，[\u4e00-\u9fa5]是匹配所有中文的正则，因为是unicode形式，所以也要转为ur
    # print(p.split(s))
    # 使用re库的split切割
    jieba.add_word('html')
    seg_list = jieba.cut(s, cut_all=True)
    keyList = []
    for item in seg_list:
        if len(item) >= 2:
            keyList.append(item)
    # print(keyList)
    return keyList


def getproxyip(ip_file):
    fo = open(ip_file, 'r', encoding='utf-8')
    proxys = fo.read().split('\n')
    proxy = ast.literal_eval(random.choice(proxys))
    # print(proxy)
    fo.close()
    return proxy


def randomheader():
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
    return headers


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
