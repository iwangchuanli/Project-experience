package cn.edu.hsxy;
/**
 * 子弹类
 * */
public class Bullet extends FlyObject{

	//
	private int yStep;
	
	public Bullet(int heroX,int heroY) {
		// TODO Auto-generated constructor stub
		this.image = ShootPanel.bullet;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.x = heroX;
		this.y = heroY;
		this.yStep = 2;
	}
	
	@Override
	public void step() {
		// TODO Auto-generated method stub
		//修改坐标
		this.y -= this.yStep;	
		//
		
	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return this.y<0;
	}
	
}
