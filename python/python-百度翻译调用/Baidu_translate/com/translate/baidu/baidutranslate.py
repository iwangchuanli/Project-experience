# -*- coding=utf-8 -*-

import requests
import js2py
import re


class BaiDuTranslateAPI(object):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36'
    }

    def __init__(self):
        super(BaiDuTranslateAPI).__init__()
        self.GetReady()

    def GetReady(self):
        url_index = 'https://www.baidu.com'
        self.session = requests.session()
        self.session.get(url=url_index, headers=self.headers)
        self.headers['Referer'] = url_index
        url_html = 'https://fanyi.baidu.com/translate?aldtype=16047&query=&keyfrom=baidu&smartresult=dict&lang=auto2zh'
        html = self.session.get(url=url_html, headers=self.headers)
        comm = re.compile('token: \'(\w+)\'')
        self.token = comm.search(html.text).group(1)
        self.headers['Referer'] = url_html

    def Get_Js(self):
        with open(r'fanyi.js', 'r', encoding='utf-8') as f:
            return f.read()

    def Translate(self, file):
        files = False
        file = str(file)
        for i in file:
            if '\u4e00' <= i.encode().decode('utf-8') <= '\u9fff':
                files = True
            else:
                files = False
            if files == False:
                break
        return files

    def BaiDu(self, file):
        run_js = js2py.EvalJs({})
        run_js.execute(self.Get_Js())
        sign = run_js.e(file)
        url_api = 'https://fanyi.baidu.com/v2transapi'
        is_it = self.Translate(file)
        if is_it:
            iia = 'zh'
            iib = 'en'
        else:
            iia = 'en'
            iib = 'zh'
        data = {
            'from': iia,
            'to': iib,
            'query': file,
            'transtype': 'realtime',
            'simple_means_flag': '3',
            'sign': sign,
            'token': self.token
        }
        html = self.session.post(url=url_api, headers=self.headers, data=data).json()
        return (html['trans_result']['data'][0]['dst'])

    def Start(self, file='i'):
        try:
            int(file)
            return file
        except:
            # self.zhunbei()
            files = self.BaiDu(file)
            return files


if __name__ == '__main__':
    a = BaiDuTranslateAPI()
    print(a.Start('i'))
    print(a.Start('love'))
    print(a.Start('you'))
