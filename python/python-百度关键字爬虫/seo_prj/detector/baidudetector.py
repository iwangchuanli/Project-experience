# -*- coding: utf-8 -*-
'''
Windows百度重复度查询

'''
import urllib
import random
import requests
from bs4 import BeautifulSoup
import re
import jieba


# 百度网址拼接  + 关键字wd  +  页数控制
def url(keyWord):
    startNum = '00'
    print('当前搜索关键字： ' + keyWord)
    print(participle(keyWord))
    keyWord = keyWord[:38]
    if keyWord.find('+'):
        keyWord = keyWord.replace('+', "%2B")
    baiduUrl = 'https://www.baidu.com/s?wd=' + keyWord + '&pn=' + startNum + '&oq=PHP%E5%88%87%E5%89%B2%E5%AD%97%E7%AC%A6%E7%94%A8%E5%88%B0%E7%9A%84explode%20%E4%BB%A5%E5%8F%8A%E8%AE%A1%E6%95%B0count&ie=utf-8&rsv_idx=6&rsv_pq=c28acd3100001b47&rsv_t=3d9eQDHY2fkt5dPog%2BySSRQbFDP6QW1tQ7mPdRqFVOAh3K436Njg0kcRFgY'
    # print('当前搜索：' + baiduUrl)
    return html(baiduUrl, keyWord)


# 百度搜索HTML内容
def html(URL, keyWord):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36',
        'Accept-Encoding': 'gzip'}
    req = requests.get(url=URL, headers=headers)
    html_doc = req.text
    soup = BeautifulSoup(html_doc, "html.parser")
    titles = soup.select('h3 > a')
    intros = soup.select('div.c-abstract')
    snapshoots = soup.select('div.f13 > a')
    for url in snapshoots:
        if url.get_text().find('php中文网') != -1:
            print("php中文网已在首页")
            return False
    title_num = detector(titles, keyWord)
    if title_num < 5:
        intro_num = detector(intros, keyWord)
        if title_num + intro_num < 5:
            print('重复次数:' + str(title_num) + '+' + str(intro_num) + '  <  5')
            return True
    else:
        print('当前搜索关键字：' + keyWord + '        >  =5')
        return False


# 标题 & 简介 飘红检测
def detector(emInfo, keyWord):
    ss = "---".join('%s' % id for id in emInfo)
    count = 1
    checkNum = 0
    length = len(keyWord)
    emStr = ss.split('---')
    for baiduEm in emStr:
        # print('----------------------------------------------------第' + str(count) + '条搜索记录----------------------------------------')
        count += 1
        sss = ''
        cfRate = 0
        # print(baiduEm)
        par_Tnum = 0
        par_Fnum = 0
        for x in participle(keyWord):
            if baiduEm.find(x) != -1:
                par_Tnum += 1
                # print(x + '--------')
            else:
                par_Fnum += 1
                # print(x + '-+++++++-')
        if par_Tnum / len(participle(keyWord)) >= 0.7:
            checkNum += 1
            continue
        while baiduEm.find('</em>') != -1:
            s = baiduEm[baiduEm.find('<em>') + 4:baiduEm.find('</em>')]
            # print(s)
            baiduEm = baiduEm[baiduEm.find('</em>') + 3:]
            cfRate = round((len(s) / length), 3)
            if cfRate > 0.7:
                checkNum += 1
                # print('---' + s + '---的重复率：' + str(cfRate) + '-----')
                continue
    return checkNum
    # if checkNum >= 5:
    #     # print(keyWord + '的重复条数>=5')
    #     return False
    # else:
    #     # print(keyWord+'的重复条数<5')
    #     return True


# 关键字分词
def participle(keyWord):
    s = keyWord.encode('utf-8')  # 举个栗子是字符串s，为了匹配下文的unicode形式，所以需要解码
    # p = re.compile(ur'[\u4e00-\u9fa5]')  # 这里是精髓，[\u4e00-\u9fa5]是匹配所有中文的正则，因为是unicode形式，所以也要转为ur
    # print(p.split(s))
    # 使用re库的split切割
    jieba.add_word('html')
    seg_list = jieba.cut(s, cut_all=True)
    keyList = []
    for list in seg_list:
        if len(list) >= 2:
            keyList.append(list)
    # print(keyList)
    return keyList


# 快照地址检测 查看网址
def snapshoot(soup):
    pass


if __name__ == '__main__':
    url('语句查重比率')
    # participle('git配置ssh，解决每次提交代码都要输入用户名和密码的问题。')
