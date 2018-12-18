package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;

/**
 * <p>
 * 内容类别相关接口的实现（包括：获取内容分类以及添加、更新、删除内容分类的接口）
 * </p>
 * <p>
 * 对应后台网站后台管理目录下的内容分类管理
 * </p>
 * 
 * @author Snailclimb
 * @version 1.0
 */
public interface ContentCategoryService {
	/**
	 * 获取内容分类
	 * 
	 * @param parentId
	 *            父节点
	 * @return 内容分类list
	 */
	List<EasyUITreeNode> getContentCatList(long parentId);

	/**
	 * 添加内容分类
	 * 
	 * @param parentId
	 *            父节点
	 * @param name
	 *            分类名称
	 * @return 添加分类的结果
	 */
	E3Result addContentCategory(long parentId, String name);

	/**
	 * 更新内容分类
	 * 
	 * @param id
	 *            修改的内容分类的id
	 * @param name
	 *            修改后内容分类的名字
	 * @return 更新内容分类的结果
	 */
	E3Result upadateContentCategory(long id, String name);

	/**
	 * 删除内容分类
	 * 
	 * @param id
	 *            修改的内容分类的id
	 * @return 删除内容分类的结果
	 */
	E3Result deleteContentCategory(long id);
}
