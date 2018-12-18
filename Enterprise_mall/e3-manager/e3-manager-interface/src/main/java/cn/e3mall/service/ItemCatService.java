package cn.e3mall.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;

/**
 * <p>
 * 获取商品类目（类别）接口
 * </p>
 * 
 * @author Snailclimb
 * @version 1.0
 */
public interface ItemCatService {

	List<EasyUITreeNode> getCatList(long parentId);
}
