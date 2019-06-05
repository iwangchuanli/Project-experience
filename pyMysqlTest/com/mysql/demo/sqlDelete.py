# -*- coding: utf-8 -*-
'''
Created on 2018��12��29��

@author: Administrator
'''
import pymysql

# Open database connection
db = pymysql.connect("localhost","root","123456","test" )

# prepare a cursor object using cursor() method
cursor = db.cursor()

# Prepare SQL query to DELETE required records
sql = "DELETE FROM EMPLOYEE WHERE AGE > '%d'" % (40)
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