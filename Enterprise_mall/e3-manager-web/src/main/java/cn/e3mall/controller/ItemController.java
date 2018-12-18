package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;

/**
 * 商品的列表的查询，商品的添加，删除，上架以及下架功能的实现 目前：商品编辑功能暂未实现
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 根据商品id返回商品信息的json格式数据（当时为了测试ssm框架是否走通写的测试方法）
	 * 
	 * @param itemId
	 * 
	 *            要查询商品的id
	 * @return
	 */
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}

	/**
	 * 商品列表查询
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		// 调用服务查询商品列表
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}

	/**
	 * 商品添加功能
	 */
	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	@ResponseBody
	public E3Result addItem(TbItem item, String desc) {
		E3Result result = itemService.addItem(item, desc);
		return result;
	}

	/**
	 * 删除选中的商品
	 * 
	 * @param itemId
	 *            选中商品的数组
	 * @return 删除商品的结果
	 */
	@RequestMapping(value = "/rest/item/delete", method = RequestMethod.POST)
	@ResponseBody
	private E3Result deleteItem(@RequestParam("ids") long[] itemId) {
		E3Result result = itemService.deleteItem(itemId);
		return result;
	}

	/**
	 * 上架商品
	 */
	@RequestMapping(value = "/rest/item/reshelf", method = RequestMethod.POST)
	@ResponseBody
	private E3Result upperoffItem(@RequestParam("ids") long[] itemId, TbItem item) {
		E3Result result = itemService.upperoffItem(itemId, item);
		return result;
	}

	/**
	 * 下架商品
	 */
	@RequestMapping(value = "/rest/item/instock", method = RequestMethod.POST)
	@ResponseBody
	private E3Result dropoffItem(@RequestParam("ids") long[] itemId, TbItem item) {
		E3Result result = itemService.dropoffItem(itemId, item);
		return result;
	}

}
