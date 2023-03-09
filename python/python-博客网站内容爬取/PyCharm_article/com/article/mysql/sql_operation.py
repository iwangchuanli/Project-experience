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
    except Exception as e:
        print(e)
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
    except Exception as e:
        print(e)
        print('\033[1;31m' + '-----sql执行失败！-----' + '\033[0m')
        db.rollback()


if __name__ == '__main__':
    db = getcon()

    closecon(db)
