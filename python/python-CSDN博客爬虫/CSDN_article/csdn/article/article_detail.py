# 文章详情页解析
import random
import threading
import time

import MySQLdb
import requests
from bs4 import BeautifulSoup

from utils.myutils import sql_opt

ip_port = ["221.229.196.165:6419", "221.229.196.164:2593", "221.229.196.167:7670", "221.229.196.164:5394",
           "221.229.196.166:5635", "221.229.196.165:4816", "221.229.196.155:7382", "221.229.196.166:9686",
           "221.229.196.165:8420", "221.229.196.167:9853", "180.97.250.129:3307"]
proxies = {
    "https": random.choices(ip_port)[0],
}
user_agents = [
    "Mozilla/5.0 (Windows NT 10.0; WOW64)", 'Mozilla/5.0 (Windows NT 6.3; WOW64)',
    'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11',
    'Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko',
    'Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36',
    'Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729;\
     .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; rv:11.0) like Gecko)',
    'Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/3.0.1',
    'Mozilla/5.0 (Windows; U; Windows NT 5.1) Gecko/20070309 Firefox/2.0.0.3',
    'Mozilla/5.0 (Windows; U; Windows NT 5.1) Gecko/20070803 Firefox/1.5.0.12',
    'Opera/9.27 (Windows NT 5.2; U; zh-cn)',
    'Mozilla/5.0 (Macintosh; PPC Mac OS X; U; en) Opera 8.0',
    'Opera/8.0 (Macintosh; PPC Mac OS X; U; en)',
    'Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.12) Gecko/20080219 Firefox/2.0.0.12 Navigator/9.0.0.6',
    'Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Win64; x64; Trident/4.0)',
    'Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)',
    'Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; SLCC2; .NET CLR 2.0.50727;\
     .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0C; .NET4.0E)',
    'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Maxthon/4.0.6.2000\
     Chrome/26.0.1410.43 Safari/537.1 ',
    'Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; SLCC2;\
     .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729;\
      Media Center PC 6.0; InfoPath.2; .NET4.0C; .NET4.0E; QQBrowser/7.3.9825.400)',
    'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0 ',
    'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) \
    Chrome/21.0.1180.92 Safari/537.1 LBBROWSER',
    'Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)',
    'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) \
    Chrome/20.0.1132.11 TaoBrowser/3.0 Safari/536.11'
]
headers = {
    'User-Agent': random.choice(user_agents),
    'accept-language': 'zh-CN,zh;q=0.9',
    'Connection': 'close',
    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
    "cookie": "uuid_tt_dd=10_30807452010-1562302731635-401378; dc_session_id=10_1562302731635.837963; __yadk_uid=iow66dtsOWOlkRLZPRXJFHig8cw3DYkL; acw_tc=7b39758715628083298192212ea403848b1b2a343f8a6a80bb317c16dc4dd3; hasSub=true; TY_SESSION_ID=156cf825-84b5-4b93-919d-bcf31b378b49; SESSION=4f5ccd25-873d-464d-ae19-d22e27e1b493; acw_sc__v2=5d26e7758678409c08715de975acb2c1ced3708c; acw_sc__v3=5d26e77773b586c68bffb68f3ac0768337ad083a; dc_tos=pugwt8; c-login-auto=8",
}


# 爬虫
def spider(url, times=0):
    try:
        requests.packages.urllib3.disable_warnings()
        response = requests.get(url, headers=headers, timeout=20, verify=False)
        requests.adapters.DEFAULT_RETRIES = 5
        s = requests.session()
        s.keep_alive = False
        return response
    except Exception as e:
        times += 1
        print("爬虫异常:" + url + "原因-：" + str(e))
        if times > 6:
            return ""
        time.sleep(random.randint(0, 9))
        print("重新爬取:" + str(times) + "===" + url)
        spider(url, times)


