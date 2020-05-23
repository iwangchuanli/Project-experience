package com.chap6;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * 界面设计
 * @author Administrator
 *
 */
public class Test_6_1 extends JFrame{

	JLabel titleLab = new JLabel("个人资料");
	JLabel nameLab = new JLabel("真实姓名");
	JLabel sexLab = new JLabel("性别");
	JLabel birdsdayLab = new JLabel("出生日期");
	JLabel typeLab = new JLabel("证件类型");
	JLabel numberLab = new JLabel("证件号码");
	
	JTextField nameTex = new JTextField(20);
	
	ButtonGroup group = new ButtonGroup();
	JRadioButton male = new JRadioButton("男");
	JRadioButton famle = new JRadioButton("女");
	
	JTextField year = new JTextField("year",4);
	JTextField month = new JTextField("month",4);
	JTextField day = new JTextField("day",4);
	
	JComboBox cmbType = new JComboBox();
	
	JTextField numberTex = new JTextField(20);
	
	JButton btnOk = new JButton("确定");
	JButton btnReset = new JButton("重填全部信息");
	
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	
	public Test_6_1() {
		cmbType.addItem("身份证");
		cmbType.addItem("军人证");
		cmbType.addItem("学生证");
		cmbType.addItem("护照");
		group.add(male);
		group.add(famle);
		
		p1.add(nameLab);p1.add(sexLab);p1.add(birdsdayLab);
		p1.add(typeLab);p1.add(numberLab);
		
		p2.add(titleLab);p2.add(nameTex);p2.add(male);
		p2.add(famle);
		p2.add(year);p2.add(month);p2.add(day);
		p2.add(cmbType);
		p2.add(numberTex);
		
		p1.setLayout(new FlowLayout(FlowLayout.RIGHT,30,10));
		p2.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
		p3.add(p1,BorderLayout.EAST);
		p3.add(p2,BorderLayout.WEST);
		
		p4.add(btnOk);p4.add(btnReset);
		getContentPane().add(p3, BorderLayout.CENTER);
		getContentPane().add(p4, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		Test_6_1 ts = new Test_6_1();
		ts.setTitle("个人资料");
		ts.setVisible(true);
		ts.setSize(400, 500);
		ts.show();
		ts.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
