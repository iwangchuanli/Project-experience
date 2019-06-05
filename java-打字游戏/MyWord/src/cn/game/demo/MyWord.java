package cn.game.demo;

import javax.swing.JFrame;

/*
 * 打字游戏
 */
public class MyWord {
	public static void main(String[] args) {
		JFrame frame =new JFrame("打字游戏");
		/** 添加画纸类 */
		MyWordPanel panel= new MyWordPanel();
		frame.add(panel);
		/** 线程关联	*/
		Thread t=new Thread(panel);
		t.start();
		/**键盘关联*/
		panel.addKeyListener(panel);
		//键盘焦点在画纸上
		panel.setFocusable(true);
		
		//设置大小
		frame.setSize(800, 600);
		//关闭方法
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置可见
		frame.setVisible(true);
		//设置居中
		frame.setLocationRelativeTo(null);
	
	}
}
