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
 * �����ı�����ӭ����JAVA ���硱,���У�����ӭ������Ϊ��ɫ��ʾ������JAVA���硱Ϊ��ɫ��ʾ �ı��þ��ο����������ɫΪ��ɫ
 * 
 * @author Administrator
 *
 */
public class Test_5_3 {

	public static void main(String[] args) {
		JFrame frame = new JFrame("��ӭ����JAVA����");
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
		String str = "��ӭ����";
		String str2 = "JAVA���磡";
		Font f1 = new Font("helvetica", Font.BOLD, 30);
		g.setColor(Color.blue);
		// �����ı����ֶ���
		TextLayout layout = new TextLayout(str+str2, f1, frc);
		Point2D loc = new Point2D.Float(50, 50);// �߿�λ
		// �����ı�
		layout.draw(g, (float) loc.getX(), (float) loc.getY());
		// ���ñ߿�
		Rectangle2D bounds = layout.getBounds();
		bounds.setRect(bounds.getX() + loc.getX(), bounds.getY() + loc.getY(), bounds.getWidth(), bounds.getHeight());
		g.draw(bounds);// ���ϱ߿�

		g.setColor(Color.yellow);
		g.fillRect(50, 65, 300, 50);
		g.setColor(Color.blue);
		g.setFont(f1);
		g.drawString("��ӭ����", 50, 100);
		g.setColor(Color.orange);
		g.drawString("JAVA���磡", 180, 100);
	}
}