# 文章详情内容存储
def article_content_insert(article_id, content, database_num):
    database = "db_csdn_article_list_" + str(database_num)
    table_list = "t_article_list_" + str(database_num)
    table_content = "t_article_content_" + str(database_num)
    content_insert_sql = "replace into " + table_content + "(article_id,content) values('%s','%s')" \
                         % (article_id, content)
    db = MySQLdb.connect("localhost", "root", "123456789", database, charset='utf8')
    cursor = db.cursor()
    try:
        print("文章数据更新:" + "---" + str(article_id))
        cursor.execute(content_insert_sql)
        db.commit()
    except Exception as e:
        print("文章数据更新sql异常：" + "---" + str(article_id) + str(e))
        db.rollback()
    db.close()


# 文章主体获取
def blog_detail_page_parse(username, article_id, database_num):
    url = "https://blog.csdn.net/" + username + "/article/details/" + str(article_id)
    response = spider(url)
    try:
        web_html = response.text
        # print(web_html)
        soup = BeautifulSoup(web_html, "lxml")
        content_div = soup.select("div#article_content > div#content_views")
        content = ""
        if content_div:
            content = str(content_div[0]).replace("\'", "\"").replace("/", "//")
        if len(content) > 1:
            article_content_insert(article_id, content, database_num)
        else:
            print("文章信息处理失败：" + username + "---" + str(article_id))
            error_handler(article_id, database_num)
    except Exception as e:
        print("文章信息处理失败：" + username + "---" + str(article_id) + str(e))
        error_handler(article_id, database_num)


# 文章内容处理失败
def error_handler(article_id, database_num):
    database = "db_csdn_article_list_" + str(database_num)
    table_list = "t_article_list_" + str(database_num)
    list_update_sql = "update " + table_list + " set flag='0' where article_id='" + str(article_id) + "\'"
    db = MySQLdb.connect("localhost", "root", "123456789", database, charset='utf8')
    cursor = db.cursor()
    try:
        print("文章数据更新:" + "---" + str(article_id))
        cursor.execute(list_update_sql)
        db.commit()
    except Exception as e:
        print("文章数据更新sql异常：" + "---" + str(article_id) + str(e))
        db.rollback()
    db.close()


def get_database_num():
    for x in range(1, 9):
        database = "db_csdn_article_list_" + str(x)
        table_list = "t_article_list_" + str(x)
        db = MySQLdb.connect("localhost", "root", "123456789", database, charset='utf8')
        cursor = db.cursor()
        select_count_sql = "SELECT COUNT(id) from " + table_list + " where flag='0'"
        cursor.execute(select_count_sql)
        results = cursor.fetchall()
        if results[0][0] < 5000000:
            db.close()
            return x


# 需要爬取文章信息提取
def article_extr():
    database_num = get_database_num()
    database = "db_csdn_article_list_" + str(database_num)
    table_list = "t_article_list_" + str(database_num)
    print("数据库" + str(database))
    if database_num:
        db = MySQLdb.connect("localhost", "root", "123456789", database, charset='utf8')
        cursor = db.cursor()
        article_select_sql = "SELECT article_id,username FROM `" + table_list + "` where flag = '0' limit 10"
        article_update_sql = "update " + table_list + " set flag ='1' where flag = '0' limit 5"
        cursor.execute(article_select_sql)
        article_results = cursor.fetchall()
        try:
            print("---文章数据提取")
            cursor.execute(article_update_sql)
            db.commit()
        except Exception as e:
            print("---文章数据提取sql执行异常" + + str(e))
            db.rollback()
        db.close()
        threads = []
        print("线程开启")
        for row in article_results:
            if row[1] == '0':
                continue
            else:
                thread = threading.Thread(target=blog_detail_page_parse, args=[row[1], row[0], database_num])
                threads.append(thread)
                thread.start()
        for thread in threads:
            thread.join()
    else:
        article_extr()


if __name__ == '__main__':
    i = 0
    while True:
        start_time = time.time()
        print("=====================================" + str(i) + "======================")
        try:
            article_extr()
        except Exception as e:
            print(str(e))
            time.sleep(60)
        i += 1
        print("=====================================" + str(time.time() - start_time) + "======================")
        time.sleep(10)
