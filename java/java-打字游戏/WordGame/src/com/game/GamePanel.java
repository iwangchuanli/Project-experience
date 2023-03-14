package com.game;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import javax.swing.JPanel;

/**
 * 		加分 score 显示在界面上
 * 		减分 字母超出下界面
 * 		随机字母颜色
 * 		控制字母下落速度  
 */
public class GamePanel extends JPanel implements Runnable,KeyListener{//继承画板类 并实现 线程和键盘动作 的接口类

	int[] x = new int[10];//分别放置字母的 x,y 坐标
	int[] y = new int[10];
	// 字符数组存储字母
	char[] words = new char[10];
	int score = 0;
	//随机10种颜色 存储
	Color[] colors=new Color[10];
	
	//游戏画面的构造
	public GamePanel() {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < 10; i++) {
			x[i] = (int)(Math.random()*800);//字母随机出现的坐标位置 （x[i],x[y]）
			y[i] = -(int)(Math.random()*600);
			words[i] = (char)((Math.random()*26)+'A');//大写字母A之后的26个字母随机赋值给words数组
			while (words.equals(words[i])) {
				words[i] = (char)((Math.random()*26)+'A');
			}
			colors[i] = randomColor();//字母颜色调用随机颜色方法
		}
	}
	@Override//绘制游戏面板
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Font ft = new Font("", Font.BOLD, 28);//构建字体对象
		g.setFont(ft);//设置游戏画面字体
		g.drawString("分数："+score+"", 50, 100);//在游戏画面上绘制一个字符串 用于计分
		g.drawLine(0, 500, 800, 500);//在画面下方绘一条标记线  单词到达即结束
		for (int i = 0; i < 10; i++) {//for循环设置游戏里面的字母
			g.setColor(colors[i]);//字母颜色设置
			
			g.drawString(words[i]+"", x[i], y[i]);//字母出现位置
		}
	}
	
	
	@Override//重写Runnable线程里面的run方法  用于画面刷新
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			//分数统计
			for (int i = 0; i < 10; i++) {
				y[i]++;
				if (y[i]>600) {
					y[i] = 0;
					score-=9;
				}
			}
			//2 调整睡眠
			try {
				int speed=15;
				if(score >= 100){//分数大于100分 速度增加 即线程睡眠时间减少
					speed = 9;
				}
				if(score >= 200){//分数大于200分 速度增加
					speed = 3;
				}
				Thread.sleep(speed);
			} catch (InterruptedException e) {//异常处理
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//重绘
			repaint();
		}
	}
	
	@Override//键盘按下方法重写
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// 判断是否一致 e是键盘事件
		for (int i = 0; i < 10; i++) {
			if (words[i] == e.getKeyCode()) {
				//一致后字母消失  加分
				x[i] = (int)(Math.random()*800);//重新设置新字母出现的位置
				y[i] = 0;
				words[i] = (char)((Math.random()*26)+'A');//再随机获取一个新字母
				while (words.equals(words[i])) {
					words[i] = (char)((Math.random()*26)+'A');//再随机获取一个新字母
				}
				score+=5;//加分
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
	//颜色随机赋值的方法（RGB三色）
	public  Color randomColor(){
		int R = (int)(Math.random()*255);
		int G = (int)(Math.random()*255);
		int B = (int)(Math.random()*255);
		Color color = new Color(R, G, B);
		return color;
	}

}

