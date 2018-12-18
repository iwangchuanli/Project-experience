package cn.e3mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;

/**
 * 内容分类管理Service
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	/**
	 * 查询分类列表
	 */
	@Override
	public List<EasyUITreeNode> getContentCatList(long parentId) {
		// 根据parentid查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		// 设置查询条件
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbContentCategory> catList = contentCategoryMapper.selectByExample(example);
		// 转换成EasyUITreeNode的列表
		List<EasyUITreeNode> nodeList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : catList) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			// 添加到列表
			nodeList.add(node);
		}
		return nodeList;
	}

	/**
	 * 添加分类
	 */
	@Override
	public E3Result addContentCategory(long parentId, String name) {
		// 创建一个tb_content_category表对应的pojo对象
		TbContentCategory contentCategory = new TbContentCategory();
		// 设置pojo的属性
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		// 1(正常),2(删除)
		contentCategory.setStatus(1);
		// 默认排序就是1
		contentCategory.setSortOrder(1);
		// 新添加的节点一定是叶子节点
		contentCategory.setIsParent(false);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		// 插入到数据库
		contentCategoryMapper.insert(contentCategory);
		// 判断父节点的isparent属性。如果不是true改为true
		// 根据parentid查询父节点
		TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parent.getIsParent()) {
			parent.setIsParent(true);
			// 更新到数数据库
			contentCategoryMapper.updateByPrimaryKey(parent);
		}
		// 返回结果，返回E3Result，包含pojo
		return E3Result.ok(contentCategory);
	}

	/**
	 * 重命名分类
	 */
	@Override
	public E3Result upadateContentCategory(long id, String name) {
		// 创建一个tb_content_category表对应的pojo对象
		TbContentCategory node = contentCategoryMapper.selectByPrimaryKey(id);
		// 更新名字
		node.setName(name);
		contentCategoryMapper.updateByPrimaryKey(node);
		return null;
	}

	/**
	 * 删除分类
	 */
	@Override
	public E3Result deleteContentCategory(long id) {
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		if (contentCategory.getIsParent()) {
			List<EasyUITreeNode> list = getContentCatList(id);
			// 递归删除
			for (EasyUITreeNode tbcontentCategory : list) {
				deleteContentCategory(tbcontentCategory.getId());
			}
		}
			if (getContentCatList(contentCategory.getParentId()).size() == 1) {
				TbContentCategory parentCategory = contentCategoryMapper
						.selectByPrimaryKey(contentCategory.getParentId());
				parentCategory.setIsParent(false);
				contentCategoryMapper.updateByPrimaryKey(parentCategory);
			}
			contentCategoryMapper.deleteByPrimaryKey(id);
			return E3Result.ok();

		}
}
