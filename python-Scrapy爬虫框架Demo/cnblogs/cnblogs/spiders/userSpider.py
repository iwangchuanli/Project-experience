# -*- coding: utf-8 -*-
import scrapy

class userSpider(scrapy.Spider):
    name = "userSpider"
    start_urls=[
    'https://www.cnblogs.com/lhyxq/',
    ]

    def parse(self, response):
        users = response.css('div.day')
        for user in users:
            title = user.css('.postTitle2::text').extract()[0]+"\n"
            href = user.css('.postTitle2').xpath('@href').extract()[0]+"\n"
            comment = user.css('.postDesc::text').extract()[0]
            fileName = 'lhyxq-user.txt'
            with open(fileName,"a+") as f:
                f.write(title)
                f.write(href)
                f.write(comment)
                f.write("\n-----------------\n")
                f.close()
        next_page = response.css('div.topicListFooter > div > a').xpath('@href').extract_first()
        if next_page is not None:
            next_page = response.urljoin(next_page)
            yield scrapy.Request(next_page, callback=self.parse)



