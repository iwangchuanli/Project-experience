# -*- coding: utf-8 -*-
# @Author: Marte
# @Date:   2019-04-12 14:43:23
# @Last Modified by:   Marte
# @Last Modified time: 2019-04-13 14:14:55
import urllib.request
import os

#http代理
#os.environ['http_proxy'] = 'http://127.0.0.1:8080'
#os.environ['https_proxy'] = 'https://127.0.0.1:8080'

url = 'https://www.cnblogs.com/mvc/AggSite/PostList.aspx'
headers = {
    'User-Agent': 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)'
}
values = {
    'CategoryId': '108715',
    'CategoryType': "SiteCategory",
    'ItemListActionName': "PostList",
    'PageIndex': '8',
    'ParentCategoryId': '108712',
    'TotalPostCount': '1872'
}
data = urllib.parse.urlencode(values).encode('utf-8')
request = urllib.request.Request(url, data, headers)
html = urllib.request.urlopen(request).read().decode('utf-8')
print(html)
