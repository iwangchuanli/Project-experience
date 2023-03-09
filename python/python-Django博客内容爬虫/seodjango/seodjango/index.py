import random
import socket

import requests
from bs4 import BeautifulSoup
from django.http.response import HttpResponse
from django.shortcuts import render_to_response
from django.http.request import HttpRequest
import re, json
import MySQLdb


def test(request):
    context = {}
    return render_to_response('test.html', context)


# index 页面控制
def index(request):
    word = request.GET['word']
    context = {}
    context['word'] = word
    print(context)
    return render_to_response('index.html', context)


# 爬虫接口控制器
def crawler(request=HttpRequest()):
    word = request.GET['word']
    word_list = page_parse(word)
    php_flag = page_html(word)
    index_info = get_index(word)
    context = {}
    # context['child_items'] = word + "456"
    data = {'keyword': word_list[0],
            'relevant_word': word_list[1],
            'php_flag': php_flag,
            'baidu_index': index_info,
            }
    data = json.dumps(data, ensure_ascii=False)
    return HttpResponse(data, content_type="application/json")


# 百度相关搜索页面解析方法
def page_parse(keyword):
    socket.setdefaulttimeout(10)  # 设置全局超时时间
    url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd=" + keyword \
          + "&rsv_pq=d0e6cce80006a4d2&rsv_t" \
            "=f95aLCNgxPsct8UBd2K9OjQpiKh9eIOEdto4esxyIT3EoDGNzBIiwNx3Y4s&rqlang=cn&rsv_enter=0"
    relevant_search = []
    full_list = []
    while True:
        try:
            soup = normal_spider(url)
            if soup != False:
                links = soup.select('th > a')
                for a in links:
                    relevant_search.append(a.get_text())
                full_list = [keyword, relevant_search]
                # print(keyword, "---完毕")
                if len(relevant_search) > 0:
                    return full_list
        except Exception as e:
            print(keyword, '--------------------------处理 失 败')
            print(e)


# 数据库处理方法
def getdata(select_sql):
    db = MySQLdb.connect("127.0.0.1", "root", "123456", 'db_seo', charset='utf8')
    cursor = db.cursor()
    try:
        cursor.execute(select_sql)
        results = cursor.fetchall()
        return results
    except Exception as e:
        print(e)
    db.close()


# 普通爬虫公共方法
def normal_spider(url):
    socket.setdefaulttimeout(10)  # 设置全局超时时间
    i = 0
    while True:
        user_agents = [
            'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11',
            'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)',
            'Mozilla/5.0 (compatible; Konqueror/3.5; Linux) KHTML/3.5.5 (like Gecko) (Kubuntu)',
            'Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.0.12) Gecko/20070731 Ubuntu/dapper-security Firefox/1.5.0.12',
            "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.7 (KHTML, like Gecko) Ubuntu/11.04 Chromium/16.0.912.77 Chrome/16.0.912.77 Safari/535.7",
            "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0 "]
        user_agent = random.choice(user_agents)
        headers = {
            'User-Agent': user_agent,
            'Accept-Encoding': 'gzip'}
        try:
            req = requests.get(url=url, headers=headers, timeout=5)
            html_doc = req.text
            soup = BeautifulSoup(html_doc, "html.parser")
            return soup
        except Exception as e:
            print(e)
            print("重新进行尝试连接")
            i += 1
    return False


# php中文网检查方法
def page_html(keyword):
    # print('\n当前关键字 : ' + keyword)
    socket.setdefaulttimeout(10)  # 设置全局超时时间 学会php可以干什么 php是什么
    url = "http://m.baidu.com/s?word=" + keyword
    user_agents = [
        'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11',
        'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)',
        'Mozilla/5.0 (compatible; Konqueror/3.5; Linux) KHTML/3.5.5 (like Gecko) (Kubuntu)',
        'Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.0.12) Gecko/20070731 Ubuntu/dapper-security Firefox/1.5.0.12',
        "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.7 (KHTML, like Gecko) Ubuntu/11.04 Chromium/16.0.912.77 Chrome/16.0.912.77 Safari/535.7",
        "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0 "]
    user_agent = random.choice(user_agents)
    headers = {
        'User-Agent': user_agent,
        'Accept-Encoding': 'gzip'}
    try:
        req = requests.get(url=url, headers=headers)
        html_doc = req.text
        soup = BeautifulSoup(html_doc, "html.parser")
        titles = soup.select('article > header > div > a')
        links = soup.select('span.c-color-gray')
        # links2 = soup.select('div.c-showurl c-line-clamp1')
        # print("网页解析完毕")
        page_list = []
        php_list = ['php中文网', 'PHP中文网', 'm.php.cn']
        for temp in titles:
            # print(temp.get('aria-roledescription'), temp.get_text())
            page_list.append(temp.get_text())
        for temp in links:
            page_list.append(temp.get_text())
        # print(page_list)
        for flag in php_list:
            for temp in page_list:
                if temp.find(flag) != -1:
                    # print(keyword + "        " + temp, '           ++++++++++++++++++++++++++++')
                    return True
        return False
    except Exception as e:
        print(e)
        print("重新进行尝试连接")
        page_html(keyword)


# 百度指数的爬取
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
        return info
    except Exception as e:
        print(e)
        get_index(keyword)
