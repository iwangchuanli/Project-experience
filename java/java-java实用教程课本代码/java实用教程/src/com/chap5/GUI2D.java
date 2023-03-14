package com.chap5;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
/**
 * 几何图形，笔画变换，颜色改变，渐变色
 * @author Administrator
 *
 */
public class GUI2D extends Applet{
	@Override
	public void paint(Graphics oldg) {
		// TODO Auto-generated method stub
		Graphics2D g = (Graphics2D) oldg;
		//设置画笔宽度
		BasicStroke stroke = new BasicStroke(10);
		g.setStroke(stroke);
		//画线
		Line2D line = new Line2D.Float(0,0,20,30);
		g.draw(line);
		line = new Line2D.Float(50,50,100,50);
		g.draw(line);
		line = new Line2D.Float(50,50,50,100);
		g.draw(line);
		//修改画笔宽度
		stroke = new BasicStroke(5);
		g.setStroke(stroke);
		//设置渐变色
		GradientPaint gt = new GradientPaint(0, 0, Color.green,50,30,Color.blue,true);
		g.setPaint((Paint)gt);
		//画矩形
		Rectangle2D rect = new Rectangle2D.Float(80,80,40,40);
		g.draw(rect);//画框
		rect = new Rectangle2D.Float(100,100,40,40);
		g.fill(rect);//区域填充
		//画椭圆
		Ellipse2D ellipse = new Ellipse2D.Float(120, 120, 30, 40);
		g.draw(ellipse);
		//更改渐变色
		gt = new GradientPaint(0, 0, Color.red, 30, 30, Color.yellow,true);
		g.setPaint(gt);
		//填充椭圆区域
		ellipse = new Ellipse2D.Float(140, 140, 20, 20);
		g.fill(ellipse);
		//画圆角矩形
		RoundRectangle2D roundRect = new RoundRectangle2D.Float(160,160,40,40,20,20);
		g.draw(roundRect);
		
		roundRect = new RoundRectangle2D.Float(180,180,40,40,20,20);
		g.fill(roundRect);
		
		//画几何图形
		GeneralPath path = new GeneralPath();
		path.moveTo(150, 0);
		path.lineTo(160, 50);
		path.curveTo(190, 200, 240, 150, 200, 100);
		g.fill(path);
		
	}
	
	
}
