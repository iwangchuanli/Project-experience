package com.chap6;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Graphics;

public class UseButton extends Applet{

	String str1 = new String();
	//������ť����
	Button b1;
	Button b2;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		//�������
		b1 = new Button();
		b2 = new Button("��ť����2");
		//��ӵ�����
		this.add(b1);
		this.add(b2);
	}
	@Override
	public void start() {
		// TODO Auto-generated method stub
		b1.setLabel("��ť����1");
		str1 = b2.getLabel();
		repaint();
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawString(str1, 20, 30);
	}
	
}
