# -*- coding: utf-8 -*-
'''
Created on 2018��12��29��

@author: Administrator
'''
import pymysql

# Open database connection
db = pymysql.connect("localhost","root","123456","test" )

# prepare a cursor object using cursor() method
#cursor = db.cursor()
cursor = db.cursor(pymysql.cursors.DictCursor)
# prepare a cursor object using cursor() method
cursor = db.cursor()

# Prepare SQL query to UPDATE required records
sql = "UPDATE EMPLOYEE SET AGE = AGE + 1 \
                          WHERE SEX = '%c'" % ('M')
try:
    # Execute the SQL command
    cursor.execute(sql)
    # Commit your changes in the database
    db.commit()
except:
    # Rollback in case there is any error
    db.rollback()

# disconnect from server
db.close()