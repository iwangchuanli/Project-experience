package cn.e3mall.common.pojo;

import java.io.Serializable;

/**
 * <p>
 * 查询商品时使用的Pojo对象，包含商品的id,title,sell_potint（卖点）等属性
 * </p>
 * 
 * @author Snailclimb
 *
 */
public class SearchItem implements Serializable {
	/**
	 * 生成序列化id
	 */
	private static final long serialVersionUID = -4170161720508278704L;
	private String id;
	private String title;
	private String sell_point;
	private long price;
	private String image;
	private String category_name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSell_point() {
		return sell_point;
	}

	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String[] getImages() {
		if (image != null && !"".equals(image)) {
			String[] strings = image.split(",");
			return strings;
		}
		return null;
	}

}
