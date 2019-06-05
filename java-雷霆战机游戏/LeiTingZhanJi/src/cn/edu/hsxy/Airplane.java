package cn.edu.hsxy;

import java.awt.image.BufferedImage;

/**
 * 敌机类
 * */
public class Airplane extends FlyObject implements Enemy{

	//私有属性
	private int yStep;
	private int index;
	private BufferedImage[] images;
	//构造方法
	public Airplane() {
		// TODO Auto-generated constructor stub
		this.image = ShootPanel.flys0;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.x = (int)(Math.random()*(512-this.width));
		this.y = -this.height;
		//游戏难度
		this.yStep = 2;
		this.index = 0;
		this.images = new BufferedImage[]{
				ShootPanel.flys0,
				ShootPanel.flys1,	
				ShootPanel.flys2,
				ShootPanel.flys3,
				ShootPanel.flys4,
				ShootPanel.flys5
			};
		}
	
	 
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 10;
	}

	//走一步
	@Override
	public void step() {
		// TODO Auto-generated method stub
		//坐标
		this.y += this.yStep;
		//修改图片
		int is = this.index++/60%this.images.length;
		this.image = this.images[is];
	}

	//出界
	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return this.y > 768;
	}

}
