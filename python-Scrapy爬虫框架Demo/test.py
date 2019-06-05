# -*- coding: utf-8 -*-
# @Author: Wangchuanli
# @Date:   2018-12-27 14:48:20
# @Last Modified by:   Wangchuanli
# @Last Modified time: 2018-12-27 15:20:50
import scrapy
class mingyanSpider(scrapy.Spider):
    name = "quotes"
    start_urls = [
        'http://lab.scrapyd.cn/',
    ]

    def parse(self, response):
        for quote in response.css('div.quote'):
            yield {
                '内容': quote.css('span.text::text').extract_first(),
                '作者': quote.xpath('span/small/text()').extract_first(),
            }

        next_page = response.css('li.next a::attr("href")').extract_first()
        if next_page is not None:
            yield scrapy.Request(next_page, self.parse)