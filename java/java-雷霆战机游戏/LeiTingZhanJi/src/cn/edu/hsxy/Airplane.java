package cn.edu.hsxy;

import java.awt.image.BufferedImage;

/**
 * �л���
 * */
public class Airplane extends FlyObject implements Enemy{

	//˽������
	private int yStep;
	private int index;
	private BufferedImage[] images;
	//���췽��
	public Airplane() {
		// TODO Auto-generated constructor stub
		this.image = ShootPanel.flys0;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.x = (int)(Math.random()*(512-this.width));
		this.y = -this.height;
		//��Ϸ�Ѷ�
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

	//��һ��
	@Override
	public void step() {
		// TODO Auto-generated method stub
		//����
		this.y += this.yStep;
		//�޸�ͼƬ
		int is = this.index++/60%this.images.length;
		this.image = this.images[is];
	}

	//����
	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return this.y > 768;
	}

}
