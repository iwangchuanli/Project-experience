import socket
import requests
from bs4 import BeautifulSoup
import urllib.request
import os
import random
import time
import sql_operation
import baidudetector
import ast

# 随机获取代理ip-----------------------------------------------------------------------------------------------
def getproxyip(ip_file):
    fo = open(ip_file, 'r')
    proxys = fo.read().split('\n')
    proxy = ast.literal_eval(random.choice(proxys))
    print(proxy)
    fo.close()
    return proxy
    pass

def page_html(i):
    print("              get  page       " + str(i) + "        ---------------------------")
    socket.setdefaulttimeout(10)  # 设置全局超时时间
    url = "https://stackoverflow.com/questions/tagged/php?sort=votes&page=" + str(i) + "&pagesize=50"
    '''
    https://stackoverflow.com/questions/tagged/sql?sort=votes&page=2&pagesize=50
    '''
    user_agents = [
        'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11',
        'Opera/9.25 (Windows NT 5.1; U; en)',
        'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)',
        'Mozilla/5.0 (compatible; Konqueror/3.5; Linux) KHTML/3.5.5 (like Gecko) (Kubuntu)',
        'Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.0.12) Gecko/20070731 Ubuntu/dapper-security Firefox/1.5.0.12',
        'Lynx/2.8.5rel.1 libwww-FM/2.14 SSL-MM/1.4.1 GNUTLS/1.2.9',
        "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.7 (KHTML, like Gecko) Ubuntu/11.04 Chromium/16.0.912.77 Chrome/16.0.912.77 Safari/535.7",
        "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0 "]
    user_agent = random.choice(user_agents)
    headers = {
        'User-Agent': user_agent,
        'Accept-Encoding': 'gzip'}
    try:
        proxy_support = urllib.request.ProxyHandler(getproxyip('proxy_ip_stackoverflow.txt'))
        opener = urllib.request.build_opener(proxy_support)
        opener.addheaders = [headers]
        urllib.request.install_opener(opener)
        html_doc = urllib.request.urlopen(url).read()
        soup = BeautifulSoup(html_doc, "html.parser")
        titles = soup.select('h3 > a')
        votes = soup.select("span.vote-count-post > strong")
        print("网页解析完毕")
        page_list = []
        for i in range(2, len(titles)):
            item = []
            url = titles[i].get('href').replace('\'', '\\\'').replace('\"', '\\\"')
            question_id = url.split("/")[2]
            item.append(question_id)
            item.append(titles[i].get_text().replace('\'', '\\\'').replace('\"', '\\\"'))
            ques_url = "https://stackoverflow.com" + url
            item.append(ques_url)
            item.append(votes[i - 2].get_text())
            page_list.append(item)
        tosql(page_list)
    except Exception as e:
        print(e)
        print("重新进行尝试连接")
        page_html(i)


# shuju
def tosql(page):
    db = sql_operation.getcon()
    for item in page:
        replace_sql = "Replace INTO `t_stackoverflow_question` " \
                      "(`question_id`,`title`, `url`, `votes`, `flag`) " \
                      "VALUES ('%s','%s', '%s', '%s','0')" \
                      % (item[0], item[1], item[2], item[3])
        sql_operation.baseoperation(db, replace_sql)
    sql_operation.closecon(db)


def timer(n):
    '''''
    每n秒执行一次
    '''
    i = 1
    while True:
        print(time.strftime('%Y-%m-%d %X', time.localtime()))
        i += 1
        # page_html(i)  # 此处为要执行的任务
        updatetosql()
        time.sleep(n)


def item_html(url):
    user_agents = [
        'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11',
        'Opera/9.25 (Windows NT 5.1; U; en)',
        'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)',
        'Mozilla/5.0 (compatible; Konqueror/3.5; Linux) KHTML/3.5.5 (like Gecko) (Kubuntu)',
        'Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.0.12) Gecko/20070731 Ubuntu/dapper-security Firefox/1.5.0.12',
        'Lynx/2.8.5rel.1 libwww-FM/2.14 SSL-MM/1.4.1 GNUTLS/1.2.9',
        "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.7 (KHTML, like Gecko) Ubuntu/11.04 Chromium/16.0.912.77 Chrome/16.0.912.77 Safari/535.7",
        "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0 "]
    user_agent = random.choice(user_agents)
    headers = {
        'User-Agent': user_agent,
        'Accept-Encoding': 'gzip'}
    try:
        proxy_support = urllib.request.ProxyHandler(getproxyip('proxy_ip_stackoverflow.txt'))
        opener = urllib.request.build_opener(proxy_support)
        opener.addheaders = [headers]
        urllib.request.install_opener(opener)
        html_doc = urllib.request.urlopen(url).read()
        # req = requests.get(url=url, headers=headers)
        # html_doc = req.text
        soup = BeautifulSoup(html_doc, "html.parser")
        times = soup.select("time")
        views = soup.select("p.label-key > b")
        active_str = str(views[2])
        active = active_str[active_str.find("title=\"") + 7:active_str.find("Z")]
        answers = soup.select("#answers-header > div > h2 >span")
        question_content = soup.select("div.post-text")
        tags = soup.select("#question > div.post-layout > div.postcell.post-layout--right > "
                           "div.post-taglist.grid.gs4.gsy.fd-column > div >a")
        title = soup.select("h1 >a")
        tags_str = ""
        item = []
        for tag in tags:
            tags_str += tag.get_text() + ","
        answer_contetnts = []
        for i in range(1, len(question_content)):
            answers_item = str(question_content[i]).replace('\'', '\\\'').replace('\"', '\\\"')
            answer_contetnts.append(answers_item)
        for i in range(len(times)):
            if len(times[i].get_text()) > 1:
                asked_time = times[i].get("datetime").replace("T", " ")
        # title  views  answersnum   asked_time  tag_str  active_time   quest_content_ text  answer_content_list
        item.append(title[0].get_text())
        item.append(views[1].get_text().split(" ")[0])
        item.append(answers[0].get_text())
        item.append(asked_time)
        item.append(tags_str)
        item.append(active)
        item.append(str(question_content[0]).replace('\'', '\\\'').replace('\"', '\\\"'))
        item.append(answer_contetnts)
        # print(item)
        return item
    except Exception as e:
        print(e)
        print("重新尝试连接")
        item_html(url)



def updatetosql():
    db = sql_operation.getcon()
    select_sql = "SELECT title,url FROM t_stackoverflow_question WHERE flag = '0' Order By rand() limit 10"
    results = sql_operation.baseselect(db, select_sql)
    for row in results:
        url = row[1]
        item = item_html(url)
        ansers_text = "[split]".join(item[7])
        updatesql = "UPDATE `t_stackoverflow_question` " \
                    "SET `tags`='%s', `views`='%s', `answers_num`='%s', `asked_time`='%s', `last_active_time`='%s', `question_content`='%s', `answers_contetnt`='%s' , `flag` = '1'" \
                    "WHERE (`title`='%s') " \
                    % (item[4], item[1], item[2], item[3], item[5], item[6], ansers_text, item[0],)
        # print(updatesql)
        sql_operation.baseoperation(db, updatesql)
    sql_operation.closecon(db)


if __name__ == '__main__':
    timer(3)
    # item_html("https://stackoverflow.com/questions/1795025/what-are-the-differences-in-die-and-exit-in-php01")
    # page_html(3)
