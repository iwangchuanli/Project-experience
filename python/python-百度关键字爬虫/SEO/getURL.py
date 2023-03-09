# -*- coding: utf-8 -*-
# @Author: Marte
# @Date:   2019-04-13 13:57:54
# @Last Modified by:   Marte
# @Last Modified time: 2019-04-13 14:10:57
#!/usr/bin/python
# -*- coding: UTF-8 -*-

import os

fr = open('cnblogs_php3','r',encoding='utf-8')
fw = open('cnblogs_php4','a',encoding='utf-8')
urls = fr.read()
url = ''
# print(urls)
while(urls.find('https://www.cnblogs.com') != -1):
    url = urls[urls.find('https://www.cnblogs.com'):urls.find('.html')+5]+'\n'
    urls = urls[urls.find('.html')+5:]
    fw.write(url)
    print(url)
fw.close()
fr.close()
