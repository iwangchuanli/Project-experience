package com.chap6;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Graphics;

public class UseButton extends Applet{

	String str1 = new String();
	//声明按钮对象
	Button b1;
	Button b2;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		//构造对象
		b1 = new Button();
		b2 = new Button("按钮对象2");
		//添加到界面
		this.add(b1);
		this.add(b2);
	}
	@Override
	public void start() {
		// TODO Auto-generated method stub
		b1.setLabel("按钮对象1");
		str1 = b2.getLabel();
		repaint();
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawString(str1, 20, 30);
	}
	
}
