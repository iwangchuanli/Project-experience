package com.tarena.shoot;
import java.util.Random;
//Airplane----�л����Ƿ����
public class Airplane extends FlyingObject implements Enemy{
	private int speed = 2;//�л��߲��Ĳ���
	public Airplane(){
		image = ShootGame.airplane;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();
		x = rand.nextInt(ShootGame.WIDTH - this.width);
		y = -this.height;   //y:���ĵл��ĸ�
		
		
	}
		
	//��д getScore();
	public int getScore(){
		return 5;
	}
	
	public void step(){
		y += speed;
	}
	public boolean outOfBounds(){
		return this.y > ShootGame.HEIGHT;      //�л���y������ڴ��ڵĸ�
			
	}

}
