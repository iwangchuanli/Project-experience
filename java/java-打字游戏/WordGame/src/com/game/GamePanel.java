package com.game;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import javax.swing.JPanel;

/**
 * 		�ӷ� score ��ʾ�ڽ�����
 * 		���� ��ĸ�����½���
 * 		�����ĸ��ɫ
 * 		������ĸ�����ٶ�  
 */
public class GamePanel extends JPanel implements Runnable,KeyListener{//�̳л����� ��ʵ�� �̺߳ͼ��̶��� �Ľӿ���

	int[] x = new int[10];//�ֱ������ĸ�� x,y ����
	int[] y = new int[10];
	// �ַ�����洢��ĸ
	char[] words = new char[10];
	int score = 0;
	//���10����ɫ �洢
	Color[] colors=new Color[10];
	
	//��Ϸ����Ĺ���
	public GamePanel() {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < 10; i++) {
			x[i] = (int)(Math.random()*800);//��ĸ������ֵ�����λ�� ��x[i],x[y]��
			y[i] = -(int)(Math.random()*600);
			words[i] = (char)((Math.random()*26)+'A');//��д��ĸA֮���26����ĸ�����ֵ��words����
			while (words.equals(words[i])) {
				words[i] = (char)((Math.random()*26)+'A');
			}
			colors[i] = randomColor();//��ĸ��ɫ���������ɫ����
		}
	}
	@Override//������Ϸ���
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Font ft = new Font("", Font.BOLD, 28);//�����������
		g.setFont(ft);//������Ϸ��������
		g.drawString("������"+score+"", 50, 100);//����Ϸ�����ϻ���һ���ַ��� ���ڼƷ�
		g.drawLine(0, 500, 800, 500);//�ڻ����·���һ�������  ���ʵ��Ｔ����
		for (int i = 0; i < 10; i++) {//forѭ��������Ϸ�������ĸ
			g.setColor(colors[i]);//��ĸ��ɫ����
			
			g.drawString(words[i]+"", x[i], y[i]);//��ĸ����λ��
		}
	}
	
	
	@Override//��дRunnable�߳������run����  ���ڻ���ˢ��
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			//����ͳ��
			for (int i = 0; i < 10; i++) {
				y[i]++;
				if (y[i]>600) {
					y[i] = 0;
					score-=9;
				}
			}
			//2 ����˯��
			try {
				int speed=15;
				if(score >= 100){//��������100�� �ٶ����� ���߳�˯��ʱ�����
					speed = 9;
				}
				if(score >= 200){//��������200�� �ٶ�����
					speed = 3;
				}
				Thread.sleep(speed);
			} catch (InterruptedException e) {//�쳣����
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//�ػ�
			repaint();
		}
	}
	
	@Override//���̰��·�����д
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// �ж��Ƿ�һ�� e�Ǽ����¼�
		for (int i = 0; i < 10; i++) {
			if (words[i] == e.getKeyCode()) {
				//һ�º���ĸ��ʧ  �ӷ�
				x[i] = (int)(Math.random()*800);//������������ĸ���ֵ�λ��
				y[i] = 0;
				words[i] = (char)((Math.random()*26)+'A');//�������ȡһ������ĸ
				while (words.equals(words[i])) {
					words[i] = (char)((Math.random()*26)+'A');//�������ȡһ������ĸ
				}
				score+=5;//�ӷ�
				//��ֹ����ѭ��
				break;
				//��ֹһ��ѭ�������´�ѭ��
				//continue;
			}
		}
	}
	@Override//�����ͷ�
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override//��������
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	//��ɫ�����ֵ�ķ�����RGB��ɫ��
	public  Color randomColor(){
		int R = (int)(Math.random()*255);
		int G = (int)(Math.random()*255);
		int B = (int)(Math.random()*255);
		Color color = new Color(R, G, B);
		return color;
	}

}

