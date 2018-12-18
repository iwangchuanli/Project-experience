package cn.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbContentExample.Criteria;

/**
 * <p>
 * 内容管理相关接口的实现（包括新增、更新、删除、内容列表查询等方法的接口）
 * </p>
 * <p>
 * 对应后台网站后台管理目录下的内容管理（比如我们首页的大广告，小广告什么都在这里发布）
 * </p>
 * 
 * @author Snailclimb
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	// 注入JedisClient接口便于在单机与集群版redis版无缝切换
	@Autowired
	private JedisClient jedisClient;
	@Value("${CONTENT_LIST}")
	private String CONTENT_LIST;

	/**
	 * 根据内容分类id查询内容列表，比如给定大广告id值就可以查询到大广告下面的内容 目前在首页大广告轮播图中使用了
	 */
	@Override
	public List<TbContent> getContentListByCid(long cid) {
		// 查询缓存
		try {
			// 如果缓存中有直接响应结果
			String json = jedisClient.hget(CONTENT_LIST, cid + "");
			System.out.println(json);// 便于测试的时候使用
			if (StringUtils.isNotBlank(json)) {
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 1 如果没有查询redis数据库
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		// 2 设置查询条件
		criteria.andCategoryIdEqualTo(cid);
		// 3 执行查询
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		// 把结果添加到缓存
		try {
			jedisClient.hset(CONTENT_LIST, cid + "", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 插入数据到数据库
	 */
	@Override
	public E3Result addContent(TbContent content) {
		// 将内容数据插入到内容表
		content.setCreated(new Date());
		content.setUpdated(new Date());
		// 插入到数据库
		contentMapper.insert(content);
		// 缓存同步
		jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		return E3Result.ok();
	}

	/**
	 * 更新内容
	 */
	@Override
	public E3Result updateContent(TbContent content) {
		// 将内容数据插入到内容表
		content.setCreated(new Date());
		content.setUpdated(new Date());
		// 插入到数据库
		contentMapper.updateByPrimaryKey(content);
		// 缓存同步
		jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		return E3Result.ok();
	}

	/**
	 * 获取内容列表
	 */
	@Override
	public EasyUIDataGridResult getContentList(long categoryId, int page, int rows) {
		// 根据categoryId查询
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		// 分页管理
		PageHelper.startPage(page, rows);
		List<TbContent> list = contentMapper.selectByExample(example);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		// {total:”2”,rows:[{“id”:”1”,”name”:”张三”},{“id”:”2”,”name”:”李四”}]}
		// rows中包含了放在该页的所有商品对象的json格式
		result.setRows(list);
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	/**
	 * 删除内容
	 */
	@Override
	public E3Result deleteContent(long[] contentId) {
		for (long l : contentId) {
			contentMapper.deleteByPrimaryKey(l);
		}
		return E3Result.ok();
	}

}
