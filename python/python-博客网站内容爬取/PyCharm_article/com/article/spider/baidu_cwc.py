# coding:utf8
# 百度知道长尾关键词挖掘工具，挖掘关键词结果会自动生成并导入一个以关键词为名称的文本文件
import sys

# sys.setdefaultencoding('utf-8')
import requests, re, os, time

# from requests.packages.urllib3.exceptions import InsecureRequestWarning
#
# requests.packages.urllib3.disable_warnings(InsecureRequestWarning)
headers = {

    "User-Agent": "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0"
}


def get_html(url):
    while 1:
        try:
            r = requests.get(url, verify=False)
            r.encoding = "gbk"
            html = r.text
            return html
        except:
            pass


def get_info(url):
    html = get_html(url)
    html = re.sub('<em>', '', html)
    html = re.sub('</em>', '', html)
    titles = re.findall('target="_blank" class="ti">([\s\S]*?)</a>\s+</dt>', html)
    f = open('%s.txt' % keyword.decode('utf8').encode('gbk'), 'a')
    for title in titles:
        if keyword in str(title):
            f.write(title + '\n')
            f.close
        else:
            continue


if __name__ == "__main__":
    print(
        '''《百度知道长尾关键词挖掘工具》
       使用方法：在下方输入一个关键词，然后按 回车 继续即可
       ''')
    # keyword = raw_input('请输入一个关键词,然后按 回车 继续:')
    keyword = "php"
    url1 = "https://zhidao.baidu.com/search?pn=0&word=%s" % keyword
    html1 = get_html(url1)
    id1 = re.findall(u'<span class="f-lighter lh-22">共(.*?)条结果</span>', html1)[0]
    id1 = re.sub(',', '', id1)
    id2 = int(id1) / 10

    if id2 >= 75:
        for id in range(0, 77):  # 共76页
            num = id * 10

            url = "http://zhidao.baidu.com/search?pn=%s&word=%s" % (num, keyword)
            # print '正在挖掘',url,'第',id+1,'页的关键词'
            print
            ('正在挖掘第', id + 1, '页的关键词')
            get_info(url)

        print
        ('挖掘完成,所有关键词已放入  %s.txt  文件中') % (keyword)

        print("""
           
                   3秒后自动打开此文件""")
        time.sleep(3)
        os.system('%s.txt' % keyword.decode('utf8').encode('gbk'))

    elif 1 < id2 < 75:

        for id in range(0, id2):  # 共76页
            # for id in range(0,77): #共76页
            num = id * 10

            url = "https://zhidao.baidu.com/search?pn=%s&word=%s" % (num, keyword)
            # print '正在挖掘',url,'第',id+1,'页的关键词'
            print
            '正在挖掘第', id + 1, '页的关键词'
            get_info(url)

        print('挖掘完成,所有关键词已放入  %s.txt  文件中') % (keyword)

        print("""

        3秒后自动打开此文件""")
        time.sleep(3)
        os.system('%s.txt' % keyword.decode('utf8').encode('gbk'))

    else:
        print('信息量太少')
