package com.chap5;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Graphics2D���ṩ��һ���ı����֣�TextLayout������
 * ����ʵ�ָ������������ı��Ļ���
 * ͨ���ַ���������font�������
 * @author Administrator
 *
 */
public class GUIText extends Applet{
	@Override
	public void paint(Graphics oldg) {
		// TODO Auto-generated method stub
		Graphics2D g = (Graphics2D) oldg;
		//��������
		Font f1 = new Font("Courier",Font.PLAIN , 24);
		Font f2 = new Font("helvetica",Font.BOLD , 24);
		FontRenderContext frc = g.getFontRenderContext();
		String str = new String("����һ���ı��������ʵ��");
		String str2 = new String("��������ı��Ĺ���");
		//�����ı����ֶ���
		TextLayout layout = new TextLayout(str, f1, frc);
		Point2D loc = new Point2D.Float(40,50);//�߿�λ
		//�����ı�
		layout.draw(g, (float)loc.getX(), (float)loc.getY());
		//���ñ߿�
		Rectangle2D bounds = layout.getBounds();
		bounds.setRect(bounds.getX()+loc.getX(),
				bounds.getY()+loc.getY(),
				bounds.getWidth(),
				bounds.getHeight());
		g.draw(bounds);//���ϱ߿�
		
		//��ӵڶ����ı�  ����ɫ
		layout= new TextLayout(str2, f2, frc);
		g.setColor(Color.red);
		layout.draw(g, 20, 80);
		
		
	}
}
