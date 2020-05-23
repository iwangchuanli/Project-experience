package com.tarena.shoot;
import java.util.Random;
//Be---小蜜蜂  既是飞行物，也能获取奖励
public class Bee extends FlyingObject implements Award{
	private int xSpeed = 1;     //x坐标走步步数
	private int ySpeed = 2;     //y坐标走步步数
	private int awardType;      //奖励类型
	
	public Bee(){
		image = ShootGame.bee;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();
		x = rand.nextInt(ShootGame.WIDTH - this.width);
	    y = -this.height; 
		awardType = rand.nextInt(2);//随机生成奖励类型
	
	}
	
	public int getType(){
		return awardType;
	}
    public void step(){   	
    	if(x >= ShootGame.WIDTH - this.width){
    		xSpeed = -1;
    	}
    	if(x <= 0){
    		xSpeed = 1;
    	}
    	x += xSpeed;
    	y += ySpeed;		
	}
    public boolean outOfBounds(){
    	return this.y > ShootGame.HEIGHT;  
	}
}
