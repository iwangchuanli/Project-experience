# -*- coding: utf-8 -*-
'''
Created on 2018��12��29��

@author: Administrator
'''
import pymysql

# Open database connection
db = pymysql.connect("localhost", "root", "123456", "testdb")

# prepare a cursor object using cursor() method
cursor = db.cursor()

# Prepare SQL query to INSERT a record into the database.
sql = """INSERT INTO EMPLOYEE(FIRST_NAME,
   LAST_NAME, AGE, SEX, INCOME)
   VALUES ('Mac', 'Su', 20, 'M', 5000)"""
try:
# Execute the SQL command
    cursor.execute(sql)
# Commit your changes in the database
    db.commit()
except:
    # Rollback in case there is any error
    db.rollback()
# # 再次插入一条记录
# Prepare SQL query to INSERT a record into the database.
sql = """INSERT INTO EMPLOYEE(FIRST_NAME,
   LAST_NAME, AGE, SEX, INCOME)
   VALUES ('Kobe', 'Bryant', 40, 'M', 8000)"""
    
sql2 = "INSERT INTO EMPLOYEE(FIRST_NAME, \
   LAST_NAME, AGE, SEX, INCOME) \
   VALUES ('%s', '%s', '%d', '%c', '%d' )" % \
   ('Max', 'Su', 25, 'F', 2800)
   
try:
    # Execute the SQL command
    cursor.execute(sql)
    # Commit your changes in the database
    db.commit()
except:
    # Rollback in case there is any error
    db.rollback()
print (sql)
print('Yes, Insert Successfull.')
# disconnect from server
db.close()
