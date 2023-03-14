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
	 * ��������������,��һ����Ϸ��ʼ
	 */
	private int mousePressedCount;            // ������Ĵ���
	private int expendedCount;                 //�׿�չ��������
	// MineField mineField;
	
	SartFrame sartframe;
	boolean isDouble ;
	int temp = Tools.totalMine;// ��ʱ����
	boolean isStart;// ��Ϸ�Ƿ�ʼ
	
	
	public MouseListener(SartFrame sartframe) {
		this.sartframe = sartframe;
	}
    
	public void mousePressed(MouseEvent arg0) {
		
		MineLabel mineLabel = (MineLabel) arg0.getSource();
		int d = arg0.getModifiersEx();               // ���ش��¼������η�����
		int d1 = arg0.getModifiers();
		// ��ȡ��ǰ������׿������������
		int rowIndex = mineLabel.getRowIndex();
		int colIndex = mineLabel.getColIndex();
		// ��¼�����������
		mousePressedCount++;

		// ����ǵ�һ�ε����������ʼ����
		if (mousePressedCount == 1) {
			sartframe.getMineField().buildMine(rowIndex, colIndex);
		}
		
		
		// 1�������
		if (d == InputEvent.BUTTON1_DOWN_MASK + InputEvent.BUTTON3_DOWN_MASK) {
			// 1 ��ΧͼƬ�л�
			for (int x = Math.max(rowIndex - 1, 0); x <= Math.min(rowIndex + 1,
					Tools.totalx - 1); x++) {
				for (int y = Math.max(colIndex - 1, 0); y <= Math.min(
						colIndex + 1, Tools.totaly - 1); y++) {
					mineLabel = sartframe.getMineField().getMineLabel()[x][y];
					if (!mineLabel.isExpanded() && !mineLabel.isFlag())
						mineLabel.setIcon(Tools.mineCount[0]);
				}
			}
			// 2 ����侪��
			sartframe.getMineState().getNewGame().setIcon(Tools.iiface2);
			// 3 ��¼˫�������־
			isDouble = true;

			// 2�Ҽ�����  ���׿�δչ��
		} else if (d1 == InputEvent.BUTTON3_MASK && !mineLabel.isExpanded()) {
			// �Ҽ��������
			int clickCount = mineLabel.getRightClickCount();
			clickCount++;
			// ��һ�Σ��׿�>>>����
			if (clickCount == 1) {
				mineLabel.setIcon(Tools.iiflag);
				mineLabel.setRightClickCount(clickCount);
				mineLabel.setFlag(true);
				temp--;
				sartframe.getMineState().setTotalMineG(temp);
			}

			// �ڶ��Σ�����>>>�ʺ�
			if (clickCount == 2) {
				mineLabel.setIcon(Tools.iiask0);
				mineLabel.setRightClickCount(clickCount);
				mineLabel.setFlag(false);
				temp++;
				sartframe.getMineState().setTotalMineG(temp);

			}
			// �����Σ��ʺ�>>>�׿�
			if (clickCount == 3) {
				mineLabel.setIcon(Tools.iiblank);
				mineLabel.setRightClickCount(0);
			}
			// 3�������

		} else if (d1 == InputEvent.BUTTON1_MASK) {
			// δչ���Ҳ�������(ͼƬ�ݽ�ȥ)
			if (!mineLabel.isExpanded() && !mineLabel.isFlag()) {
				// �ʺ�
				if (mineLabel.getRightClickCount() == 2) {
					mineLabel.setIcon(Tools.iiask1);
					// �׿�
				} else {
					mineLabel.setIcon(Tools.mineCount[0]);
				}
				// ����>>����
				sartframe.getMineState().getNewGame().setIcon(Tools.iiface2);
			}

			if (mineLabel.isExpanded()) {
				sartframe.getMineState().getNewGame().setIcon(Tools.iiface0);
			}

		}

	}

	/**
	 * ����ͷ�
	 */
	public void mouseReleased(MouseEvent arg0) {

		MineLabel mineLabel = (MineLabel) arg0.getSource();
		
		int rowIndex = mineLabel.getRowIndex();
		int colIndex = mineLabel.getColIndex();

		int i = arg0.getModifiers();
		
		
		// ����ͷ�
		if (isDouble) {
			// 2 ���龪�Ȼ�ԭ
			sartframe.getMineState().getNewGame().setIcon(Tools.iiface0);
			doubleReleased(rowIndex, colIndex, mineLabel.getMineCount());
			isDouble = false;
			isMind();

			
			
			// ����ͷ�
		} else if (i == InputEvent.BUTTON1_MASK) {
			if (!mineLabel.isExpanded() && !mineLabel.isFlag()) {
				/**
				 * ����һ�ε������ʱ��ʱ����ʼ��ʱ
				 */

				if (!sartframe.isStart()) {
					sartframe.getTimer().start();
					sartframe.setStart(true); // ��Ϸ��ʼ
				}
				// �ȵ���,չ��������

				if (mineLabel.isMine() && !mineLabel.isFlag()) {
					openMine(rowIndex, colIndex);

					/**
					 * �ȵ���ʱ��ʱ��ֹͣ��ʱ����Ϸ������
					 */
					sartframe.getMineState().getNewGame()
							.setIcon(Tools.iiface3);

					sartframe.getTimer().stop();
					sartframe.setStart(false);

					// û�вȵ���,չ����ȫ����
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
		 * flagBeside ��Χ8��λ�õ�������
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

		// 1 ��ǰ�׿�δչ�����������������ֲ���ȣ�ͼƬ��ԭ
		if (!sartframe.getMineField().getMineLabel()[rowIndex][colIndex]
				.isExpanded()
				|| sartframe.getMineField().getMineLabel()[rowIndex][colIndex]
						.getMineCount() != flagBeside) {
			doublePressedBeside(rowIndex, colIndex, 2);
		}

		// 2 �Ѿ�չ������Χ������������ȣ��ж���Χ8�������Ƿ��вȵ���(���ף���û������)
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
			// �ȵ���
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
				//���׵����  �Ҳ�������
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
					// �Ƴ�����
					sartframe.getMineField().getMineLabel()[g][h]
							.removeMouseListener(sartframe.getMineField()
									.getMouseListener());

				}
			sartframe.getMineState().getNewGame().setIcon(Tools.iiface4);

			sartframe.getMineState().setTotalMineG(0);

			sartframe.getTimer().stop();
			new Win(sartframe);
			

			//�ɹ��󵯳�Ӣ�ۼ�¼��
			
			 
			sartframe.setStart(false);
		}

	}


}
