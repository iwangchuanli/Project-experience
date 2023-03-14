package com.tarena.shoot;
import java.awt.image.BufferedImage;

public abstract class FlyingObject {
	protected BufferedImage image;//ͼƬ����--java�����е�
	protected int width;     //��
	protected int height;    //��
	protected int x;         //x����
	protected int y;         //y����
	
	//�������߲�
	public abstract void step();
	//Խ�緽��
	public abstract boolean outOfBounds();
	
	//���˱��ӵ�ײ 
	public boolean shootBy(Bullet bullet){
		//this:����       other���ӵ�
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
