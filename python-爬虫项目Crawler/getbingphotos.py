# -*- coding: utf-8 -*-
# @Author: Wangchuanli
# @Date:   2018-12-03 11:49:02
# @Last Modified by:   Wangchuanli
# @Last Modified time: 2018-12-03 14:15:56

# python3抓取bing主页所有背景图片

import urllib.request
import re
import sys
import os


def getbingbg():
    if os.path.exists('photos') == False:
        os.mkdir('photos')
    for i in range(0,30):
        url = 'http://cn.bing.com/HPImageArchive.aspx?format=js&idx='+str(i)+'&n=1&nc=1361089515117&FORM=HYLH1'
        # print (url)
        html = urllib.request.urlopen(url).read()
        if html == 'null':
            print('open & read bing error!')
            sys.exit(-1)
        html = html.decode('utf-8')
        reg = re.compile('"url":"(.*?)","urlbase"', re.S)
        text = re.findall(reg, html)
        for imgurl in text:
            right = imgurl.rindex('/')
            name = imgurl.replace(imgurl[:right + 1], '')
            savepath = 'photos/' + name
            photourl = "http://s.cn.bing.net/" + imgurl
            print ("imageurl..."+photourl)
            print ("downing..."+savepath)
            urllib.request.urlretrieve(photourl, savepath)
        #print (html)
        #print (text)

print ("------------Start")
getbingbg()
print ("------------End")
