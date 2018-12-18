# -*- coding: utf-8 -*-
# @Author: Wangchuanli
# @Date:   2018-12-15 09:54:15
# @Last Modified by:   Wangchuanli
# @Last Modified time: 2018-12-18 10:54:12
import os
# 从本地clone的仓库中得到文件列表
def fileListFunc(fileList,filePath,suffix):
    for filename in os.listdir(filePath):
        if os.path.isdir((filePath+"/"+filename)):
            # print (filePath+"/"+filename)
            fileListFunc(fileList,(filePath+"/"+filename),suffix)
        else:
            if filename.endswith(suffix):
                fileList.append(filePath+"/"+filename)
    return fileList
# 对列表中的文件进行字符串拼接成图片链接
def listHandler(fileList,filePath):
    for i in range(0,len(fileList)):
        fileList[i] = "<img src=\""+fileList[i].replace(filePath,"https://github.com/wangchuanli001/image-host/raw/master")+"\" height=\"200\" width=\"200\">"
    fileList.append("-----------------------")
# 将图片链接追加到md文件的最后
def list2md(fileList):
    fo = open("image.md","a+")
    for item in fileList:
        fo.write(item)
    fo.close()

fileList = []
filePath = "E:\GitHub\Respositories\image-host"
fileLists = fileListFunc(fileList,filePath,"jpg")
listHandler(fileLists,filePath)
print (fileLists)
list2md(fileLists)
