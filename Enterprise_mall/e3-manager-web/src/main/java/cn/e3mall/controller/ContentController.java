package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;

/**
 * <p>
 * 内容管理Controller
 * <p>
 * 
 * @author Snailclimb
 * @version 1.0
 */
@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;

	/**
	 * 添加内容的Handle
	 * 
	 * @param content
	 *            添加的内容对对象
	 * @return 添加的结果
	 */
	@RequestMapping("/content/save")
	@ResponseBody
	public E3Result addContent(TbContent content) {
		E3Result result = contentService.addContent(content);
		return result;
	}

	/**
	 * 更新内容的Handle
	 * 
	 * @param content
	 *            更新后的内容对象
	 * @return 更新的结果
	 */
	@RequestMapping("/content/update")
	@ResponseBody
	public E3Result updateContent(TbContent content) {
		E3Result result = contentService.updateContent(content);
		return result;
	}

	/**
	 * 内容管理列表查询的Handle
	 * 
	 * @param categoryId
	 *            内容分类id(比如是大广告下的内容还是小广告下的内容
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(long categoryId, int page, int rows) {
		EasyUIDataGridResult content = contentService.getContentList(categoryId, page, rows);
		return content;
	}

	/**
	 * 删除内容的Handle
	 * 
	 * @param itemId
	 *            前台传来的内容id的数组（用户可能选择了不止删除一个内容，所以这里传的是数组）
	 * @return
	 */
	@RequestMapping("/content/delete")
	@ResponseBody
	public E3Result deleteContent(@RequestParam("ids") long[] itemId) {
		E3Result result = contentService.deleteContent(itemId);
		return result;
	}

}
