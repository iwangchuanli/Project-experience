package com.sf.minesweeper.bean;
 
import javax.swing.JLabel;
	/**
	 * 雷块模型
	 */
public class MineLabel extends JLabel {
	private static final long serialVersionUID = -7271927020145498558L;
	/**
	 * 判断是否是雷
	 * */
	private boolean isMine;
	/**
	 * 判断雷块是否展开
	 * */
	private boolean isExpanded;
	/**
	 * 判断雷块是否是旗子
	 * */
	private boolean isFlag;
	/**
	 * 判断是否是雷且未标上旗子
	 * */
	private boolean isMineAndNotflag;
	/**
	 * count 计算雷块周围的雷数
	 * */
	private int mineCount;
	/**
	 * 雷块所在的行
	 * */
	private int rowIndex;
	/**
	 * 雷块所在的列
	 * */
	private int colIndex;
	private int expend=0;
	//雷区上的行列坐标
	public MineLabel(int rowIndex, int colIndex) {
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;
	}
	//一些对应的get和set方法
	public boolean isMine() {
		return isMine;
	}

	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

	public boolean isFlag() {
		return isFlag;
	}

	public void setFlag(boolean isFlag) {
		this.isFlag = isFlag;
	}

	public int getMineCount() {
		return mineCount;
	}

	public void setMineCount(int mineCount) {
		this.mineCount = mineCount;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getColIndex() {
		return colIndex;
	}

	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
		
		
	}
	/**
	 * rightClickCount 右键点击次数
	 * */
	private int rightClickCount;

	public int getRightClickCount() {
		return rightClickCount;
	}

	public void setRightClickCount(int rightClickCount) {
		this.rightClickCount = rightClickCount;
	}

}



