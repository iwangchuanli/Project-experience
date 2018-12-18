package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.service.ItemCatService;

/**
 * 商品类目选择功能的实现就是获取商品选择类目的列表
 * 
 * @author Snailclimb
 * @version 1.0
 */
@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	/**
	 * 获取商品类目（类别）的Handle
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {

		List<EasyUITreeNode> list = itemCatService.getCatList(parentId);
		return list;
	}
}
