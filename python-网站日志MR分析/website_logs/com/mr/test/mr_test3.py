from mrjob.job import MRJob
from mrjob.step import MRStep
import re

WORD_RE = re.compile(r"[\w']+")


class MRMostUsedWord(MRJob):

    def mapper(self, key, line):
        i = 0
        for flow in line.split():
            if i == 0:
                ip = flow
            # # 获取时间字段,位于日志的第4列，内容如[14/Jan/2018:08:41:24
            # if i == 3:
            #     timerow = flow.split(":")
            #     # 获取“小时:分钟”作为key
            #     hm = timerow[1] + ":" + timerow[2]
            # 获取日志第10列  - 发送的字节数，作为value
            # if i == 9 and re.match(r"\d{1,}", flow):
            #     # 初始化key-value
            #     yield ip, int(flow)
            yield ip, 1
            i += 1

    def reducer(self, key, occurrences):
        # 相同key "小时:分钟"的value作累加操作
        yield key, sum(occurrences)


# never forget
if __name__ == '__main__':
    MRMostUsedWord.run()
