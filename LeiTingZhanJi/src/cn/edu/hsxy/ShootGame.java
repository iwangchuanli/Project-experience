package cn.edu.hsxy;

import java.awt.Frame;

import javax.swing.JFrame;

public class ShootGame {
public static void main(String[] args) {
	JFrame frame = new JFrame("雷霆战机");
	/**添加画纸类*/
	ShootPanel panel = new ShootPanel();
	frame.add(panel);
	/**线程关联*/
	Thread t = new Thread(panel);
	t.start();
	/**鼠标关联*/
	panel.addMouseMotionListener(panel);
	
	//画面格式
	frame.setSize(512, 768);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
}
}
