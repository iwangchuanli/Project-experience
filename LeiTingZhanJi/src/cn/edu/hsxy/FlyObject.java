package cn.edu.hsxy;

import java.awt.image.BufferedImage;

/**
 * 飞行物  父类
 * 所有飞行物都是继承它
 * 
 * */
public abstract class FlyObject {

	int x,y;
	int width,height;
	BufferedImage image;
	
	//走一步
	public abstract void step();
	//出界
	public abstract boolean outOfBounds();
	//判断碰撞
	public boolean hitBy(Bullet bt){
		
		int btX = bt.x;
		int btY = bt.x;
		int btXW = bt.x+bt.width;
		int btYH = bt.y+bt.height;
		
		int fX = this.x;
		int fY = this.x;
		int fXW = this.x+this.width;
		int fYH = this.y+this.height;
		//业务逻辑
		boolean flag = btX > fX && btXW < fXW && btY>fY && btYH<fYH;
		return flag;
	}
}
