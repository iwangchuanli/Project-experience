package com.chap5;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 * 使用Graphics类进行图形绘制，要求绘制深蓝色矩形和红色椭圆形，然后分别进行填充
 * 
 * @author Administrator
 *
 */
public class Test_5_1 {

	public static void main(String[] args) {
		JFrame frame =new JFrame("形状打印");
		myJpanel1 panel = new myJpanel1();
		frame.add(panel);
		

		//设置大小
		frame.setSize(800, 600);
		//关闭方法
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置可见
		frame.setVisible(true);
		//设置居中
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
