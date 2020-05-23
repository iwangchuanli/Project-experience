package com.sf.minesweeper.listener;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.sf.minesweeper.bean.MineLabel;
import com.sf.minesweeper.dialog.Win;
import com.sf.minesweeper.frame.SartFrame;
import com.sf.minesweeper.panel.MineField;
import com.sf.minesweeper.tools.Tools;

public class MouseListener extends MouseAdapter {
	/**
	 * 标记雷区点击次数,第一次游戏开始
	 */
	private int mousePressedCount;            // 鼠标点击的次数
	private int expendedCount;                 //雷块展开的数量
	// MineField mineField;
	
	SartFrame sartframe;
	boolean isDouble ;
	int temp = Tools.totalMine;// 临时雷数
	boolean isStart;// 游戏是否开始
	
	
	public MouseListener(SartFrame sartframe) {
		this.sartframe = sartframe;
	}
    
	public void mousePressed(MouseEvent arg0) {
		
		MineLabel mineLabel = (MineLabel) arg0.getSource();
		int d = arg0.getModifiersEx();               // 返回此事件的修饰符掩码
		int d1 = arg0.getModifiers();
		// 获取当前点击的雷块和其行列索引
		int rowIndex = mineLabel.getRowIndex();
		int colIndex = mineLabel.getColIndex();
		// 记录雷区点击次数
		mousePressedCount++;

		// 如果是第一次点击雷区，则开始布雷
		if (mousePressedCount == 1) {
			sartframe.getMineField().buildMine(rowIndex, colIndex);
		}
		
		
		// 1左键按下
		if (d == InputEvent.BUTTON1_DOWN_MASK + InputEvent.BUTTON3_DOWN_MASK) {
			// 1 周围图片切换
			for (int x = Math.max(rowIndex - 1, 0); x <= Math.min(rowIndex + 1,
					Tools.totalx - 1); x++) {
				for (int y = Math.max(colIndex - 1, 0); y <= Math.min(
						colIndex + 1, Tools.totaly - 1); y++) {
					mineLabel = sartframe.getMineField().getMineLabel()[x][y];
					if (!mineLabel.isExpanded() && !mineLabel.isFlag())
						mineLabel.setIcon(Tools.mineCount[0]);
				}
			}
			// 2 表情变惊讶
			sartframe.getMineState().getNewGame().setIcon(Tools.iiface2);
			// 3 记录双键点击标志
			isDouble = true;

			// 2右键按下  且雷块未展开
		} else if (d1 == InputEvent.BUTTON3_MASK && !mineLabel.isExpanded()) {
			// 右键点击次数
			int clickCount = mineLabel.getRightClickCount();
			clickCount++;
			// 第一次，雷块>>>旗子
			if (clickCount == 1) {
				mineLabel.setIcon(Tools.iiflag);
				mineLabel.setRightClickCount(clickCount);
				mineLabel.setFlag(true);
				temp--;
				sartframe.getMineState().setTotalMineG(temp);
			}

			// 第二次，旗子>>>问号
			if (clickCount == 2) {
				mineLabel.setIcon(Tools.iiask0);
				mineLabel.setRightClickCount(clickCount);
				mineLabel.setFlag(false);
				temp++;
				sartframe.getMineState().setTotalMineG(temp);

			}
			// 第三次，问号>>>雷块
			if (clickCount == 3) {
				mineLabel.setIcon(Tools.iiblank);
				mineLabel.setRightClickCount(0);
			}
			// 3左键按下

		} else if (d1 == InputEvent.BUTTON1_MASK) {
			// 未展开且不是旗子(图片陷进去)
			if (!mineLabel.isExpanded() && !mineLabel.isFlag()) {
				// 问号
				if (mineLabel.getRightClickCount() == 2) {
					mineLabel.setIcon(Tools.iiask1);
					// 雷块
				} else {
					mineLabel.setIcon(Tools.mineCount[0]);
				}
				// 表情>>惊讶
				sartframe.getMineState().getNewGame().setIcon(Tools.iiface2);
			}

			if (mineLabel.isExpanded()) {
				sartframe.getMineState().getNewGame().setIcon(Tools.iiface0);
			}

		}

	}

