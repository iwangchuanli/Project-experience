package cn.e3mall.content.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbContent;

/**
 * <p>
 * 内容管理的相关接口（包括新增、更新、删除、内容列表查询等方法的接口）
 * </p>
 * <p>
 * 对应后台网站后台管理目录下的内容管理（比如我们首页的大广告，小广告什么都在这里发布）
 * </p>
 * 
 * @author Snailclimb
 * @version 1.0
 */
public interface ContentService {
	/**
	 * 添加内容
	 * 
	 * @param content
	 *            添加的内容对象
	 * @return 添加的结果
	 */
	E3Result addContent(TbContent content);

	/**
	 * 更新内容
	 * 
	 * @param content
	 *            更新后的内容对象
	 * @return 更新的结果
	 */
	E3Result updateContent(TbContent content);

	/**
	 * 根据内容分类id查询内容列表
	 */
	EasyUIDataGridResult getContentList(long categoryId, int page, int rows);

	/**
	 * 根据内容分类id查询内容列表，不带分页版（我实现的是这个接口，因为考虑一个内容分类下面不会有太多内容）
	 */
	List<TbContent> getContentListByCid(long cid);

	/**
	 * 
	 * @param contentId
	 *            前台传来的内容id的数组（用户可能选择了不止删除一个内容，所以这里传的是数组）
	 * @return
	 */
	E3Result deleteContent(@RequestParam("ids") long[] contentId);

}
