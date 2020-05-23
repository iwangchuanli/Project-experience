# -*- coding: utf-8 -*-
# @Author: Marte
# @Date:   2019-04-15 08:56:30
# @Last Modified by:   Marte
# @Last Modified time: 2019-04-15 08:59:59
import MySQLdb

# 打开数据库连接
db = MySQLdb.connect("127.0.0.1", "root", "123456", "original_article_database", charset='utf8' )
# 使用cursor()方法获取操作游标
cursor = db.cursor()
# 使用execute方法执行SQL语句
cursor.execute("SELECT VERSION()")
# 使用 fetchone() 方法获取一条数据
data = cursor.fetchone()
print ("Database version : %s " % data)
# 关闭数据库连接
db.close()