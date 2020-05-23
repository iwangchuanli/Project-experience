# -*- coding: utf-8 -*-
# @Author: Marte
# @Date:   2019-04-15 08:56:30
# @Last Modified by:   Marte
# @Last Modified time: 2019-04-15 08:59:59
import MySQLdb


def contest():
    # 打开数据库连接
    db = MySQLdb.connect("127.0.0.1", "root", "123456", "original_article_database", charset='utf8')
    # 使用cursor()方法获取操作游标
    cursor = db.cursor()
    # 使用execute方法执行SQL语句
    cursor.execute("SELECT VERSION()")
    # 使用 fetchone() 方法获取一条数据
    data = cursor.fetchone()
    print("Database version : %s " % data)
    # 关闭数据库连接
    db.close()


def createtable():
    # 打开数据库连接
    db = MySQLdb.connect("127.0.0.1", "root", "123456", "original_article_database", charset='utf8')
    # 使用cursor()方法获取操作游标
    cursor = db.cursor()
    # 如果数据表已经存在使用 execute() 方法删除表。
    cursor.execute("DROP TABLE IF EXISTS EMPLOYEE")
    # 创建数据表SQL语句
    sql = """CREATE TABLE EMPLOYEE (
             FIRST_NAME  CHAR(20) NOT NULL,
             LAST_NAME  CHAR(20),
             AGE INT,  
             SEX CHAR(1),
             INCOME FLOAT )"""
    cursor.execute(sql)
    # 关闭数据库连接
    db.close()


def insert():
    # 打开数据库连接
    db = MySQLdb.connect("127.0.0.1", "root", "123456", "original_article_database", charset='utf8')
    # 使用cursor()方法获取操作游标
    cursor = db.cursor()
    # SQL 插入语句
    sql = """INSERT INTO EMPLOYEE(FIRST_NAME,
             LAST_NAME, AGE, SEX, INCOME)
             VALUES ('Mac', 'Mohan', 20, 'M', 2000)"""
    try:
        # 执行sql语句
        cursor.execute(sql)
        # 提交到数据库执行
        db.commit()
    except:
        # Rollback in case there is any error
        db.rollback()
    # 关闭数据库连接
    db.close()


def select():
    # 打开数据库连接
    db = MySQLdb.connect("127.0.0.1", "root", "123456", "original_article_database", charset='utf8')
    # 使用cursor()方法获取操作游标
    cursor = db.cursor()
    # SQL 查询语句
    sql = "SELECT * FROM EMPLOYEE "
    try:
        # 执行SQL语句
        cursor.execute(sql)
        # 获取所有记录列表
        results = cursor.fetchall()
        for row in results:
            fname = row[0]
            lname = row[1]
            age = row[2]
            sex = row[3]
            income = row[4]
            # 打印结果
            print
            "fname=%s,lname=%s,age=%s,sex=%s,income=%s" % \
            (fname, lname, age, sex, income)
    except:
        print
        "Error: unable to fecth data"
    # 关闭数据库连接
    db.close()


def update():
    # 打开数据库连接
    db = MySQLdb.connect("127.0.0.1", "root", "123456", "original_article_database", charset='utf8')
    # 使用cursor()方法获取操作游标
    cursor = db.cursor()
    # SQL 更新语句
    sql = "UPDATE EMPLOYEE SET AGE = AGE + 1 WHERE SEX = '%c'" % ('M')
    try:
        # 执行SQL语句
        cursor.execute(sql)
        # 提交到数据库执行
        db.commit()
    except:
        # 发生错误时回滚
        db.rollback()
    # 关闭数据库连接
    db.close()


def delete():
    # 打开数据库连接
    db = MySQLdb.connect("127.0.0.1", "root", "123456", "original_article_database", charset='utf8')
    # 使用cursor()方法获取操作游标
    cursor = db.cursor()
    # SQL 删除语句
    sql = "DELETE FROM EMPLOYEE WHERE AGE > %s" % (20)
    try:
        # 执行SQL语句
        cursor.execute(sql)
        # 提交修改
        db.commit()
    except:
        # 发生错误时回滚
        db.rollback()
    # 关闭连接
    db.close()


if __name__ == '__main__':
    # insert()
    # createtable()
    select()
