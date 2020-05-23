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
 * Graphics2D类提供了一个文本布局（TextLayout）对象
 * 用于实现各种字体或段落文本的绘制
 * 通过字符串和字体font构造对象
 * @author Administrator
 *
 */
public class GUIText extends Applet{
	@Override
	public void paint(Graphics oldg) {
		// TODO Auto-generated method stub
		Graphics2D g = (Graphics2D) oldg;
		//设置字体
		Font f1 = new Font("Courier",Font.PLAIN , 24);
		Font f2 = new Font("helvetica",Font.BOLD , 24);
		FontRenderContext frc = g.getFontRenderContext();
		String str = new String("这是一个文本布局类的实现");
		String str2 = new String("扩充绘制文本的功能");
		//构造文本布局对象
		TextLayout layout = new TextLayout(str, f1, frc);
		Point2D loc = new Point2D.Float(40,50);//边框定位
		//绘制文本
		layout.draw(g, (float)loc.getX(), (float)loc.getY());
		//设置边框
		Rectangle2D bounds = layout.getBounds();
		bounds.setRect(bounds.getX()+loc.getX(),
				bounds.getY()+loc.getY(),
				bounds.getWidth(),
				bounds.getHeight());
		g.draw(bounds);//画上边框
		
		//添加第二个文本  并改色
		layout= new TextLayout(str2, f2, frc);
		g.setColor(Color.red);
		layout.draw(g, 20, 80);
		
		
	}
}
