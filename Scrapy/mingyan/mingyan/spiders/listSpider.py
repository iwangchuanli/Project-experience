# -*- coding: utf-8 -*-
# @Author: Wangchuanli
# @Date:   2018-12-27 16:04:47
# @Last Modified by:   Wangchuanli
# @Last Modified time: 2018-12-27 16:20:14
import scrapy

class itemSpider(scrapy.Spider):

    # name = '<a href="http://www.scrapyd.cn/doc/149.html" target="_blank"><u>listSpider</u></a>'
    name = 'listSpider'
    start_urls = ['http://lab.scrapyd.cn']

    def parse(self, response):
        mingyan = response.css('div.quote')  # 提取首页所有名言，保存至变量mingyan

        for v in mingyan:  # 循环获取每一条名言里面的：名言内容、作者、标签
            text = v.css('.text::text').extract_first()  # 提取名言
            autor = v.css('.author::text').extract_first()  # 提取作者
            tags = v.css('.tags .tag::text').extract()  # 提取标签
            tags = ','.join(tags)  # 数组转换为字符串
            """
            接下来进行写文件操作，每个名人的名言储存在一个txt文档里面
            """
            fileName = '%s-语录.txt' % autor  # 定义文件名,如：木心-语录.txt
            with open(fileName, "a+") as f:  # 不同人的名言保存在不同的txt文档，“a+”以追加的形式
                f.write(text)
                f.write('\n')  # ‘\n’ 表示换行
                f.write('标签：' + tags)
                f.write('\n-------\n')
                f.close()
        next_page = response.css('li.next a::attr(href)').extract_first()
        if next_page is not None:
            next_page = response.urljoin(next_page)
            yield scrapy.Request(next_page, callback=self.parse)
