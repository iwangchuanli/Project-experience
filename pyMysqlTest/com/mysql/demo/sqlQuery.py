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
# 按字典返回 
# cursor = db.cursor(pymysql.cursors.DictCursor)

# Prepare SQL query to select a record from the table.
sql = "SELECT * FROM EMPLOYEE \
       WHERE INCOME > %d" % (1000)
#print (sql)
try:
    # Execute the SQL command
    cursor.execute(sql)
    # Fetch all the rows in a list of lists.
    results = cursor.fetchall()
    for row in results:
        #print (row)
        fname = row[1]
        lname = row[2]
        age = row[3]
        sex = row[4]
        income = row[5]
        # Now print fetched result
        print ("name = %s %s,age = %s,sex = %s,income = %s" % \
               (fname, lname, age, sex, income ))
except:
    import traceback
    traceback.print_exc()
    print ("Error: unable to fetch data")

# disconnect from server
db.close()
