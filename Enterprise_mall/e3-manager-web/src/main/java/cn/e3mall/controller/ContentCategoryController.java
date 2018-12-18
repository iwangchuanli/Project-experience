package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;

/**
 * <p>
 * 内容分类管理Controller
 * <p>
 * 
 * @author Snailclimb
 * @version 1.0
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;

	/**
	 * 获取内容分类列表的Handle
	 * 
	 * @param parentId
	 *            父节点id
	 * @return 包含内容分类列表list
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {

		List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
		return list;
	}

	/**
	 * 创建内容分类列表的Handle
	 * 
	 * @param parentId
	 *            父节点id
	 * @param name
	 * @return 添加内容分类列表的结果
	 */
	@RequestMapping("/create")
	@ResponseBody
	public E3Result createCategory(Long parentId, String name) {
		E3Result result = contentCategoryService.addContentCategory(parentId, name);
		return result;
	}

	/**
	 * 更新内容分类列表的Handle
	 * 
	 * @param id
	 *            内容分类id
	 * @param name
	 *            内容分类名字
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public E3Result update(Long id, String name) {
		E3Result result = contentCategoryService.upadateContentCategory(id, name);
		return result;
	}

	/**
	 * 删除内容分类的Handle
	 * 
	 * @param id
	 *            内容分类id
	 * @return 删除的结果
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public E3Result update(Long id) {
		E3Result result = contentCategoryService.deleteContentCategory(id);
		return result;
	}

}
