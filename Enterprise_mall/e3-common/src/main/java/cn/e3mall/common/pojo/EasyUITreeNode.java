package cn.e3mall.common.pojo;

import java.io.Serializable;

/**
 * <p>
 * 一个EasyUITreeNode对象实例便是tree的一个节点 { "id": 2, "text": "Node 2", "state":
 * "closed" }
 * </p>
 * 
 * @author Snailclimb
 * @version 1.0
 */
public class EasyUITreeNode implements Serializable {

	private static final long serialVersionUID = -3722391707720182282L;

	private long id;
	private String text;
	private String state;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
