package com.chap6;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Test_view extends JFrame{

	Button button = new Button("��ť����");
	JLabel label = new JLabel("��ǩ����");
	JRadioButton radBtn = new JRadioButton("��ѡ��ť����");
	JTextField textFid = new JTextField("�ı������");
	JTextArea textArea = new JTextArea("�ı��������");
	
	JPanel panel = new JPanel();
	public Test_view() {
		panel.add(label);
		panel.add(button);
		panel.add(radBtn);
		panel.add(textFid);
		panel.add(textArea);
		textArea.setSize(50, 60);
		
		getContentPane().add(panel, BorderLayout.NORTH);
	}
	
	public static void main(String[] args) {
		Test_view view = new Test_view();
		view.setVisible(true);
		view.setSize(500, 600);
		view.setTitle("�������Ԫ��");
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setLocation(null);
		
	}
}