package com.pinyougou.search.service;

import java.util.List;
import java.util.Map;

public interface ItemSearchService {

	
	/**
	 * 搜索方法
	 * @param searchMap
	 * @return
	 */
	public Map search(Map searchMap);
	
	/**
	 * 导入列表
	 * @param list
	 */
	public void importList(List list);
	
	/**
	 * 删除商品列表
	 * @param goodsIds  (SPU)
	 */
	public void deleteByGoodsIds(List goodsIds);
	
}
