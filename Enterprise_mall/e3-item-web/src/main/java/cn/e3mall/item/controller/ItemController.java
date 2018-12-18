package cn.e3mall.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.e3mall.item.pojo.Item;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.ItemService;

/**
 * <p>
 * 商品详情展示Controller
 * </p>
 * 
 * @author Snailclimb
 * @version 1.0
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 商品详情页面展示的Handle
	 * 
	 * @param itemId
	 *            商品id
	 * @param model
	 *            向前台页面传值的时候使用
	 * @return 商品详情页面的视图
	 */
	@RequestMapping("/item/{itemId}")
	public String showItemInfo(@PathVariable Long itemId, Model model) {
		// 调用服务取商品基本信息
		TbItem tbItem = itemService.getItemById(itemId);
		// 我们想要的是继承tbItem的item类（item类中有我们写的getImages方法）
		Item item = new Item(tbItem);
		// 取商品描述信息
		TbItemDesc itemDesc = itemService.getItemDescById(itemId);
		// 向页面传值
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", itemDesc);
		// 返回逻辑视图
		return "item";
	}
}
