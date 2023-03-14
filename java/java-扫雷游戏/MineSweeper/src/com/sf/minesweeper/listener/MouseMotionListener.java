package com.sf.minesweeper.listener;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import com.sf.minesweeper.bean.MineLabel;

public class MouseMotionListener implements java.awt.event.MouseMotionListener{

	public void mouseDragged(MouseEvent e) {
		// ��ȡ��ǰ������׿������������
		MineLabel mineLabel = (MineLabel) e.getSource();
		int rowIndex = mineLabel.getRowIndex();
		int colIndex = mineLabel.getColIndex();
		// ��ȡ�������
		int d = e.getModifiersEx();
		int i = e.getModifiers();

		if (d == InputEvent.BUTTON1_DOWN_MASK + InputEvent.BUTTON3_DOWN_MASK) {

		} else if (i == InputEvent.BUTTON3_MASK && !mineLabel.isExpanded()) {

		} else if (i == InputEvent.BUTTON1_MASK) {

		}
	}

	public void mouseMoved(MouseEvent e) {
		// ��ȡ��ǰ������׿������������
		MineLabel mineLabel = (MineLabel) e.getSource();
		int rowIndex = mineLabel.getRowIndex();
		int colIndex = mineLabel.getColIndex();
		// ��ȡ�������
		int d = e.getModifiersEx();
		int i = e.getModifiers();

		if (d == InputEvent.BUTTON1_DOWN_MASK + InputEvent.BUTTON3_DOWN_MASK) {

		} else if (i == InputEvent.BUTTON3_MASK && !mineLabel.isExpanded()) {

		} else if (i == InputEvent.BUTTON1_MASK) {

		}
	}

}
