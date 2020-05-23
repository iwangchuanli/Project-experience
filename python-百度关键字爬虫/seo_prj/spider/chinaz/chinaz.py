# -*- coding: utf-8 -*-

import requests
from bs4 import BeautifulSoup
import urllib.request
import myutils


# cnblogs post请求
def getDoc(keyword):
    url = 'http://tool.chinaz.com/kwevaluate'
    headers = {
        'User-Agent': 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)'
    }
    values = {
        't': 'kwevaluate',
        'kw': keyword
    }
    data = urllib.parse.urlencode(values).encode('utf-8')
    request = urllib.request.Request(url, data, headers)
    html = urllib.request.urlopen(request).read().decode('utf-8')
    print(html)


# get
def get(keyword):
    url = "http://index.baidu.com/api/SearchApi/index?word=" + keyword + "&area=0&days=30"
    headers = {
        "Accept": "application / json, text / plain, * / *",
        "Accept-Encoding": "gzip, deflate",
        "Accept-Language": "zh-CN,zh;q=0.9",
        "Connection": "keep-alive",
        "Cookie":"BAIDUID=47B9867DEBA6B652903C4975C010AB3F:FG=1; BIDUPSID=47B9867DEBA6B652903C4975C010AB3F; PSTM=1554868710; BD_UPN=12314753; BDORZ=AE84CDB3A529C0F8A2B9DCDD1D18B695; H_WISE_SIDS=130593_125703_128701_130792_125696_130163_120129_131381_128879_118882_118864_118838_118819_118793_130763_131649_131577_131535_131534_131530_130222_131295_131246_129565_107317_131392_130120_131517_131239_131195_130350_117431_129649_127027_130689_128967_131036_130569_129838_130990_129479_129644_124802_131423_131467_130716_110085_127969_131506_123289_131210_131296_127317_128200_131549_130595_131264_131262_128604_131458_128806; delPer=0; BD_CK_SAM=1; BDUSS=IzekZDay1FUHJJclIxNTVVQVpuZE5-NUtBLXVWdzREdk9SRHkzc2MwSWtWLTVjSVFBQUFBJCQAAAAAAAAAAAEAAACWA4Z6gVfs4buou~DYvAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACTKxlwkysZcRl; bdindexid=g296e3ba116gv1ulcnasoo8gt0; SE_LAUNCH=5%3A25941955; PSINO=5; BD_HOME=1; BDRCVFR[feWj1Vr5u3D]=I67x6TjHwwYf0; H_PS_PSSID=1466_21117_28721_28832_28585_26350_28604_28890; H_PS_645EC=c576SDj%2FVTd6i0UC7abuVnm0w%2Bi370%2F7VhDK9yUMJlg6A%2BE0Vg1mVFzXbHCRTajZ7D%2Bv; sug=3; sugstore=0; ORIGIN=0; bdime=0",
        "Host": "index.baidu_index.com",
        "Referer": "http://index.baidu.com/v2/main/index.html",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36",
        "X-Requested-With": "XMLHttpRequest"
    }
    req = requests.get(url=url, headers=headers, timeout=5)
    html_doc = req.text
    print(html_doc)


if __name__ == '__main__':
    # for i in range(1, 10):
    get('php从入门到精通')
    get('java开发工具')
    get('matlab好学吗')
