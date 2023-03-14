package com.chap5;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ʹ��Graphics2D ����Ʒۺ�ɫ����ɫ�Ľ��䣬
 * ��䵽Բ�Ǿ��Ρ�����Ρ��ʻ����Ϊ10
 * 
 * @author Administrator
 *
 */
public class Test_5_2 {

	public static void main(String[] args) {
		JFrame frame = new JFrame("����ɫ���");
		myJpanel2 panel = new myJpanel2();
		frame.add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setLocation(null);
		
	}
	
}
class myJpanel2 extends JPanel{
	@Override
	public void paint(Graphics oldg) {
		// TODO Auto-generated method stub
//		super.paint(oldg);
//		oldg.drawRect(50, 50, 200, 300);
		Graphics2D g = (Graphics2D) oldg;
		//���û���
		BasicStroke stroke = new BasicStroke(10);
		g.setStroke(stroke);
		g.drawLine(20, 20, 400, 20);
		//���ý���ɫ
		GradientPaint gp = new GradientPaint(0, 0, Color.pink, 60, 30, Color.blue,true);
		g.setPaint(gp);
		
		g.fillRect(50, 50, 300, 100);
		g.fillRoundRect(50, 160, 300, 100, 50, 40);
		
		GeneralPath path = new GeneralPath();
		path.moveTo(50, 300);
		path.lineTo(500, 500);
		path.curveTo(300, 350, 400, 350, 350, 350);
		
		g.fill(path);
	}
}
