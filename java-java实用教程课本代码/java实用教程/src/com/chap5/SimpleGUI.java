package com.chap5;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class SimpleGUI extends Applet{

	Image samImage;
	@Override
	public void init() {
		// TODO Auto-generated method stub
		samImage = getImage(getDocumentBase(),"sample.gif");
		
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
		g.clipRect(50,80,180,180);
		g.drawLine(0,0,20,30);
		g.drawString("图形显示",100,30);
		//颜色设置
		Color c = new Color(255,200,0);
		g.setColor(c);
		//字体设置
		Font f = new Font("TimesRoman",Font.BOLD+Font.ITALIC,24);
		g.setFont(f);
		g.drawString("图形显示",180 ,30 );
		//画线
		g.drawLine(20,20 ,100 ,50 );
		g.drawLine(20,20 ,50 ,100 );
		//画矩形框
		g.drawRect(40,40,40,40);
		g.fillRect(60,60,40,40);
		
		g.setColor(Color.red);
		//画3D矩形
		g.draw3DRect(80,80,40,40,true);
		g.draw3DRect(100,100,40,40,false);
		g.draw3DRect(120,120,40,40,true);
		//画椭圆
		g.drawOval(150,150,30,40);
		g.fillOval(170,170,20,20);
		
		g.setColor(Color.blue);
		
		g.drawRoundRect(180,180,40,40,20,20);
		g.fillRoundRect(200,200,40,40,20,20);
		
		int []xC = {242,260,254,297,242};
		int []yC = {240,243,290,300,270};
		g.drawPolygon(xC,yC,5);
		
		g.drawImage(samImage, 250,40,this);
	}
	
}
