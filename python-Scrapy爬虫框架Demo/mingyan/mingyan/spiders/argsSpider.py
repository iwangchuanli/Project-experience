# -*- coding: utf-8 -*-
# @Author: Wangchuanli
# @Date:   2018-12-27 16:26:58
# @Last Modified by:   Wangchuanli
# @Last Modified time: 2018-12-27 16:27:34
import scrapy
# run  scrapy crawl argsSpider -a tag=励志
class ArgsspiderSpider(scrapy.Spider):

    name = "argsSpider"

    def start_requests(self):
        url = 'http://lab.scrapyd.cn/'
        tag = getattr(self, 'tag', None)  # 获取tag值，也就是爬取时传过来的参数
        if tag is not None:  # 判断是否存在tag，若存在，重新构造url
            url = url + 'tag/' + tag  # 构造url若tag=爱情，url= "http://lab.scrapyd.cn/tag/爱情"
        yield scrapy.Request(url, self.parse)  # 发送请求爬取参数内容

    """
    以下内容为上一讲知识，若不清楚具体细节，请查看上一讲！
    """

    def parse(self, response):
        mingyan = response.css('div.quote')
        for v in mingyan:
            text = v.css('.text::text').extract_first()
            tags = v.css('.tags .tag::text').extract()
            tags = ','.join(tags)
            fileName = '%s-语录.txt' % tags
            with open(fileName, "a+") as f:
                f.write(text)
                f.write('\n')
                f.write('标签：' + tags)
                f.write('\n-------\n')
                f.close()
        next_page = response.css('li.next a::attr(href)').extract_first()
        if next_page is not None:
            next_page = response.urljoin(next_page)
            yield scrapy.Request(next_page, callback=self.parse)