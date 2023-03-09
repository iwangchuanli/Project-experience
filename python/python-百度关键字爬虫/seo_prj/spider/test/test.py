import json
import random
import socket

import requests
from bs4 import BeautifulSoup


def get_index(keyword):
    start_date = "2019-01-01"
    end_date = "2019-05-01"
    url = "http://index.baidu.com/api/SearchApi/index?word=" + keyword \
          + "&area=0&startDate=" + start_date + "&endDate=" + end_date
    headers = {
        "Accept": "application / json, text / plain, * / *",
        "Accept-Encoding": "gzip, deflate",
        "Accept-Language": "zh-CN,zh;q=0.9",
        "Connection": "keep-alive",
        "Cookie": "BAIDUID=47B9867DEBA6B652903C4975C010AB3F:FG=1; BIDUPSID=47B9867DEBA6B652903C4975C010AB3F; PSTM=1554868710; BDUSS=IzekZDay1FUHJJclIxNTVVQVpuZE5-NUtBLXVWdzREdk9SRHkzc2MwSWtWLTVjSVFBQUFBJCQAAAAAAAAAAAEAAACWA4Z6gVfs4buou~DYvAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACTKxlwkysZcRl; bdshare_firstime=1556603263799; SIGNIN_UC=70a2711cf1d3d9b1a82d2f87d633bd8a03073772022; bdindexid=pbgt1q0dv1tqteqphcc3r10s96; Hm_lvt_d101ea4d2a5c67dab98251f0b5de24dc=1556593941,1556603388,1556615384,1557365405; H_WISE_SIDS=130593_125703_128701_131824_125696_130163_120129_131381_128879_131602_131906_118882_118864_131401_118838_118819_118793_130763_131649_131577_131535_131534_131530_130222_131295_131871_131390_129565_107317_131796_131392_130120_130569_131195_130350_117431_131240_129649_127027_131436_131687_131036_129838_130990_129479_129644_124802_131467_130825_110085_131767_127969_131506_123289_131749_131298_127317_128200_131552_130595_131264_131262_128604_131458_128806; BDORZ=AE84CDB3A529C0F8A2B9DCDD1D18B695; delPer=0; PSINO=5; H_PS_PSSID=1466_21117_28721_28964_28832_28585_26350_28604; ZD_ENTRY=empty; Hm_lpvt_d101ea4d2a5c67dab98251f0b5de24dc=1557367184",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36",
        "X-Requested-With": "XMLHttpRequest"
    }
    info = []
    try:
        req = requests.get(url=url, headers=headers, timeout=5)
        html_doc = req.text
        # print(keyword, html_doc)
        py_json = json.loads(html_doc)
        status = py_json['status']
        if status == 0:
            word = py_json['data']['generalRatio'][0]['word']
            all = py_json['data']['generalRatio'][0]['all']
            pc = py_json['data']['generalRatio'][0]['pc']
            wise = py_json['data']['generalRatio'][0]['wise']
            info = [word, start_date, end_date, status, all['avg'], all['yoy'], all['qoq'], pc['avg'],
                    pc['yoy'],
                    pc['qoq'], wise['avg'], wise['yoy'], wise['qoq'], py_json['message'],
                    py_json['data']['uniqid']]
        else:
            info = []
            info = [keyword, start_date, end_date, status, py_json['message']]
        print(info)
    except Exception as e:
        print(e)
        print(info)  # 异常处理


if __name__ == '__main__':
    print(get_index('php'))

