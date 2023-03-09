# -*- coding: utf-8 -*-
# @Author: Marte
# @Date:   2019-04-13 15:26:39
# @Last Modified by:   Marte
# @Last Modified time: 2019-04-13 17:25:01
import requests
import random
from bs4 import BeautifulSoup

# 单条记录的详细内容网页查询
def getHTML(pageAddress):
    headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36',
    'Accept-Encoding':'gzip'}
    req = requests.get(url=pageAddress,headers=headers)
    html_doc = req.text
    soup = BeautifulSoup(html_doc,"html.parser",from_encoding="utf-8")
    pres = soup.select('pre')
    ps = soup.select('p')
    count = 0
    keyWord = ' '
    preBol = True;
    pBol = True;
    if len(pres) >= 1:
        while(len(keyWord) <= 10):
            if count > 5:
                break;
            keyWord = random.choice(pres).get_text().replace(' ','')
            count += 1
        if(len(keyWord) > 10):
            preBol = baiduUrl(keyWord[:38],'00')
    keyWord = ' '
    if len(ps) >= 1:
        while(len(keyWord) <= 10):
            if count > 5:
                break;
            keyWord = random.choice(ps).get_text().replace(' ','')
            count += 1
        if(len(keyWord) > 10):
            pBol = baiduUrl(keyWord[:38],'00')
    if (preBol and pBol):
        return True;
    else:
        return False;

# 内容循环查询 避免几率性
def circleCheck(url):
    TNum = 0
    FNum = 0
    for i in range(5):
        if(getHTML(url)):
            TNum += 1
        else:
            FNum += 1
    if((TNum - FNum) > 0):
        print(url+'---------------------------------------YES---')
        return True;
    else:
        print(url+'---------------------------------------NO---')
        return False;

# 文件读写
def fileContentIO():
    fo = open('cnblogs_php5','r+',encoding='utf-8')
    urlLists = fo.read()
    for url in urlLists.split('\n'):
        if(len(url) > 0):
            print(url)
            if(circleCheck(url)):
                fow = open('cnblogs_php6','a',encoding='utf-8')
                fow.write(url+'\n')
                fow.close()
                print('写入一条数据')
    fo.close()

# 百度网址拼接  + 关键字wd  +  页数控制
def baiduUrl(keyWord,startNum):
    baiduUrl = 'https://www.baidu.com/s?wd='+keyWord+'&pn='+startNum+'&oq=PHP%E5%88%87%E5%89%B2%E5%AD%97%E7%AC%A6%E7%94%A8%E5%88%B0%E7%9A%84explode%20%E4%BB%A5%E5%8F%8A%E8%AE%A1%E6%95%B0count&ie=utf-8&rsv_idx=6&rsv_pq=c28acd3100001b47&rsv_t=3d9eQDHY2fkt5dPog%2BySSRQbFDP6QW1tQ7mPdRqFVOAh3K436Njg0kcRFgY'
    return searchHTML(baiduUrl,keyWord);


# 百度搜索HTML内容
def searchHTML(URL,keyWord):
    # print('当前百度搜索URL：'+URL)
    headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36',
    'Accept-Encoding':'gzip'}
    req = requests.get(url=URL,headers=headers)
    html_doc = req.text
    #创建一个BeautifulSoup解析对象
    soup = BeautifulSoup(html_doc,"html.parser",from_encoding="utf-8")
    #获取
    links = soup.select('div')
    titles = soup.select('h3 > a')
    intros = soup.select('div.c-abstract')
    #print(html_doc) 119 160  162--165
    if(titleCheck(titles,keyWord) and titleCheck(intros,keyWord)):
        return True;
    else:
        return False;


# 标题 & 简介 飘红检测
def titleCheck(titles,keyWord):
    ss="---".join('%s' %id for id in titles)
    count = 1
    checkNum = 0
    length = len(keyWord)
    titleStr = ss.split('---')
    for title in titleStr:
        #print('----------------------------------------------------第'+str(count)+'条搜索记录----------------------------------------')
        count += 1
        sss=''
        cfRate = 0
        while(title.find('</em>') != -1):
            s = title[title.find('<em>')+4:title.find('</em>')]
            title = title[title.find('</em>')+3:]
            cfRate = round((len(s)/length),2)
            if cfRate > 0.7:
                checkNum += 1
            #print('---'+s+'---的重复率：'+str(cfRate)+'-----')
            sss +=s
    if(checkNum >= 5):
        print(keyWord+'的重复条数>=5')
        return False;
    else:
        # print(keyWord+'的重复条数<5')
        return True;




if __name__ == '__main__':
    fileContentIO()
    # targe = 'https://www.cnblogs.com/wangziyue/p/10691129.html'
    # urlList=[]
    # for url in urlList:
    #     circleCheck(url)
