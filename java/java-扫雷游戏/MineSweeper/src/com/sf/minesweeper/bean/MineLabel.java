package com.sf.minesweeper.bean;
 
import javax.swing.JLabel;
	/**
	 * �׿�ģ��
	 */
public class MineLabel extends JLabel {
	private static final long serialVersionUID = -7271927020145498558L;
	/**
	 * �ж��Ƿ�����
	 * */
	private boolean isMine;
	/**
	 * �ж��׿��Ƿ�չ��
	 * */
	private boolean isExpanded;
	/**
	 * �ж��׿��Ƿ�������
	 * */
	private boolean isFlag;
	/**
	 * �ж��Ƿ�������δ��������
	 * */
	private boolean isMineAndNotflag;
	/**
	 * count �����׿���Χ������
	 * */
	private int mineCount;
	/**
	 * �׿����ڵ���
	 * */
	private int rowIndex;
	/**
	 * �׿����ڵ���
	 * */
	private int colIndex;
	private int expend=0;
	//�����ϵ���������
	public MineLabel(int rowIndex, int colIndex) {
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;
	}
	//һЩ��Ӧ��get��set����
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
	 * rightClickCount �Ҽ��������
	 * */
	private int rightClickCount;

	public int getRightClickCount() {
		return rightClickCount;
	}

	public void setRightClickCount(int rightClickCount) {
		this.rightClickCount = rightClickCount;
	}

}



