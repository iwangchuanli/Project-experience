'''

https://blog.csdn.net/xufive/phoenix/article/list/2
'''
import json
import threading
import time

import MySQLdb
import math

from utils import myutils


# 分库分表  500W分一次库
def sqlfkfb():
    for x in range(1, 9):
        database = "db_csdn_article_list_" + str(x)
        table_list = "t_article_list_" + str(x)
        db = MySQLdb.connect("localhost", "root", "123456789", database, charset='utf8')
        cursor = db.cursor()
        select_count_sql = "SELECT COUNT(id) from " + table_list
        cursor.execute(select_count_sql)
        results = cursor.fetchall()
        if results[0][0] < 5000000:
            db.close()
            return x


# 文章信息插入
def article_insert(article_list, database_num):
    database = "db_csdn_article_list_" + str(database_num)
    table_list = "t_article_list_" + str(database_num)
    table_base = "t_article_base_" + str(database_num)
    table_summary = "t_article_summary_" + str(database_num)
    db = MySQLdb.connect("localhost", "root", "123456789", database, charset='utf8')
    cursor = db.cursor()
    if article_list:
        for article in article_list:
            article_id = article['ArticleId']
            title = str(article['Title']).replace(" ", "").replace("\'", "\'\'")
            summary = str(article['Description']).replace(" ", "") \
                .replace("\'", "\\'").replace("\r\n", "").replace("\n", "")
            read_count = article['ViewCount']
            comment_count = article['CommentCount']
            public_time = article['PostTime']
            username = article['UserName']
            article_list = "insert into " + table_list + " (article_id,username) values('%s','%s')" \
                           % (article_id, username)
            article_base = "insert into " + table_base + " (article_id, public_time, read_count, comment_count) " \
                                                         "values ('%s','%s','%s','%s')" \
                           % (article_id, public_time, read_count, comment_count)
            article_summary = "insert into " + table_summary + " (article_id, title, summary) values ('%s','%s','%s')" \
                              % (article_id, title, summary)
            try:
                print("---文章数据插入:" + username + "===" + title)
                cursor.execute(article_list)
                cursor.execute(article_base)
                cursor.execute(article_summary)
                db.commit()
            except Exception as e:
                print("---文章数据插入sql执行异常：" + username + "===" + title + str(e))
                db.rollback()
    db.close()


# 每页文章信息获取
def user_blog_page_parse(username, index, database_num, times=0):
    url = "https://blog.csdn.net/" + username + "/phoenix/article/list/" + str(index + 1)
    times += 1
    if times > 6:
        return False
    response = myutils.spider(url)
    if response:
        try:
            page_results = json.loads(response.text)
            if page_results['status']:
                results = page_results['data']['article_list']
                if results:
                    article_insert(results, database_num)
            else:
                print(username + "==页数" + str(index + 1) + "文章信息获取失败重试")
                user_blog_page_parse(username, index, times)
        except Exception as e:
            # print("页文章信息解析error:--" + username + "==页数" + str(index + 1) + str(e))
            user_blog_page_parse(username, index, times)
    else:
        print(username + "==页数" + str(index + 1) + "访问失败重试")
        user_blog_page_parse(username, index, times)


# 多少文章页控制
def user_page_handler(username, blog_num, database_num):
    if blog_num[1:2] == "k":
        blog_num = blog_num[0:1] + "000"
    if blog_num[1:2] == "w":
        blog_num = blog_num[0:1] + "0000"
    page_num = math.ceil(int(blog_num) / 20)
    if page_num < 20:
        threads = []
        for index in range(page_num):
            thread = threading.Thread(target=user_blog_page_parse, args=[username, index, database_num, 0])
            threads.append(thread)
            thread.start()
        for thread in threads:
            thread.join()
    else:
        for index in range(page_num):
            user_blog_page_parse(username, index, 0)


# 数据库提取用户
def username_extr():
    database_num = sqlfkfb()
    print("数据库" + str(database_num))
    if database_num:
        db = MySQLdb.connect("localhost", "root", "123456789", "db_csdn_user", charset='utf8')
        cursor = db.cursor()
        user_select_sql = "SELECT username,blog FROM `t_user_blog_1` where flag = '0' limit 20"
        user_update_sql = "update t_user_blog_1 set flag ='1' where flag = '0' limit 20"
        cursor.execute(user_select_sql)
        user_results = cursor.fetchall()
        try:
            print("---用户数据提取")
            cursor.execute(user_update_sql)
            db.commit()
        except Exception as e:
            print("---用户数据提取sql执行异常" + + str(e))
            db.rollback()
        db.close()
        threads = []
        print("线程开启")
        for row in user_results:
            if row[1] == '0':
                continue
            else:
                thread = threading.Thread(target=user_page_handler, args=[row[0], row[1], database_num])
                threads.append(thread)
                thread.start()
        for thread in threads:
            thread.join()
    else:
        username_extr()


if __name__ == '__main__':
    i = 0
    while True:
        start_time = time.time()
        print("=====================================" + str(i) + "======================")
        try:
            username_extr()
        except Exception as e:
            print(str(e))
            time.sleep(60)
        i += 1
        print("=====================================" + str(time.time() - start_time) + "======================")
        time.sleep(5)
