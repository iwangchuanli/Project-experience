# -*- coding=utf-8 -*-

import threading
import time, requests, random, sys, codecs
import MySQLdb
import math
from bs4 import BeautifulSoup

# 代理
from utils.myutils import spider


# 数据库更新语句执行操作
def sql_opt(sql):
    db = MySQLdb.connect("localhost", "root", "123456789", "db_csdn_user",
                         charset='utf8')
    cursor = db.cursor()
    try:
        cursor.execute(sql)
        db.commit()
    except Exception as e:
        print("----sql_opt数据库语句执行异常" + str(e))
        db.rollback()
    db.close()


# 数据库分表
def sjkfb(table):
    db = MySQLdb.connect("localhost", "root", "123456789", "db_csdn_user",
                         charset='utf8')
    cursor = db.cursor()
    for index in range(1, 5):
        table_name = table + "_" + str(index)
        table_check_sql = "select COUNT(id) from " + table_name
        cursor.execute(table_check_sql)
        check_results = cursor.fetchall()
        if check_results[0][0] < 2000000:  # 每200W数据分一次表
            return table_name
    db.close()


# 用户信息的插入
def user_insert(username, insert_base, insert_blog, insert_relation, user_list):
    db = MySQLdb.connect("localhost", "root", "123456789", "db_csdn_user",
                         charset='utf8')
    cursor = db.cursor()
    try:
        print("---用户数据插入:" + username)
        cursor.execute(insert_base)
        cursor.execute(insert_blog)
        cursor.execute(insert_relation)
        db.commit()
    except Exception as e:
        print("---用户信息插入sql执行异常：" + username + str(e))
        db.rollback()
    user_sql = "select username from t_user_list"
    cursor.execute(user_sql)
    username_results = cursor.fetchall()
    for row in username_results:
        if row[0] in user_list:
            user_list.remove(row[0])
    for user in user_list:
        insert_sql = "INSERT INTO `t_user_list` (`username`) VALUES ('%s')" % (user)
        try:
            print("--用户列表插入:" + user)
            cursor.execute(insert_sql)
            db.commit()
        except Exception as e:
            print("--用户列表插入sql执行异常：" + user + str(e))
            db.rollback()
    db.close()


# me个人页面解析
def me_page_parse(username, times=0):
    url = "https://me.csdn.net/blog/" + username
    times += 1
    if (times > 6):
        error_sql = "UPDATE `t_user_list` SET `flag`='error' WHERE (`username`='%s')" % (username)
        sql_opt(error_sql)
        return
    response = spider(url)
    if response:
        try:
            web_html = response.text
            soup = BeautifulSoup(web_html, 'lxml')
            nickname = soup.select("p.lt_title")[0].get_text().replace("\n", "").replace(" ", "").replace("\'", "\\'")[
                       1:]
            description = soup.select("p.description_detail")
            if len(description) > 0:
                description = description[0].get_text().replace("\n", "").replace(" ", "").replace("\'", "\\'")
            else:
                description = "无"
            tab_item = soup.select("span.count ")
            rank = str(soup.select("div.me_chanel_det > div.access > span")[1].get_text()) \
                .replace("\n", "").replace(" ", "")
            if len(tab_item) >= 6:
                blog = tab_item[0].get_text().replace("\n", "").replace(" ", "")
                download = tab_item[1].get_text().replace("\n", "").replace(" ", "")
                bbs = tab_item[2].get_text().replace("\n", "").replace(" ", "")
                blink = tab_item[3].get_text().replace("\n", "").replace(" ", "")
                ask = tab_item[4].get_text().replace("\n", "").replace(" ", "")
                myfavorite = tab_item[5].get_text().replace("\n", "").replace(" ", "")
            fans = soup.select("div.fans > a > span")[0].get_text().replace("\n", "").replace(" ", "")
            follow = soup.select("div.att > a > span")[0].get_text().replace("\n", "").replace(" ", "")
            user_base_insert_sql = "INSERT INTO `" + sjkfb("t_user_base") + "` (`username`, `nickname`, `description`" \
                                                                            ", `download`, `bbs`, `blink`, `ask`,`myfavorite`) " \
                                                                            "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s','%s')" \
                                   % (username, nickname, description, download, bbs, blink, ask, myfavorite)
            user_blog_insert_sql = "INSERT INTO `" + sjkfb("t_user_blog") + "` (`username`, `blog`, `rank`) " \
                                                                            "VALUES ('%s', '%s', '%s')" \
                                   % (username, blog, rank)
            user_relation_insert_sql = "INSERT INTO `" + sjkfb("t_user_relation") + "` (`username`, `fans`, `follow`) " \
                                                                                    "VALUES ('%s', '%s', '%s')" \
                                       % (username, fans, follow)
            new_user_list = ["csdnnews"]
            if int(fans) > 0:
                new_user_list.extend(fans_page_parse(username))
            if int(follow) > 0:
                new_user_list.extend(follow_page_parse(username))
            new_user_list = list(set(new_user_list))
            user_insert(username, user_base_insert_sql, user_blog_insert_sql, user_relation_insert_sql, new_user_list)
        except Exception as e:
            print("--用户信息解析处理失败异常：" + username + str(e))
            error_sql = "UPDATE `t_user_list` SET `flag`='error' WHERE (`username`='%s')" % (username)
            sql_opt(error_sql)
    else:
        me_page_parse(username, times)


