package cn.game.demo;

import javax.swing.JFrame;

/*
 * ������Ϸ
 */
public class MyWord {
	public static void main(String[] args) {
		JFrame frame =new JFrame("������Ϸ");
		/** ��ӻ�ֽ�� */
		MyWordPanel panel= new MyWordPanel();
		frame.add(panel);
		/** �̹߳���	*/
		Thread t=new Thread(panel);
		t.start();
		/**���̹���*/
		panel.addKeyListener(panel);
		//���̽����ڻ�ֽ��
		panel.setFocusable(true);
		
		//���ô�С
		frame.setSize(800, 600);
		//�رշ���
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ÿɼ�
		frame.setVisible(true);
		//���þ���
		frame.setLocationRelativeTo(null);
	
	}
}
