# -*- coding: utf-8 -*-
# @Author: Marte
# @Date:   2019-04-15 14:52:05
# @Last Modified by:   Marte
# @Last Modified time: 2019-04-15 15:03:18
import requests
import random
from bs4 import BeautifulSoup

def split(article):
    fo = open(article,'r',encoding='utf-8')
    s = fo.read()
    s = s.replace(' ','').replace('\n','').replace('\r','')
    ss = s.split('。')
    sss = []
    for sstr in ss:
        temps = sstr.split(',')
        for temp in temps:
            sss.append(temp)
    for ssstr in sss:
        print(ssstr)

if __name__ == '__main__':
    split('')