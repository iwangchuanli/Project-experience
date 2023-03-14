package com.chap5;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TexturePaint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * BufferedImage ���ܻ���ͼ��	���ͼ�����ݵĿɷ��ʵĻ���
 * ʹ�ÿ�ȡ��߶ȡ�ͼƬ���͹���
 * 
 * @author Administrator
 *
 */
public class GUIImage extends Applet{

	@Override
	public void paint(Graphics oldg) {
		// TODO Auto-generated method stub
		Graphics2D g = (Graphics2D) oldg;
		try {
			URL imgURL = new URL(getDocumentBase(),"sample.jpg");
			Image img = getImage(imgURL);
			int h = img.getHeight(this);
			int w = img.getWidth(this);
			//���컺��ͼ�����
			BufferedImage buf = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			//������ʱͼ����
			Graphics tmpG = buf.createGraphics();
			tmpG.drawImage(img, 10,10,this);
			g.drawImage(buf, 10, 20, this);
			//����͸����ɫ����
			Color transBlue = new Color(0, 0, 255,100);
			g.setColor(transBlue);
			GeneralPath path = new GeneralPath();
			path.moveTo(60, 0);
			path.lineTo(50, 100);
			path.curveTo(160, 230, 240, 140, 200, 100);
			g.fill(path);
			
			transBlue = new Color(0,0,255,240);
			g.fill(new Ellipse2D.Float(100,100,50,50));
			Rectangle2D rect = new Rectangle2D.Float(0,0,h,w);
			
			//ͼƬ���
			TexturePaint t = new TexturePaint(buf, rect);
			g.setPaint(t);
			g.fill(new Ellipse2D.Float(100,50,60,60));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error:"+e.getMessage());
		}
		
		
	}
}
