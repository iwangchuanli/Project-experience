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
 * �������
 * @author Administrator
 *
 */
public class Test_6_1 extends JFrame{

	JLabel titleLab = new JLabel("��������");
	JLabel nameLab = new JLabel("��ʵ����");
	JLabel sexLab = new JLabel("�Ա�");
	JLabel birdsdayLab = new JLabel("��������");
	JLabel typeLab = new JLabel("֤������");
	JLabel numberLab = new JLabel("֤������");
	
	JTextField nameTex = new JTextField(20);
	
	ButtonGroup group = new ButtonGroup();
	JRadioButton male = new JRadioButton("��");
	JRadioButton famle = new JRadioButton("Ů");
	
	JTextField year = new JTextField("year",4);
	JTextField month = new JTextField("month",4);
	JTextField day = new JTextField("day",4);
	
	JComboBox cmbType = new JComboBox();
	
	JTextField numberTex = new JTextField(20);
	
	JButton btnOk = new JButton("ȷ��");
	JButton btnReset = new JButton("����ȫ����Ϣ");
	
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	
	public Test_6_1() {
		cmbType.addItem("���֤");
		cmbType.addItem("����֤");
		cmbType.addItem("ѧ��֤");
		cmbType.addItem("����");
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
		ts.setTitle("��������");
		ts.setVisible(true);
		ts.setSize(400, 500);
		ts.show();
		ts.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
