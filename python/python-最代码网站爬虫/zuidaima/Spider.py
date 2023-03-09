# -*- coding: utf-8 -*-
# @Author: Marte
# @Date:   2019-04-13 13:46:58
# @Last Modified by:   Marte
# @Last Modified time: 2019-04-13 13:53:20
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
    links = soup.select('a[class="title"]')
    print(links)


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
    targe = 'http://www.zuidaima.com/blog/p4.htm'
    getHTML(targe)