package com.chap5;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class UseColor extends Applet {
	@Override
	public void paint(Graphics oldg) {
		// TODO Auto-generated method stub
		Graphics2D g = (Graphics2D) oldg;
		g.setColor(Color.BLUE);
		
		g.fill(new Ellipse2D.Float(50,50,150,150));
		g.setColor(new Color(255, 0, 0,0));
		
		g.fill(new Ellipse2D.Float(50,50,140,140));
		g.setColor(new Color(255,0,0,64));
		
		g.fill(new Ellipse2D.Float(50,50,130,130));
		g.setColor(new Color(255,0,0,128));
		
		g.fill(new Ellipse2D.Float(50,50,110,110));
		g.setColor(new Color(255,0,0,255));
		
		g.fill(new Ellipse2D.Float(50,50,90,90));
		g.setColor(new Color(255,200,0));
		
		g.fill(new Ellipse2D.Float(50,50,70,70));
		
	}
}
