package com.tarena.shoot;
import java.awt.image.BufferedImage;

public abstract class FlyingObject {
	protected BufferedImage image;//图片命名--java包自有的
	protected int width;     //宽
	protected int height;    //高
	protected int x;         //x坐标
	protected int y;         //y坐标
	
	//飞行物走步
	public abstract void step();
	//越界方法
	public abstract boolean outOfBounds();
	
	//敌人被子弹撞 
	public boolean shootBy(Bullet bullet){
		//this:敌人       other：子弹
		int x1 = this.x;
		int x2 = this.x + this.width;
		int y1 = this.y;
		int y2 = this.y + this.height;
		int x = bullet.x;
		int y = bullet.y;
		return x > x1 && x < x2
				&&
				y > y1 && y < y2;
	}
	
}
