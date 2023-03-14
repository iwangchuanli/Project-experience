package cn.game.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

/*
 * 1
 * 2
 * 3
 * 4
 * 5 接口KeyListener
 * 
 * 6	加分 score 显示在界面上
 * 		减分 字母超出下界面
 * 		随机字母颜色
 * 		控制字母下落速度  100 speed -5  200  speed  -1
 */
public class MyWordPanel extends JPanel implements Runnable,KeyListener{

	int[] x = new int[10];
	int[] y = new int[10];
	// 字符数组存储字母
	char[] words = new char[10];
	int score = 0;
	Color[] colors=new Color[10];
	
	//
	public MyWordPanel() {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < 10; i++) {
			x[i] = (int)(Math.random()*800);
			y[i] = -(int)(Math.random()*600);
			words[i] = (char)((Math.random()*26)+'A');
			colors[i] = randomColor();
		}
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Font ft = new Font("", Font.BOLD, 28);
		g.setFont(ft);
		g.drawString("分数："+score+"", 50, 100);
		g.drawLine(0, 500, 800, 500);
		for (int i = 0; i < 10; i++) {
			
			g.setColor(colors[i]);
			g.drawString(words[i]+"", x[i], y[i]);
			
		}
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (true) {
			//
			for (int i = 0; i < 10; i++) {
				y[i]++;
				if (y[i]>600) {
					y[i] = 0;
					score--;
				}
			}
			
			
			//2 调整睡眠
			try {
				int speed=15;
				if(score >= 100){
					speed = 9;
				}
				if(score >= 200){
					speed = 3;
				}
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//重绘
			repaint();
		}
		
	}
	@Override//键盘按下
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// 判断是否一致 e是键盘事件
		for (int i = 0; i < 10; i++) {
			if (words[i] == e.getKeyCode()) {
				//一致后字母消失  加分
				x[i] = (int)(Math.random()*800);
				y[i] = 0;
				words[i] = (char)((Math.random()*26)+'A');
				score+=10;
				//终止所有循环
				break;
				//终止一次循环跳到下次循环
				//continue;
			}
		}
	}
	@Override//键盘释放
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override//键盘类型
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public  Color randomColor(){
		int R = (int)(Math.random()*255);
		int G = (int)(Math.random()*255);
		int B = (int)(Math.random()*255);
		Color color = new Color(R, G, B);
		return color;
	}

}
