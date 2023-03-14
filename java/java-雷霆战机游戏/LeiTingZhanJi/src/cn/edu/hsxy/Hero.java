package cn.edu.hsxy;

import java.awt.image.BufferedImage;

/**
 * Ӣ��ս��
 * */
public class Hero extends FlyObject{

	//˽������
	private int life;//����
	private int doubleFire;//����ֵ >0
	private int award;//����ֵ 0���� 1�ӻ���ֵ
	private int index;//����ͼƬ��ת��ֵ �ڼ���ͼƬ
	private BufferedImage[] images;//ͼƬ��
	
	//���췽��
	public Hero() {
		// TODO Auto-generated constructor stub
		//���е�
		this.image = ShootPanel.ws00;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.x = 200;
		this.y = 500;
		//˽�б���
		this.life = 10;
		this.doubleFire = 0;
		this.award = 0;
		this.index = 0;
		this.images = new BufferedImage[]{
			ShootPanel.ws00,
			ShootPanel.ws01,	
			ShootPanel.ws02,
			ShootPanel.ws03,
			ShootPanel.ws04,
			ShootPanel.ws05,
			ShootPanel.ws06,
			ShootPanel.ws07,
			ShootPanel.ws08,
			ShootPanel.ws09
		};
	}
	
	@Override
	public void step() {
		// TODO Auto-generated method stub
		//�޸�����ֵ  �������Ķ�
		
		//����ͼƬ
		//  0 / 10 % 10 ==0			1 /10  % 10  ==0   ~~~11  /10  % 10==1  ÿ10�θ���һ��ͼƬ
		int is= this.index++/10%this.images.length;
		this.image = this.images[is];//ÿһ��ͼƬ���¸���image
	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		//Ӣ�ۻ��������
		return false;
	}
	public void moveTo(int mouseX,int mouseY){
		this.x = mouseX - this.width/2;
		this.y = mouseY - this.height/2;
	}

	//�����ӵ�
	public Bullet[] shootBy(){
		//˫��
		Bullet[] bs;
		if (this.doubleFire > 0) {
			bs = new Bullet[2];
			bs[0] = new Bullet(this.x + this.width/4*1,this.y);
			bs[1] = new Bullet(this.x + this.width/4*3,this.y);
			this.doubleFire --;
		}else{
			bs = new Bullet[1];
			bs[0] = new Bullet(this.x + this.width/4*2,this.y);
		}
		return bs;	
	}
	//�������ֵ
	public void addLife(){
		this.life += 5;
	}
	//��ӻ���
	public void addFire(){
		this.doubleFire = 40;
	}
}
