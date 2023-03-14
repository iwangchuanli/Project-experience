package cn.edu.hsxy;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * 1、注释加框架
 * 2、背景移动
 * 		变量 background
 * 		初始化 static
 * 		画图
 * 		图片的移动
 * 3、战机
 * 
 * */

public class ShootPanel extends JPanel implements Runnable,MouseMotionListener{
	/**第一区域：变量声明区域*/
	// 静态的  方法区中 永远存在
	static BufferedImage background;
	int backY = 0;
	
	static BufferedImage ws00;
	static BufferedImage ws01;
	static BufferedImage ws02;
	static BufferedImage ws03;
	static BufferedImage ws04;
	static BufferedImage ws05;
	static BufferedImage ws06;
	static BufferedImage ws07;
	static BufferedImage ws08;
	static BufferedImage ws09;
	
	//英雄机
	Hero hero = new Hero();
	//子弹
	static BufferedImage bullet;
	Bullet [] bullets={};
	//
	int flyIndex = 0;
	                
	//敌机图片
	static BufferedImage flys0;
	static BufferedImage flys1;
	static BufferedImage flys2;
	static BufferedImage flys3;
	static BufferedImage flys4;
	static BufferedImage flys5;
	//敌机数组 flys 包含爱心和敌机
	FlyObject [] flys ={};
	//Airplane airplane = new Airplane();
	//爱心
	static BufferedImage qq00;
	static BufferedImage qq01;
	static BufferedImage qq02;
	static BufferedImage qq03;
	static BufferedImage qq04;
	static BufferedImage qq05;
	static BufferedImage qq06;
	static BufferedImage qq07;
	static BufferedImage qq08;
	
	
	
	/**第二区域：静态图片初始化区域*/
	static {
		try {
			background=ImageIO.read(ShootPanel.class.getResource("background.jpg"));
			ws00=ImageIO.read(ShootPanel.class.getResource("ws00.png"));
			ws01=ImageIO.read(ShootPanel.class.getResource("ws01.png"));
			ws02=ImageIO.read(ShootPanel.class.getResource("ws02.png"));
			ws03=ImageIO.read(ShootPanel.class.getResource("ws03.png"));
			ws04=ImageIO.read(ShootPanel.class.getResource("ws04.png"));
			ws05=ImageIO.read(ShootPanel.class.getResource("ws05.png"));
			ws06=ImageIO.read(ShootPanel.class.getResource("ws06.png"));
			ws07=ImageIO.read(ShootPanel.class.getResource("ws07.png"));
			ws08=ImageIO.read(ShootPanel.class.getResource("ws08.png"));
			ws09=ImageIO.read(ShootPanel.class.getResource("ws09.png"));
			bullet=ImageIO.read(ShootPanel.class.getResource("bullets.png"));
			flys0=ImageIO.read(ShootPanel.class.getResource("flys0.png"));
			flys1=ImageIO.read(ShootPanel.class.getResource("flys1.png"));
			flys2=ImageIO.read(ShootPanel.class.getResource("flys2.png"));
			flys3=ImageIO.read(ShootPanel.class.getResource("flys3.png"));
			flys4=ImageIO.read(ShootPanel.class.getResource("flys4.png"));
			flys5=ImageIO.read(ShootPanel.class.getResource("flys5.png"));
			qq00=ImageIO.read(ShootPanel.class.getResource("qq00.png"));
			qq01=ImageIO.read(ShootPanel.class.getResource("qq01.png"));
			qq02=ImageIO.read(ShootPanel.class.getResource("qq02.png"));
			qq03=ImageIO.read(ShootPanel.class.getResource("qq03.png"));
			qq04=ImageIO.read(ShootPanel.class.getResource("qq04.png"));
			qq05=ImageIO.read(ShootPanel.class.getResource("qq05.png"));
			qq06=ImageIO.read(ShootPanel.class.getResource("qq06.png"));
			qq07=ImageIO.read(ShootPanel.class.getResource("qq07.png"));
			qq08=ImageIO.read(ShootPanel.class.getResource("qq08.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//构造方法
	public ShootPanel() {
		// TODO Auto-generated constructor stub
	}
	
	/**第三区域画笔方法区域*/
	//paint
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		// 3.1 画背景图方法调用
		paintBackground(g);
		// 3.2画英雄机
		paintHero(g);
		//画子弹
		paintBullet(g);
		// 3.4 画飞行物
		paintFlyObject(g);
		
	}
	// 3.1 画背景图方法
	public void paintBackground(Graphics g){
		backY++;
		g.drawImage(background, 0, backY, 512, 768, null);
		g.drawImage(background, 0, backY-768, 512, 768, null);
		if (backY > 768) {
			backY = 0;
		}
	}
	//3.2画英雄机
	public void paintHero(Graphics g){
		g.drawImage(hero.image, hero.x, hero.y, hero.width, hero.height, null);
	}

	//画子弹
	public void paintBullet(Graphics g){
		for (int i = 0; i < bullets.length; i++) {
			Bullet bt = bullets[i];
			g.drawImage(bt.image, bt.x, bt.y, bt.width, bt.height, null);
		}
	}
	//画飞行物
	public void paintFlyObject(Graphics g){
		for (int i = 0; i < flys.length; i++) {
			FlyObject object = flys[i];
			g.drawImage(object.image, object.x, object.y,object.width, object.height, null);
		}
	}
	
	/**第四区域：业务逻辑处理区域*/
	//线程
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			//1 修改坐标值
			//4.1飞行物生成
			enterAction();
			//4.2走一步
			stepAction();
			//4.3出界
			outOfBoundsAction();
			// 4.4 碰撞判断
			hitAction();
			
			//2 调用睡眠
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//3 调用重绘
			repaint();
		}	
	}
	
	//生成
	public void enterAction(){
		flyIndex++;
		//生成爱心和敌机
		if (flyIndex % 60 == 0) {
			FlyObject object = null;
			int type = (int)(Math.random()*20);
			if (type<4) {
				object = new Love();
			}else{
				object = new Airplane();
			}
			//放到flys中  数组扩容
			flys = Arrays.copyOf(flys,flys.length+1);
			flys[flys.length-1] = object;	
		}
		//生成英雄子弹
		if (flyIndex % 40 == 0) {
			//单倍  双倍
			Bullet[] bs = hero.shootBy();
			//数组扩容
			bullets = Arrays.copyOf(bullets,bullets.length+bs.length);
			//数组添加数组
			//src srcPos dest  dest
			//原数组  原数组需要从哪里开始添加  目标数组   从目标数组的什么位置开始  需要复制几个到目标数组中
			System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length);
//			Bullet bt = new Bullet(hero.x,hero.y);
//			//bullets是复制后的数组长度
//			//bullets-1 是最后一个房间的下标号
//			bullets[bullets.length-1] = bt;
		}

		
	}
	//走一步
	public void stepAction(){
		hero.step();
		/**飞行物走一步*/
		for (int i = 0; i < flys.length; i++) {
			FlyObject object = flys[i];
			object.step();
		}
		//子弹
		for (int i = 0; i < bullets.length; i++) {
			Bullet bt = bullets[i];
			bt.step();
		}
	}
	// 4.3 出界
	public void outOfBoundsAction(){
		//子弹出界
		for (int i = 0; i < bullets.length; i++) {
			Bullet bt = bullets[i];
			if (bt.outOfBounds()) {
				//数组的缩容
				bullets[i] = bullets[bullets.length-1];
				bullets = Arrays.copyOf(bullets,bullets.length-1);
				break;
			}
		}
	}
	
