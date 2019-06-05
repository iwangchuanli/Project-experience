package cn.edu.hsxy;

import java.awt.image.BufferedImage;

/**
 * 英雄战机
 * */
public class Hero extends FlyObject{

	//私有属性
	private int life;//生命
	private int doubleFire;//火力值 >0
	private int award;//奖励值 0加命 1加火力值
	private int index;//代表图片集转换值 第几张图片
	private BufferedImage[] images;//图片集
	
	//构造方法
	public Hero() {
		// TODO Auto-generated constructor stub
		//公有的
		this.image = ShootPanel.ws00;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.x = 200;
		this.y = 500;
		//私有变量
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
		//修改坐标值  根据鼠标改动
		
		//更改图片
		//  0 / 10 % 10 ==0			1 /10  % 10  ==0   ~~~11  /10  % 10==1  每10次更改一下图片
		int is= this.index++/10%this.images.length;
		this.image = this.images[is];//每一张图片重新赋给image
	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		//英雄机不会出界
		return false;
	}
	public void moveTo(int mouseX,int mouseY){
		this.x = mouseX - this.width/2;
		this.y = mouseY - this.height/2;
	}

	//生成子弹
	public Bullet[] shootBy(){
		//双倍
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
	//添加生命值
	public void addLife(){
		this.life += 5;
	}
	//添加火力
	public void addFire(){
		this.doubleFire = 40;
	}
}
