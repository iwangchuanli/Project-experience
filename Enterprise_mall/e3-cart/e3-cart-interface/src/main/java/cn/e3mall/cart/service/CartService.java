package cn.e3mall.cart.service;

import java.util.List;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;

/**
 * <p>
 * 处理购物车相关的一些接口
 * </p>
 * 
 * @author Snailclimb
 * @version 1.0
 */
public interface CartService {
	/**
	 * 添加购物车的方法
	 * 
	 * @param userId
	 *            用户的id
	 * @param itemId
	 *            商品id
	 * @param num
	 *            商品数量
	 * @return 添加成功的消息（200）
	 */
	E3Result addCart(long userId, long itemId, int num);

	/**
	 * 合并购物车的方法
	 * 
	 * @param userId
	 *            用户的id
	 * @param itemList
	 *            商品列表
	 * @return
	 */
	E3Result mergeCart(long userId, List<TbItem> itemList);

	List<TbItem> getCartList(long userId);

	E3Result updateCartNum(long userId, long itemId, int num);

	E3Result deleteCartItem(long userId, long itemId);
}
