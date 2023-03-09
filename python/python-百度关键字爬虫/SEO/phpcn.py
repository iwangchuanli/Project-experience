# -*- coding: utf-8 -*-
# @Author: Marte
# @Date:   2019-04-15 14:16:55
# @Last Modified by:   Marte
# @Last Modified time: 2019-04-15 14:50:47

import requests
import random
from bs4 import BeautifulSoup


def getHTML(pageAddress):
    headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36',
    'Accept-Encoding':'gzip'}
    req = requests.get(url=pageAddress,headers=headers)
    html_doc = req.text
    soup = BeautifulSoup(html_doc,"html.parser",from_encoding="utf-8")
    titles = soup.select('h3')
    print(titles)


if __name__ == '__main__':
    # getHTML('https://www.qdfuns.com/article/list/newest/page/4.html')