# -*- coding: utf-8 -*-
# @Author: Wangchuanli
# @Date:   2018-12-02 19:20:27
# @Last Modified by:   Wangchuanli
# @Last Modified time: 2018-12-02 21:21:35
import re
import requests
import urllib.request
from bs4 import BeautifulSoup
def down(url,title):
    print ("downloading"+title)
    r = requests.get(url)
    with open(title, "wb") as code:
         code.write(r.content)

def findimage(url):
    html_doc = urllib.request.urlopen(url).read()
    # html = html_doc.decode('utf-8','ignore')
    soup = BeautifulSoup(html_doc,"html.parser",from_encoding="utf-8")
    links = soup.select('img')
    print ("图片链接",html_doc)
    for link in links:
        imageurl = link['src']
        imagefile = link['title']+".png"
        print (emojiurl+imagefile)

findimage("https://www.baidu.com/")




