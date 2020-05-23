package com.tarena.shoot;
//子弹--只是飞行物 
public class Bullet extends FlyingObject{
	private int speed = 3;  //子弹走步步数，只有y坐标在变
	public Bullet(int x,int y){//子弹的坐标随着英雄机的变化而变化		
		image = ShootGame.bullet;
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
	}
    public void step(){
		y -= speed;
	}
    public boolean outOfBounds(){
		return this.y < -this.height;
	}

}