# fans页面解析
def fans_page_parse(username, times=0):
    url = "https://me.csdn.net/fans/" + username
    times += 1
    if (times > 6):
        error_sql = "UPDATE `t_user_list` SET `flag`='error' WHERE (`username`='%s')" % (username)
        sql_opt(error_sql)
        return
    response = spider(url)
    if response:
        try:
            web_html = response.text
            soup = BeautifulSoup(web_html, "lxml")
            fans_page_list = soup.select("p.user_name > a")
            fans_list = []
            if len(fans_page_list) > 0:
                for fan in fans_page_list:
                    fansname = str(fan['href'])
                    fansname = fansname[fansname.rfind("/") + 1:]
                    fans_list.append(fansname)
            return fans_list
        except Exception as e:
            print("---粉丝页面解析异常" + username + str(e))
    else:
        fans_page_parse(username, times)


# follow页面解析
def follow_page_parse(username, times=0):
    url = "https://me.csdn.net/follow/" + username
    times += 1
    if (times > 6):
        error_sql = "UPDATE `t_user_list` SET `flag`='error' WHERE (`username`='%s')" % (username)
        sql_opt(error_sql)
        return
    response = spider(url)
    if response:
        try:
            web_html = response.text
            soup = BeautifulSoup(web_html, "lxml")
            follow_page_list = soup.select("p.user_name > a")
            follow_list = []
            if len(follow_page_list) > 0:
                for fan in follow_page_list:
                    followname = str(fan['href'])
                    followname = followname[followname.rfind("/") + 1:]
                    follow_list.append(followname)
            return follow_list
        except Exception as e:
            print("---关注页面解析异常" + username + str(e))
    else:
        follow_page_parse(username, times)


# 方法弃置不用
def cf_check():
    db = MySQLdb.connect("localhost", "root", "123456789", "db_csdn_user",
                         charset='utf8')
    cursor = db.cursor()
    cf_check_sql = "SELECT COUNT(*) FROM(select id from t_user_list WHERE flag = '0' GROUP BY username HAVING COUNT(username) > 1) alias"
    cd_handler = "DELETE FROM t_user_list WHERE id in (SELECT id FROM(select id from t_user_list WHERE flag = '0' GROUP BY username HAVING COUNT(username) > 1) alias)"
    cursor.execute(cf_check_sql)
    check_results = cursor.fetchall()
    while check_results[0][0] != 0:
        try:
            cursor.execute(cd_handler)
            db.commit()
        except Exception as e:
            print(str(e))
            db.rollback()
        cursor.execute(cf_check_sql)
        check_results = cursor.fetchall()
    db.close()


# 用户信息爬虫控制
def user_controleer():
    # cf_check()
    # print("重复检测结束")
    db = MySQLdb.connect("localhost", "root", "123456789", "db_csdn_user",
                         charset='utf8')
    cursor = db.cursor()
    user_select_sql = "SELECT username FROM `t_user_list` WHERE flag = '0' limit 20"
    user_update_sql = "UPDATE `t_user_list` SET `flag`='1' WHERE flag = '0' limit 20"  # 每次从数据库中取完然后更新数据库
    cursor.execute(user_select_sql)
    username_results = cursor.fetchall()
    try:
        print("-用户提取更新", username_results)
        cursor.execute(user_update_sql)
        db.commit()
    except Exception as e:
        print("-用户提取更新sql执行异常：" + str(e))
        db.rollback()
    db.close()
    threads = []
    for row in username_results:
        thread = threading.Thread(target=me_page_parse, args=[row[0]])
        threads.append(thread)
        thread.start()
    for thread in threads:
        thread.join()


if __name__ == '__main__':
    i = 0
    while True:
        start_time = time.time()
        print("=====================================" + str(i) + "======================")
        try:
            user_controleer()
        except Exception as e:
            print(str(e))
            time.sleep(60)
        i += 1
        print("=====================================" + str(time.time() - start_time) + "======================")
        time.sleep(5)
