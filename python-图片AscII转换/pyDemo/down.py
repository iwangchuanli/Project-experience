# -*- coding: utf-8 -*-
# @Author: Wangchuanli
# @Date:   2018-12-02 20:27:46
# @Last Modified by:   Wangchuanli
# @Last Modified time: 2018-12-02 21:13:58
import requests
print ("downloading with requests")
url = 'https://static.segmentfault.com/v-5c03a88e/global/img/emojis/bowtie.png'
r = requests.get(url)
with open("bowtie.png", "wb") as code:
     code.write(r.content)

fo = open("emoji.txt","r+",encoding="utf-8")
    doc = fo.read()
    soup = BeautifulSoup(doc,"html.parser",from_encoding="utf-8")
    links = soup.select('img')
    print ("所有的链接")
    for link in links:

        emojiurl = link['src']
        title = "emoji/"+link['title']+".png"
        print (emojiurl+title)
        #print(title)
        down(emojiurl,title)
    fo.close()