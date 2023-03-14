package com.chap6;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UseCheckbox extends JFrame{

	String str1 = new String();
	boolean b1 = false;
	boolean b2 = false;
	Checkbox c1,c2,c3;
	Checkbox cRadio1,cRadio2;
	CheckboxGroup c;
	JPanel panel = new JPanel();
	public UseCheckbox() {
		c1 = new Checkbox();
		c1.setLabel("复选框对象1");
		c2 = new Checkbox("复选框对象2");
		c3 = new Checkbox("复选框对象3",true);
		//构造单选按钮
		c = new CheckboxGroup();
		cRadio1 = new Checkbox("单选按钮1",c,false);
		cRadio2 = new Checkbox("单选按钮2", c ,true);
		//添加到界面
		panel.add(c1);
		panel.add(c2);
		panel.add(c3);
		panel.add(cRadio1);
		panel.add(cRadio2);
		getContentPane().add(panel,BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		UseCheckbox uck = new UseCheckbox();
		uck.setVisible(true);
		uck.setSize(500, 600);
		uck.setTitle("复选框与单选按钮");
		uck.setLocation(null);
		uck.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
