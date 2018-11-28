package cn.edu.hsxy;

import javax.swing.JFrame;

public class MyTom {
	public static void main(String[] args) {
		
	JFrame frame = new JFrame("MyTomCat");
	
	MyTomPanel panel = new MyTomPanel();
	frame.add(panel);
	
	//Thread t = new Thread(panel);
	//t.start();
	
	panel.addMouseListener(panel);
	
	frame.setSize(350, 600);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	
	}

}
