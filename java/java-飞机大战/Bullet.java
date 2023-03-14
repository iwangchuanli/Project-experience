package com.tarena.shoot;
//�ӵ�--ֻ�Ƿ����� 
public class Bullet extends FlyingObject{
	private int speed = 3;  //�ӵ��߲�������ֻ��y�����ڱ�
	public Bullet(int x,int y){//�ӵ�����������Ӣ�ۻ��ı仯���仯		
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
