# -*- coding=utf-8 -*-
'''
mysql数据连接
'''
import MySQLdb

database = 'db_seo'


# 获得数据库连接
def getcon():
    # 打开数据库连接
    db = MySQLdb.connect("127.0.0.1", "root", "123456", 'db_seo', charset='utf8')
    return db


# 关闭数据库连接
def closecon(self):
    self.close()


# 创建表格操作

# 数据查询操作
def baseselect(self, sql):
    cursor = self.cursor()
    try:
        # 执行SQL语句
        cursor.execute(sql)
        # 获取所有记录列表
        results = cursor.fetchall()
        # print('\033[1;32m' + '+++++sql查询成功！+++++' + '\033[0m')
        return results
    except Exception as e:
        print(e)
        print('\033[1;32m' + "Error: unable to fecth data" + '\033[0m')


# 数据更新操作
# 数据删除操作
# 数据插入操作
def baseoperation(self, sql):
    cursor = self.cursor()
    try:
        cursor.execute(sql)
        self.commit()
        # print('\033[1;32m' + '+++++sql执行成功！+++++' + '\033[0m')
    except Exception as e:
        print(e)
        print('\033[1;31m' + '-----sql执行失败！-----' + '\033[0m')
        self.rollback()


if __name__ == '__main__':
    db = getcon()
    print(db)
    closecon(db)
