# -*- coding: utf-8 -*-
import urllib
import random
import requests
from bs4 import BeautifulSoup


# 百度网址拼接  + 关键字wd  +  页数控制
def url(keyWord):
    startNum = '00'
    keyWord = keyWord[:38]
    # print('当前搜索关键字：' + keyWord)
    baiduUrl = 'https://www.baidu.com/s?wd=' + keyWord + '&pn=' + startNum + '&oq=PHP%E5%88%87%E5%89%B2%E5%AD%97%E7%AC%A6%E7%94%A8%E5%88%B0%E7%9A%84explode%20%E4%BB%A5%E5%8F%8A%E8%AE%A1%E6%95%B0count&ie=utf-8&rsv_idx=6&rsv_pq=c28acd3100001b47&rsv_t=3d9eQDHY2fkt5dPog%2BySSRQbFDP6QW1tQ7mPdRqFVOAh3K436Njg0kcRFgY'
    return html(baiduUrl, keyWord)


# 百度搜索HTML内容
def html(URL, keyWord):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36',
        'Accept-Encoding': 'gzip'}
    req = requests.get(url=URL, headers=headers)
    html_doc = req.text
    soup = BeautifulSoup(html_doc, "html.parser")
    # , from_encoding = "utf-8"
    titles = soup.select('h3 > a')
    intros = soup.select('div.c-abstract')
    if detector(titles, keyWord) and detector(intros, keyWord):
        print('当前搜索关键字：'+keyWord + '        <  5')
        return True
    else:
        print('当前搜索关键字：'+keyWord + '        >  =5')
        return False


# 标题 & 简介 飘红检测
def detector(emInfo, keyWord):
    ss = "---".join('%s' % id for id in emInfo)
    count = 1
    checkNum = 0
    length = len(keyWord)
    emStr = ss.split('---')
    for baiduEm in emStr:
        # print('----------------------------------------------------第'+str(count)+'条搜索记录----------------------------------------')
        count += 1
        sss = ''
        cfRate = 0
        while baiduEm.find('</em>') != -1:
            s = baiduEm[baiduEm.find('<em>') + 4:baiduEm.find('</em>')]
            baiduEm = baiduEm[baiduEm.find('</em>') + 3:]
            cfRate = round((len(s) / length), 2)
            if cfRate > 0.8:
                checkNum += 1
            # print('---'+s+'---的重复率：'+str(cfRate)+'-----')
            sss += s
    if checkNum >= 5:
        # print(keyWord + '的重复条数>=5')
        return False
    else:
        # print(keyWord+'的重复条数<5')
        return True
