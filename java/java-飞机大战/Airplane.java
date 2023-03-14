package com.tarena.shoot;
import java.util.Random;
//Airplane----敌机既是飞行物，
public class Airplane extends FlyingObject implements Enemy{
	private int speed = 2;//敌机走步的步数
	public Airplane(){
		image = ShootGame.airplane;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();
		x = rand.nextInt(ShootGame.WIDTH - this.width);
		y = -this.height;   //y:负的敌机的高
		
		
	}
		
	//重写 getScore();
	public int getScore(){
		return 5;
	}
	
	public void step(){
		y += speed;
	}
	public boolean outOfBounds(){
		return this.y > ShootGame.HEIGHT;      //敌机的y坐标大于窗口的高
			
	}

}
