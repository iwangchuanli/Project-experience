# -*- coding: utf-8 -*-
'''
Created on 2018��12��29��

@author: Administrator
'''
import pymysql


def getCon():
    db = pymysql.connect("localhost", "root", "123456", "testdb")
    if (db is not None):
        print("success to connection")
        return db
    else:
        print("failed to get connection")

    
def closeCon(db):
    db.close()
    print("database was closed.")

        
def execute(sql):
    db = getCon()
    try:
        cursor = db.cursor()
        cursor.execute(sql)
        db.commit()
        print("success:" + sql)
    except:
        print("failed:" + sql)
        db.rollback()
    finally:
        closeCon(db)

            
def query(sql):
    db = getCon()
    try:
        cursor = db.cursor()
        cursor.execute(sql)
        results = cursor.fetchall()
        for item in results:
            print(item)
        db.commit()
        print("success:" + sql)
    except:
        print("failed:" + sql)
        db.rollback()
    finally:
        closeCon(db)


Insertsql = "INSERT INTO EMPLOYEE(FIRST_NAME, \
   LAST_NAME, AGE, SEX, INCOME) \
   VALUES ('%s', '%s', '%d', '%c', '%d' )" % \
   ('Max', 'Su', 25, 'F', 2800)
Deletesql = "DELETE FROM EMPLOYEE WHERE AGE > '%d'" % (40)
Querysql = "SELECT * FROM EMPLOYEE \
       WHERE INCOME > %d" % (1000)

# execute(Insertsql)
query(Querysql)
