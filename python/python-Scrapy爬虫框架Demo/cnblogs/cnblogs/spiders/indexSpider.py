# -*- coding: utf-8 -*-

import scrapy

class indexSpider(scrapy.Spider):
    name = "indexSpider"

    def start_requests(self):
        urls = [
        'https://www.cnblogs.com/',
        'https://www.cnblogs.com/news/',
        'https://www.cnblogs.com/pick/',
        'https://www.cnblogs.com/candidate/',
        ]
        # for num in (4,8):
        #     pageStr = "https://www.cnblogs.com/#p"+str(num)
        #     next_page = response.urljoin(pageStr)
        #     urls.append(pageStr)
        self.log(urls)
        for url in urls:
                yield scrapy.Request(url = url, callback = self.parse)

    def parse(self, response):
        # num = url.split()[26:]
        items = response.css('div.post_item_body')
        for item in items:
            title = item.css('.titlelnk::text').extract_first()+"\n"
            link = item.css('a').xpath('@href').extract_first()+"\n"
            # summary = item.css('.post_item_summary::text').extract_first()
            author = item.css('.lightblue::text').extract_first()
            # time = item.css('.post_item_foot::text').extract_first()
            comment = item.css('.gray::text').extract()[0]
            view = item.css('.gray::text').extract()[1]
            fileName = 'cnblogs.txt'
            with open(fileName,"a+") as f:
                # f.write(response.body)
                f.write(title)
                f.write(link)
                f.write(author)
                f.write(comment)
                f.write(view)
                f.write("\n"+"-----------------------------"+"\n")
            self.log('保存文章: %s' % title)
        # next_page = response.css('div.pager>a').xpath('@href').extract()[1]
        # num = int(next_page.split("/")[3])


