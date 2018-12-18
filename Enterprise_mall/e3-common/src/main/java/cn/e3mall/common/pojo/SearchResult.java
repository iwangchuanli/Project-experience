package cn.e3mall.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 查询结果使用的Pojo对象，包含查询到的总记录数，每页放的商品数以及查询到的商品列表这三个属性
 * </p>
 * 
 * @author Snailclimb
 *
 */
public class SearchResult implements Serializable {

	private static final long serialVersionUID = -653086593472374273L;

	private long recordCount;
	private int totalPages;
	private List<SearchItem> itemList;

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<SearchItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}

}
