from get_index import BaiduIndex

if __name__ == "__main__":
    """
    最多一次请求5个关键词
    """
    # 查看城市和省份的对应代码
    # print(BaiduIndex.city_code)
    # print(BaiduIndex.province_code)

    baidu_index = BaiduIndex(['张艺兴', 'lol', '极限挑战', 'python 免费空间'], '2019-04-01', '2019-04-02')
    # for data in baidu_index('lol', 'all'):
    #     print(data)

    # 获取全部5个关键词的全部数据
    # print(baidu_index.result)
    # 获取1个关键词的全部数据
    print(baidu_index.result['python 免费空间'])
    # 获取1个关键词的移动端数据
    print(baidu_index.result['极限挑战']['all'])
    # 获取1个关键词的pc端数据
    print(baidu_index.result['极限挑战']['pc'])
