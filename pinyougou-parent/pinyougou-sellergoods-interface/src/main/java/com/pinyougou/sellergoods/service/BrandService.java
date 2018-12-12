package com.pinyougou.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbBrand;

import entity.PageResult;

/**
 * 品牌管理的服务层接口
 * @author jt
 *
 */
public interface BrandService {
	/**
	 * 查询所有品牌列表的接口的方法
	 * @return
	 */
	public List<TbBrand> findAll();
	
	/**
	 * 分页查询品牌的方法
	 */
	public PageResult findByPage(TbBrand brand,int pageNum,int pageSize);
	
	/**
	 * 保存品牌的方法
	 */
	public void save(TbBrand brand);
	
	/**
	 * 查询一个品牌
	 */
	public TbBrand findById(Long id);
	
	/**
	 * 修改品牌的方法
	 */
	public void update(TbBrand brand);
	
	/**
	 * 删除多个
	 */
	public void delete(Long[] ids);
	
	public List<Map> selectOptionList();
}
