package com.chap6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Scrollbar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 设计一个标签区域和三个滚动条，滚动条的取值范围是0~255
 * 即使得滚动条的取值对应颜色对象的红黄蓝三个基色的值，初始标签为红色
 * @author Administrator
 *
 */
public class Test_6_2 extends JFrame{

	Scrollbar s1,s2,s3;
	JLabel label = new JLabel("标签对象");
	JPanel p = new JPanel();
	Color c;
	public Test_6_2() {
		s1 = new Scrollbar(Scrollbar.VERTICAL,255,0,0,255);
		s2 = new Scrollbar(Scrollbar.VERTICAL,0,0,0,255);
		s3 = new Scrollbar(Scrollbar.VERTICAL,0,0,0,255);
		c = new Color(s1.getValue(), s2.getValue() , s3.getValue());
		label.setBackground(c);
		p.add(s1);p.add(s2);p.add(s3);p.add(label);
		getContentPane().add(p, BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		Test_6_2 test = new Test_6_2();
		test.setVisible(true);
		test.setSize(500, 600);
		test.setLocation(null);
		test.setTitle("test.6.2");
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