	// 4.4 碰撞
	public void hitAction(){
		for (int i = 0; i < bullets.length; i++) {
			Bullet bt =bullets[i];
			hit(bt,i);	//子弹对象  下标		
		}
	}
	//碰撞判断
	public void hit(Bullet bt,int btIndex){
		//变量 一个变量
		int hitIndex = -1;
		//将飞行物与子弹做对比
		for (int i = 0; i < flys.length; i++) {
			FlyObject object = flys[i];
			//调用飞行物中的碰撞判断
			//true是碰撞
			if (object.hitBy(bt)) {
				//将碰撞飞行物的下标给hitIndex
				//
				hitIndex = i;
				break;
			}
		}
		//判断
		if (hitIndex != -1) {
			FlyObject object = flys[hitIndex];
			if (object instanceof Airplane) {
				//加分 score += object.getScore();
				
			}
			if (object instanceof Love) {
				int awardType = ((Love)object).getAward();
				switch (awardType) {
				case Award.LIFE:
					//加命
					hero.addLife();
					break;
				case Award.DoubleFire:
					//加火力.
					hero.addFire();
					break;

				}
			}
		/**子弹的缩容*/
		bullets[btIndex]=bullets[bullets.length-1];
		bullets =Arrays.copyOf(bullets, bullets.length-1);
		/**飞行物的缩容*/
		flys[hitIndex]=flys[flys.length-1];
		flys =Arrays.copyOf(flys, flys.length-1);
		}
	}
	/**第五区域：鼠标监听区域*/

	@Override//鼠标拖拽
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override//鼠标移动
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int mouseX = e.getX();
		int mouseY = e.getY();
		hero.moveTo(mouseX,mouseY);
	}

}
