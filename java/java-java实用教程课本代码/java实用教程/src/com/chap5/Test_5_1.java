package com.chap5;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 * ʹ��Graphics�����ͼ�λ��ƣ�Ҫ���������ɫ���κͺ�ɫ��Բ�Σ�Ȼ��ֱ�������
 * 
 * @author Administrator
 *
 */
public class Test_5_1 {

	public static void main(String[] args) {
		JFrame frame =new JFrame("��״��ӡ");
		myJpanel1 panel = new myJpanel1();
		frame.add(panel);
		

		//���ô�С
		frame.setSize(800, 600);
		//�رշ���
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ÿɼ�
		frame.setVisible(true);
		//���þ���
		frame.setLocationRelativeTo(null);
		panel.setFocusable(true);
	}
	
}
class myJpanel1 extends JPanel{
	public myJpanel1() {
		// TODO Auto-generated constructor stub
	}
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Color c = Color.blue;
		g.drawRect(50, 50, 200, 100);
		g.drawOval(300, 50, 200, 100);
		g.setColor(c);
		g.fillRect(50, 160, 200, 100);
		c = Color.red;
		g.setColor(c);
		g.fillOval(300, 160, 200, 100);
		
		
		//Font ft = new Font("", Font.BOLD, 28);
		//g.setFont(ft);
		
		//g.drawLine(0, 500, 800, 500);
		
	}
}
