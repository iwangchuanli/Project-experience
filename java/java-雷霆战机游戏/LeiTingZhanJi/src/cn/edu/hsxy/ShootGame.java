package cn.edu.hsxy;

import java.awt.Frame;

import javax.swing.JFrame;

public class ShootGame {
public static void main(String[] args) {
	JFrame frame = new JFrame("����ս��");
	/**��ӻ�ֽ��*/
	ShootPanel panel = new ShootPanel();
	frame.add(panel);
	/**�̹߳���*/
	Thread t = new Thread(panel);
	t.start();
	/**������*/
	panel.addMouseMotionListener(panel);
	
	//�����ʽ
	frame.setSize(512, 768);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
}
}