	/**
	 * 鼠标释放
	 */
	public void mouseReleased(MouseEvent arg0) {

		MineLabel mineLabel = (MineLabel) arg0.getSource();
		
		int rowIndex = mineLabel.getRowIndex();
		int colIndex = mineLabel.getColIndex();

		int i = arg0.getModifiers();
		
		
		// 左键释放
		if (isDouble) {
			// 2 表情惊讶还原
			sartframe.getMineState().getNewGame().setIcon(Tools.iiface0);
			doubleReleased(rowIndex, colIndex, mineLabel.getMineCount());
			isDouble = false;
			isMind();

			
			
			// 左键释放
		} else if (i == InputEvent.BUTTON1_MASK) {
			if (!mineLabel.isExpanded() && !mineLabel.isFlag()) {
				/**
				 * 鼠标第一次点击雷区时计时器开始计时
				 */

				if (!sartframe.isStart()) {
					sartframe.getTimer().start();
					sartframe.setStart(true); // 游戏开始
				}
				// 踩到雷,展开所有雷

				if (mineLabel.isMine() && !mineLabel.isFlag()) {
					openMine(rowIndex, colIndex);

					/**
					 * 踩到雷时计时器停止计时，游戏结束，
					 */
					sartframe.getMineState().getNewGame()
							.setIcon(Tools.iiface3);

					sartframe.getTimer().stop();
					sartframe.setStart(false);

					// 没有踩到雷,展开安全雷区
				} else {
					sartframe.getMineState().getNewGame()
							.setIcon(Tools.iiface0);
					open(rowIndex, colIndex);
					isMind();
				}
			} else if (mineLabel.isExpanded()) {
				sartframe.getMineState().getNewGame().setIcon(Tools.iiface0);
			}
		}
	}


	
	public void doubleReleased(int rowIndex, int colIndex, int count) {
		/**
		 * flagBeside 周围8个位置的旗子数
		 */
		int flagBeside = 0;
		for (int x = Math.max(rowIndex - 1, 0); x <= Math.min(rowIndex + 1,
				Tools.totalx - 1); x++) {
			for (int y = Math.max(colIndex - 1, 0); y <= Math.min(colIndex + 1,
					Tools.totaly - 1); y++) {
				if (sartframe.getMineField().getMineLabel()[x][y].isFlag()) {
					flagBeside++;
				}

			}
		}

		// 1 当前雷块未展开或者旗子数与数字不相等，图片还原
		if (!sartframe.getMineField().getMineLabel()[rowIndex][colIndex]
				.isExpanded()
				|| sartframe.getMineField().getMineLabel()[rowIndex][colIndex]
						.getMineCount() != flagBeside) {
			doublePressedBeside(rowIndex, colIndex, 2);
		}

		// 2 已经展开且周围雷数和旗子相等，判断周围8个方向是否有踩到雷(是雷，但没有旗子)
		if (sartframe.getMineField().getMineLabel()[rowIndex][colIndex]
				.isExpanded()
				&& sartframe.getMineField().getMineLabel()[rowIndex][colIndex]
						.getMineCount() == flagBeside) {
			boolean isBobm = false;
			for (int x = Math.max(rowIndex - 1, 0); x <= Math.min(rowIndex + 1,
					Tools.totalx - 1); x++) {
				for (int y = Math.max(colIndex - 1, 0); y <= Math.min(
						colIndex + 1, Tools.totaly - 1); y++) {
					if (sartframe.getMineField().getMineLabel()[x][y].isMine()
							&& !sartframe.getMineField().getMineLabel()[x][y]
									.isFlag()) {
						isBobm = true;
						break;
					} else if (!sartframe.getMineField().getMineLabel()[x][y]
							.isExpanded()) {
						open(x, y);
					}
				}
			}
			// 踩到雷
			if (isBobm) {
				openMine(rowIndex, colIndex);
			}

		}

	}

	
	public void doublePressedBeside(int rowIndex, int colIndex, int doubleType) {

		doublePressed(rowIndex, colIndex, doubleType);

		for (int x = Math.max(rowIndex - 1, 0); x <= Math.min(rowIndex + 1,
				Tools.totalx - 1); x++) {
			for (int y = Math.max(colIndex - 1, 0); y <= Math.min(colIndex + 1,
					Tools.totaly - 1); y++) {
				doublePressed(x, y, doubleType);
			}
		}

	}
	
