package cn.e3mall.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 分页使用的Pojo对象，包含查询到的总记录数和每页显示条数两个属性
 * </p>
 * 
 * @author Snailclimb
 *
 */
public class EasyUIDataGridResult implements Serializable {

	private static final long serialVersionUID = 2042280482739974978L;
	private long total;
	private List rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
