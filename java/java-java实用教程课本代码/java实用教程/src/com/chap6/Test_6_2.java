package com.chap6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Scrollbar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ���һ����ǩ�������������������������ȡֵ��Χ��0~255
 * ��ʹ�ù�������ȡֵ��Ӧ��ɫ����ĺ����������ɫ��ֵ����ʼ��ǩΪ��ɫ
 * @author Administrator
 *
 */
public class Test_6_2 extends JFrame{

	Scrollbar s1,s2,s3;
	JLabel label = new JLabel("��ǩ����");
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
