# -*- coding: utf-8 -*-
# @Author: Marte
# @Date:   2019-04-13 13:20:14
# @Last Modified by:   Marte
# @Last Modified time: 2019-04-13 13:21:08
import os

# 列出当前目录下所有的文件
files = os.listdir(".")

for filename in files:
    portion = os.path.splitext(filename)
    # 如果后缀是.txt
    if portion[1] == "":
        # 重新组合文件名和后缀名
        newname = portion[0] + ".html"
        os.rename(filename,newname)