	public void doublePressed(int i, int j, int doubleType) {
		MineLabel minelabel = sartframe.getMineField().getMineLabel()[i][j];

		if (!minelabel.isExpanded() && !minelabel.isFlag()) {

			if (doubleType == 1) {

				if (minelabel.getRightClickCount() == 2) {

					minelabel.setIcon(Tools.iiask1);

				} else {
					minelabel.setIcon(Tools.mineCount[0]);

				}
			} else {
				if (minelabel.getRightClickCount() == 2) {
					minelabel.setIcon(Tools.iiask0);
				} else {

					minelabel.setIcon(Tools.iiblank);

				}
			}
		}
	}

	public void openMine(int i, int j) {
		for (int m = 0; m < Tools.totalx; m++) {
			for (int n = 0; n < Tools.totaly; n++) {
				MineLabel mineLabel = sartframe.getMineField().getMineLabel()[m][n];
				//是雷的情况  且不是旗子
				if (mineLabel.isMine() && !mineLabel.isFlag()) {
					if (i == m && j == n) {
						mineLabel.setIcon(Tools.iiblood);
					} else {
						mineLabel.setIcon(Tools.iimine);
					}
				} else if (!mineLabel.isMine() && mineLabel.isFlag()) {
					mineLabel.setIcon(Tools.iierror);
					sartframe.getMineState().getNewGame()
					.setIcon(Tools.iiface3);
					sartframe.getTimer().stop();
					sartframe.setStart(false);
					
				}
				mineLabel.removeMouseListener(sartframe.getMineField()
						.getMouseListener());
			}
		}
	}


	
	private void open(int rowIndex, int colIndex) {

		MineLabel mineLabel = sartframe.getMineField().getMineLabel()[rowIndex][colIndex];
		if (!mineLabel.isExpanded() && !mineLabel.isFlag()) {
			int count = mineLabel.getMineCount();
			mineLabel.setIcon(Tools.mineCount[count]);
			mineLabel.setExpanded(true);
			expendedCount++;
			if (count == 0) {
				for (int x = Math.max(rowIndex - 1, 0); x <= Math.min(
						rowIndex + 1, Tools.totalx - 1); x++) {
					for (int y = Math.max(colIndex - 1, 0); y <= Math.min(
							colIndex + 1, Tools.totaly - 1); y++) {
						open(x, y);
					}
				}
			}
		}

	}


	public void isMind() {
		// TODO Auto-generated method stubt;
		if (Tools.totalx * Tools.totaly - expendedCount == Tools.totalMine) {

			for (int g = 0; g < Tools.totalx; g++)
				for (int h = 0; h < Tools.totaly; h++) {
					if (sartframe.getMineField().getMineLabel()[g][h].isMine()
							&& !sartframe.getMineField().getMineLabel()[g][h]
									.isFlag()) {
						sartframe.getMineField().getMineLabel()[g][h]
								.setIcon(Tools.iiflag);
					}
					// 移除监听
					sartframe.getMineField().getMineLabel()[g][h]
							.removeMouseListener(sartframe.getMineField()
									.getMouseListener());

				}
			sartframe.getMineState().getNewGame().setIcon(Tools.iiface4);

			sartframe.getMineState().setTotalMineG(0);

			sartframe.getTimer().stop();
			new Win(sartframe);
			

			//成功后弹出英雄记录版
			
			 
			sartframe.setStart(false);
		}

	}


}
