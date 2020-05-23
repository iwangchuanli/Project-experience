# -*- coding: utf-8 -*-
# @Author: Marte
# @Date:   2019-04-12 13:16:11
# @Last Modified by:   Marte
# @Last Modified time: 2019-04-13 14:54:19
import requests
from bs4 import BeautifulSoup
import urllib.request
import os

# cnblogs post请求
def getDoc(index):
    print('--------------------------------------------------当前爬取第'+index+'页-----------------------------------------------')
    url = 'https://www.cnblogs.com/mvc/AggSite/PostList.aspx'
    headers = {
        'User-Agent': 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)'
    }
    values = {
        'CategoryId': '108715',
        'CategoryType': "SiteCategory",
        'ItemListActionName': "PostList",
        'PageIndex': index,
        'ParentCategoryId': '108712',
        'TotalPostCount': '1872'
    }
    data = urllib.parse.urlencode(values).encode('utf-8')
    request = urllib.request.Request(url, data, headers)
    html = urllib.request.urlopen(request).read().decode('utf-8')
    HTMLparse(html)

# 单网页爬取处理
def HTMLparse(html_doc):
    #创建一个BeautifulSoup解析对象
    soup = BeautifulSoup(html_doc,"html.parser",from_encoding="utf-8")
    #获取 标题 简介 地址
    titles = soup.select('h3 > a')
    intros = soup.select('p.post_item_summary')
    authors = soup.select('div.post_item_foot > a')
    times = soup.select('div.post_item_foot')
    titleIO = []
    introIO = []
    otherIO = []
    for i in range(len(titles)):
        # print(titles[i].get_text())
        titleWord = titles[i].get_text().replace(' ','')
        introWord = intros[i].get_text().replace(' ','')[:37]
        if (baiduUrl(titleWord,'00') and baiduUrl(introWord,'00')):
            # if(baiduUrl(introWord,'00') and baiduUrl(introWord,'10')):
            print('+++++++++++++++++++++++++++'+titleWord+'-------------可以使用')
            titleIO.append(titles[i])
            introIO.append(intros[i])
            otherIO.append(times[i])
        else:
            print('++++++'+titleWord+'-------------不能够使用')
            continue;
    infoLists = [titleIO,introIO,otherIO]
    fileIO(infoLists)

# 文件存储(原始文件)
def fileIO(infoLists):
    titles = infoLists[0]
    intros = infoLists[1]
    times = infoLists[2]
    f = open('cnblogs_mysql','a',encoding='utf-8')
    for i in range(len(titles)):
        # print(titles[i].get_text())
        other = ''.join(times[i].get_text().split())
        f.write('\n'+'-----------------------------------------------------------------------------------------------------------------------------------------'+'\n')
        f.write(titles[i].get_text()+'\n')
        f.write(titles[i]['href'])
        f.write(intros[i].get_text().replace('\n',''))
        f.write(other.replace(' ',''))
    f.close()

# 多网页内容爬取
def crawler():
    for i in range(51,94):
        getDoc(str(i))


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
        # print('----------searchHTML keyishiyong -------------')
        return True;
    else:
        # print('-----------searchHTML--buneng shiyogn ---------')
        return False;


# 百度网址拼接  + 关键字wd  +  页数控制
def baiduUrl(keyWord,startNum):
    baiduUrl = 'https://www.baidu.com/s?wd='+keyWord+'&pn='+startNum+'&oq=PHP%E5%88%87%E5%89%B2%E5%AD%97%E7%AC%A6%E7%94%A8%E5%88%B0%E7%9A%84explode%20%E4%BB%A5%E5%8F%8A%E8%AE%A1%E6%95%B0count&ie=utf-8&rsv_idx=6&rsv_pq=c28acd3100001b47&rsv_t=3d9eQDHY2fkt5dPog%2BySSRQbFDP6QW1tQ7mPdRqFVOAh3K436Njg0kcRFgY'
    return searchHTML(baiduUrl,keyWord);



# 标题 & 简介 飘红检测
def titleCheck(titles,keyWord):
    ss="---".join('%s' %id for id in titles)
    count = 1
    checkNum = 0
    length = len(keyWord)
    titleStr = ss.split('---')
    for title in titleStr:
        # print('----------------------------------------------------第'+str(count)+'条搜索记录----------------------------------------')
        count += 1
        sss=''
        cfRate = 0
        while(title.find('</em>') != -1):
            s = title[title.find('<em>')+4:title.find('</em>')]
            title = title[title.find('</em>')+3:]
            cfRate = round((len(s)/length),2)
            if cfRate > 0.88:
                checkNum += 1
            # print('---'+s+'---的重复率：'+str(cfRate)+'-----')
            sss +=s
    if(checkNum >= 5):
        print(keyWord+'的重复条数>=5')
        return False;
    else:
        # print(keyWord+'的重复条数<5')
        return True;


# 简介飘红检测
def introCheck(intros,keyWord):
    ss="---".join('%s' %id for id in intros)
    length=len(keyWord)
    count = 1
    introStrs = ss.split('---')
    for intro in introStrs:
        print('----------------------------------------------------第'+str(count)+'条简介记录----------------------------------------')
        count += 1
        sss=''
        while(intro.find('</em>') != -1):
            s = intro[intro.find('<em>')+4:intro.find('</em>')]
            intro = intro[intro.find('</em>')+3:]
            print('---'+s+'---的重复率：'+str(round((len(s)/length),2))+'-----')
            sss +=s




if __name__ == '__main__':
    crawler()
    # baiduUrl('PHP的数据类型和魔术常量','00')