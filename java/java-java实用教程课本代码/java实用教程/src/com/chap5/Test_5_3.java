package com.chap5;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 绘制文本“欢迎来到JAVA 世界”,其中：“欢迎来到”为蓝色显示，而“JAVA世界”为橙色显示 文本用矩形框框起来，底色为黄色
 * 
 * @author Administrator
 *
 */
public class Test_5_3 {

	public static void main(String[] args) {
		JFrame frame = new JFrame("欢迎来到JAVA世界");
		myJpanel3 panel = new myJpanel3();

		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(null);
	}
}

class myJpanel3 extends JPanel {

	@Override
	public void paint(Graphics oldg) {
		// TODO Auto-generated method stub
		Graphics2D g = (Graphics2D) oldg;
		FontRenderContext frc = g.getFontRenderContext();
		String str = "欢迎来到";
		String str2 = "JAVA世界！";
		Font f1 = new Font("helvetica", Font.BOLD, 30);
		g.setColor(Color.blue);
		// 构造文本布局对象
		TextLayout layout = new TextLayout(str+str2, f1, frc);
		Point2D loc = new Point2D.Float(50, 50);// 边框定位
		// 绘制文本
		layout.draw(g, (float) loc.getX(), (float) loc.getY());
		// 设置边框
		Rectangle2D bounds = layout.getBounds();
		bounds.setRect(bounds.getX() + loc.getX(), bounds.getY() + loc.getY(), bounds.getWidth(), bounds.getHeight());
		g.draw(bounds);// 画上边框

		g.setColor(Color.yellow);
		g.fillRect(50, 65, 300, 50);
		g.setColor(Color.blue);
		g.setFont(f1);
		g.drawString("欢迎来到", 50, 100);
		g.setColor(Color.orange);
		g.drawString("JAVA世界！", 180, 100);
	}
}
