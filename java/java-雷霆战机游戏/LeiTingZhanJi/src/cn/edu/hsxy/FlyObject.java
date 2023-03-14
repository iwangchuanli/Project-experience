package cn.edu.hsxy;

import java.awt.image.BufferedImage;

/**
 * ������  ����
 * ���з����ﶼ�Ǽ̳���
 * 
 * */
public abstract class FlyObject {

	int x,y;
	int width,height;
	BufferedImage image;
	
	//��һ��
	public abstract void step();
	//����
	public abstract boolean outOfBounds();
	//�ж���ײ
	public boolean hitBy(Bullet bt){
		
		int btX = bt.x;
		int btY = bt.x;
		int btXW = bt.x+bt.width;
		int btYH = bt.y+bt.height;
		
		int fX = this.x;
		int fY = this.x;
		int fXW = this.x+this.width;
		int fYH = this.y+this.height;
		//ҵ���߼�
		boolean flag = btX > fX && btXW < fXW && btY>fY && btYH<fYH;
		return flag;
	}
}
