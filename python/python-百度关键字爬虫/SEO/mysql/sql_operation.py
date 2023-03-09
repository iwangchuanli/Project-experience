# -*- coding=utf-8 -*-

import MySQLdb


# 获得数据库连接
def getcon():
    # 打开数据库连接
    db = MySQLdb.connect("127.0.0.1", "root", "123456", "original_article_database", charset='utf8')
    return db


# 关闭数据库连接
def closecon(db):
    db.close()


# 创建表格操作

# 数据查询操作
def baseselect(db, sql):
    cursor = db.cursor()
    try:
        # 执行SQL语句
        cursor.execute(sql)
        # 获取所有记录列表
        results = cursor.fetchall()
        print('\033[1;32m' + '+++++sql查询成功！+++++' + '\033[0m')
        return results
    except:
        print('\033[1;32m' + "Error: unable to fecth data" + '\033[0m')


# 数据更新操作
# 数据删除操作
# 数据插入操作
def baseoperation(db, sql):
    cursor = db.cursor()
    try:
        cursor.execute(sql)
        db.commit()
        print('\033[1;32m' + '+++++sql执行成功！+++++' + '\033[0m')
    except:
        print('\033[1;31m' + '-----sql执行失败！-----' + '\033[0m')
        db.rollback()


if __name__ == '__main__':
    db = getcon()
    cursor = db.cursor()
    # SQL 查询语句
    sql = "SELECT * FROM EMPLOYEE"
    try:
        # 执行SQL语句
        cursor.execute(sql)
        # 获取所有记录列表
        results = cursor.fetchall()
        print(results)
        for row in results:
            fname = row[0]
            lname = row[1]
            age = row[2]
            sex = row[3]
            income = row[4]
            # 打印结果
            print("fname=%s,lname=%s,age=%s,sex=%s,income=%s" % (fname, lname, age, sex, income))
    except:
        print
        "Error: unable to fecth data"
    closecon(db)
