# -*- coding:UTF-8 -*-
'''

网站访问流量作为衡量一个站点的价值、热度的重要标准，另外，在CDN服务中心流量会涉及计费，如何快速准确分析当前站点的流量数据至关重要。本实例精确到分钟统计网站访问流量，原理是在mapper操作时将Web日志中小时的每分钟作为key，将对应的发送字节数作为value, 在reducer操作时对相同key做累加(sum)统计。

'''

from mrjob.job import MRJob
import re


class MRCounter(MRJob):

    def mapper(self, key, line):
        i = 0;
        for flow in line.split():
            # 获取时间字段,位于日志的第4列，内容如[14/Jan/2018:08:41:24
            if i == 3:
                timerow = flow.split(":")
                # 获取“小时:分钟”作为key
                hm = timerow[1] + ":" + timerow[2]
            # 获取日志第10列  - 发送的字节数，作为value
            if i == 9 and re.match(r"\d{1,}", flow):
                # 初始化key-value
                yield hm, int(flow)
            i += 1

    def reducer(self, key, occurrences):
        # 相同key "小时:分钟"的value作累加操作
        yield key, sum(occurrences)


if __name__ == '__main__':
    MRCounter.run()

#python httpflow.py -r hadoop --jobconf mapreduce.job.priority=VERY_HIGH --jobconf mapreduce.map.tasks=2 --jobconf mapduce.reduce.tasks=1 -o hdfs://liuyazhuang121:9000/output/httpflow hdfs://liuyazhuang121:9000/user/root/website.com/20180114
