package cn.edu.hsxy;

import java.awt.image.BufferedImage;

public class Love extends FlyObject implements Award{

	//私有属性
	private BufferedImage [] images;
	private int index;
	private int xStep;
	private int yStep;
	private int award;
	
	public Love() {
		// TODO Auto-generated constructor stub
		this.image = ShootPanel.qq00;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.x = (int)(Math.random()*(512-this.image.getWidth()));
		this.y = -(int)(Math.random()*768);
		this.award  = (int)(Math.random()*2);
		this.yStep=2;
		this.xStep=1;
		this.index=0;
		this.images=new BufferedImage[] {
				ShootPanel.qq00,
				ShootPanel.qq01,
				ShootPanel.qq02,
				ShootPanel.qq03,
				ShootPanel.qq04,
				ShootPanel.qq05,
				ShootPanel.qq06,
				ShootPanel.qq07,
				ShootPanel.qq08
		};
	}
	
	@Override
	public int getAward() {
		// TODO Auto-generated method stub
		return this.award;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		//改坐标
		this.y +=this.yStep;
		if (this.x+this.width>512) {
			this.xStep=-1;}
		else if (this.x<0) {
				this.xStep=1;
			}
		this.x+=this.xStep;
		//改图片
		int is = this.index++/10%this.images.length;
		this.image=this.images[is];
		
	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		//出界
		return this.y>768;
	}

}
