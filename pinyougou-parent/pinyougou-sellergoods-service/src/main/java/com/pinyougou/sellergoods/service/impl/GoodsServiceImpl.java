package com.pinyougou.sellergoods.service.impl;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.util.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.mapper.TbGoodsDescMapper;
import com.pinyougou.mapper.TbGoodsMapper;
import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.mapper.TbItemMapper;
import com.pinyougou.mapper.TbSellerMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.TbGoodsDesc;
import com.pinyougou.pojo.TbGoodsExample;
import com.pinyougou.pojo.TbGoodsExample.Criteria;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.pojo.TbItemExample;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.pojo.group.Goods;
import com.pinyougou.sellergoods.service.GoodsService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private TbGoodsMapper goodsMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbGoods> findAll() {
		return goodsMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbGoods> page=   (Page<TbGoods>) goodsMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Autowired
	private TbGoodsDescMapper goodsDescMapper;
	
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Autowired
	private TbBrandMapper brandMapper;
	
	@Autowired
	private TbSellerMapper sellerMapper;
	/**
	 * 增加
	 */
	@Override
	public void add(Goods goods) {
		goods.getGoods().setAuditStatus("0"); // 设置审核的状态
		
		goodsMapper.insert(goods.getGoods()); // 插入商品信息
		
		goods.getGoodsDesc().setGoodsId(goods.getGoods().getId());
		
		goodsDescMapper.insert(goods.getGoodsDesc()); // 插入商品的扩展信息
		
		setItemList(goods);
	}
	
	private void setItemList(Goods goods){
		if("1".equals(goods.getGoods().getIsEnableSpec())){
			// 启用规格
			// 保存SKU列表的信息:
			for(TbItem item:goods.getItemList()){
				// 设置SKU的数据：
				// item.setTitle(title);
				String title = goods.getGoods().getGoodsName();
				Map<String,String> map = JSON.parseObject(item.getSpec(), Map.class);
				//Map<String,String> map = item.getSpec();
				for (String key : map.keySet()) {
					title+= " "+map.get(key);
				}
				item.setTitle(title);
				
				setValue(goods,item);
				
				itemMapper.insert(item);
			}
		}else{
			// 没有启用规格
			TbItem item = new TbItem();
			
			item.setTitle(goods.getGoods().getGoodsName());
			
			item.setPrice(goods.getGoods().getPrice());
			
			item.setNum(999);
			
			item.setStatus("0");
			
			item.setIsDefault("1");
			item.setSpec("{}");
			//item.setSpec(new HashMap());
			setValue(goods,item);
			itemMapper.insert(item);
		}
	}

	private void setValue(Goods goods,TbItem item){
		List<Map> imageList = JSON.parseArray(goods.getGoodsDesc().getItemImages(),Map.class);
		if(imageList.size()>0){
			item.setImage((String)imageList.get(0).get("url"));
		}
		
		// 保存三级分类的ID:
		item.setCategoryid(goods.getGoods().getCategory3Id());
		item.setCreateTime(new Date());
		item.setUpdateTime(new Date());
		// 设置商品ID
		item.setGoodsId(goods.getGoods().getId());
		item.setSellerId(goods.getGoods().getSellerId());
		
		TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(goods.getGoods().getCategory3Id());
		item.setCategory(itemCat.getName());
		
		TbBrand brand = brandMapper.selectByPrimaryKey(goods.getGoods().getBrandId());
		item.setBrand(brand.getName());
		
		TbSeller seller = sellerMapper.selectByPrimaryKey(goods.getGoods().getSellerId());
		item.setSeller(seller.getNickName());
	}
	
	/**
	 * 修改
	 */
	@Override
	public void update(Goods goods){
		// 修改商品信息
		goods.getGoods().setAuditStatus("0");
		goodsMapper.updateByPrimaryKey(goods.getGoods());
		// 修改商品扩展信息:
		goodsDescMapper.updateByPrimaryKey(goods.getGoodsDesc());
		// 修改SKU信息:
		// 先删除，再保存:
		// 删除SKU的信息:
		TbItemExample example = new TbItemExample();
		com.pinyougou.pojo.TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andGoodsIdEqualTo(goods.getGoods().getId());
		itemMapper.deleteByExample(example);
		// 保存SKU的信息
		setItemList(goods);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Goods findOne(Long id){
		Goods goods = new Goods();
		// 查询商品表的信息
		TbGoods tbGoods = goodsMapper.selectByPrimaryKey(id);
		goods.setGoods(tbGoods);
		// 查询商品扩展表的信息
		TbGoodsDesc tbGoodsDesc = goodsDescMapper.selectByPrimaryKey(id);
		goods.setGoodsDesc(tbGoodsDesc);
		
		// 查询SKU表的信息:
		TbItemExample example = new TbItemExample();
		com.pinyougou.pojo.TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andGoodsIdEqualTo(id);
		List<TbItem> list = itemMapper.selectByExample(example);
		goods.setItemList(list);
		
		return goods;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
//			goodsMapper.deleteByPrimaryKey(id);
			TbGoods tbGoods = goodsMapper.selectByPrimaryKey(id);
			tbGoods.setIsDelete("1");
			goodsMapper.updateByPrimaryKey(tbGoods);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbGoods goods, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbGoodsExample example=new TbGoodsExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andIsDeleteIsNull();
		
		if(goods!=null){			
					if(goods.getSellerId()!=null && goods.getSellerId().length()>0){
			criteria.andSellerIdEqualTo(goods.getSellerId());
		}
		if(goods.getGoodsName()!=null && goods.getGoodsName().length()>0){
			criteria.andGoodsNameLike("%"+goods.getGoodsName()+"%");
		}
		if(goods.getAuditStatus()!=null && goods.getAuditStatus().length()>0){
			criteria.andAuditStatusLike("%"+goods.getAuditStatus()+"%");
		}
		if(goods.getIsMarketable()!=null && goods.getIsMarketable().length()>0){
			criteria.andIsMarketableLike("%"+goods.getIsMarketable()+"%");
		}
		if(goods.getCaption()!=null && goods.getCaption().length()>0){
			criteria.andCaptionLike("%"+goods.getCaption()+"%");
		}
		if(goods.getSmallPic()!=null && goods.getSmallPic().length()>0){
			criteria.andSmallPicLike("%"+goods.getSmallPic()+"%");
		}
		if(goods.getIsEnableSpec()!=null && goods.getIsEnableSpec().length()>0){
			criteria.andIsEnableSpecLike("%"+goods.getIsEnableSpec()+"%");
		}
		if(goods.getIsDelete()!=null && goods.getIsDelete().length()>0){
			criteria.andIsDeleteLike("%"+goods.getIsDelete()+"%");
		}

		}
		
		Page<TbGoods> page= (Page<TbGoods>)goodsMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public void updateStatus(Long[] ids, String status) {
		for (Long id : ids) {
			TbGoods tbGoods = goodsMapper.selectByPrimaryKey(id);
			
			tbGoods.setAuditStatus(status);
			
			goodsMapper.updateByPrimaryKey(tbGoods);
		}
	}
	
	
	/**
	 * 根据SPU的ID集合查询SKU列表
	 * @param goodsIds
	 * @param status
	 * @return
	 */
	public List<TbItem>	findItemListByGoodsIdListAndStatus(Long []goodsIds,String status){
		
		TbItemExample example=new TbItemExample();
		com.pinyougou.pojo.TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(status);//状态
		criteria.andGoodsIdIn( Arrays.asList(goodsIds));//指定条件：SPUID集合
		
		return itemMapper.selectByExample(example);
	}
}
