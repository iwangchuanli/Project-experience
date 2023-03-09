# -*- coding: utf-8 -*-
# @Author: Marte
# @Date:   2019-04-13 08:52:46
# @Last Modified by:   Marte
# @Last Modified time: 2019-04-13 08:53:23
import requests
from bs4 import BeautifulSoup

# 获取网页主体内容
def getHTML(pageAddress):
    print("读取当前网址内容："+pageAddress)
    headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36',
    'Accept-Encoding':'gzip'}
    req = requests.get(url=pageAddress,headers=headers)
    html_doc = req.text
    #创建一个BeautifulSoup解析对象
    soup = BeautifulSoup(html_doc,"html.parser",from_encoding="utf-8")
    #获取
    links = soup.select('div#city')
    nextName = soup.select('a.next')[0]['href']
    fileName = pageAddress[pageAddress.rindex('/')+1:]
    # 图片提取拼接？
    # images = soup.select('div#city > img')
    # print(images)
    # 文件创建
    print('正在生成文件：'+fileName)
    fileOut(links,fileName)
    #下一个网页递归
    nextPage(nextName)

# 寻找下一个网页
def nextPage(nextName):
    pageAddress = 'https://www.javatpoint.com/'+nextName
    getHTML(pageAddress)

# 文件创建
def fileOut(links,fileName):
    f= open(fileName,"w",encoding='utf-8')
    ss="".join('%s' %id for id in links)
    f.write(ss)
    f.close()

# 图片解析处理
def fileIn():
    pass;


if __name__ == '__main__':
    targe = 'https://www.javatpoint.com/flask-tutorial'
    getHTML(targe)
'''
https://www.javatpoint.com/django-tutorial
https://www.javatpoint.com/java-tutorial
https://www.javatpoint.com/variable-datatype
https://www.javatpoint.com/django-tutorial
https://www.javatpoint.com/flask-tutorial

'''