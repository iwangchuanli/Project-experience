package com.tarena.shoot;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Arrays;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
//主程序测试类
public class ShootGame extends JPanel {
	public static final int WIDTH = 400;  //窗口的宽
	public static final int HEIGHT = 654; //窗口的高
	//静态资源
	public static BufferedImage background; //背景图
	public static BufferedImage start;     //开始图
	public static BufferedImage pause;      //暂停图
	public static BufferedImage gameover;   //游戏结束图
	public static BufferedImage airplane;   //敌机图
	public static BufferedImage bee;        //蜜蜂图
	public static BufferedImage bullet;     //子弹图
	public static BufferedImage hero0;      //英雄机0图
	public static BufferedImage hero1;      //英雄机1图
	
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;
	private int state = 0;   //当前状态
	
	private Hero hero = new Hero();         //英雄机
	private Bullet[] bullets = {};          //子弹数组
	private FlyingObject[] flyings = {};     //敌人数组
	
	
	private Timer timer;
	private int intervel = 10;              //间隔时间：单位--毫秒
	
	
	//静态块
	static{
		try{
			background = ImageIO.read(ShootGame.class.getResource("background.png"));
			start = ImageIO.read(ShootGame.class.getResource("start.png"));
			pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
			gameover = ImageIO.read(ShootGame.class.getResource("gameover.png"));
			airplane = ImageIO.read(ShootGame.class.getResource("airplane.png"));
			bee = ImageIO.read(ShootGame.class.getResource("bee.png"));
			bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));
			hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	public static FlyingObject nextOne(){
		Random rand = new Random();
		int type = rand.nextInt(20);  //生成0到19的随机数
		if(type == 0){                //随机数为0，返回bee;否则返回敌机
			return new Bee();
		}else{
			return new Airplane();
		}
	}
		
	int flyEnteredIndex = 0;
	
	//敌人登场
	public void enterAction(){//10毫秒走一次
		flyEnteredIndex++;  //每10毫秒增1
		if(flyEnteredIndex % 40 == 0){
			FlyingObject obj = nextOne();
			flyings = Arrays.copyOf(flyings,flyings.length + 1);
			flyings[flyings.length - 1] = obj;//将敌人赋值给flyings数组的最后一个元素
		}
	}
	
	public void stepAction(){        //10毫秒走一次
		hero.step();                 //英雄机走一步
		for(int i = 0;i < flyings.length;i++){
			flyings[i].step();       //敌人走一步
		}
		for(int i = 0;i < bullets.length;i++){
			bullets[i].step();       //子弹走一步
		}
	}
	int shootIndex = 0;
	public void shootAction(){         //10毫秒走一次
		shootIndex++;                 //每10毫秒增1
		if(shootIndex % 30 == 0){     //300毫秒发射一次子弹			
			Bullet[] bs = hero.shoot();//获取子弹对象
			bullets = Arrays.copyOf(bullets,bullets.length + bs.length);
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length,bs.length);
		}
	}
	
	//删除越界飞行物
	public void outOfBoundsAction(){
		int index = 0;
		FlyingObject[] flyingLives = new FlyingObject[flyings.length];
		for(int i = 0;i < flyings.length;i++){
			FlyingObject f = flyings[i];
			if(!f.outOfBounds()){
				flyingLives[index] = f;//不越界，将其装入flyingLives[]数组中
				index++;
			}			
		}		
		flyings = Arrays.copyOf(flyingLives,index);
		
		index = 0;
		Bullet[] bulletsLives = new Bullet[bullets.length];
		for(int i = 0;i < bullets.length;i++){
			Bullet bs = bullets[i];
			if(!bs.outOfBounds()){
				bulletsLives[index] = bs;//不越界，将其装入flyingLives[]数组中
				index++;
			}			
		}
		bullets = Arrays.copyOf(bulletsLives,index);
	
	}
	
	int score = 0;        //得分
	//所有子弹与所有敌人撞
	public void bangAction(){
		for(int i = 0;i < bullets.length;i++){
			bang(bullets[i]);
		}
	}
	
	//一个子弹与所有敌人撞
	public void bang(Bullet b){
		int index = -1;//被撞敌人的索引
		for(int i = 0;i < flyings.length;i++){//遍历所有的敌人
			if(flyings[i].shootBy(b)){//判断是否撞上
				index = i;                    //记录被撞敌人的索引
				break;
			}
		}
		if(index != -1){//撞上了
			FlyingObject one = flyings[index]; 
			if(one instanceof Enemy){
				Enemy e = (Enemy)one;
				score += e.getScore();
			}
			if(one instanceof Award){
				Award a = (Award)one;
				int type = a.getType();
				switch(type){
				case Award.DOUBLE_FIRE:      //奖励活力值
					hero.addDoubleFire();    //英雄机增加火力
					break;
				case Award.LIFE:             //奖励命
					hero.addLife();          //英雄机增命
					break;				
				}
			}
			//被撞敌人与flyings数组中的最后一个元素交换
			FlyingObject t = flyings[index];
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = t;
			//缩容，删除随后一个元素---即被撞的对象
			flyings = Arrays.copyOf(flyings, flyings.length  - 1);
		}
	}

	public void checkGameOverAction(){
		if(isGameOver()){    //结束游戏
			state = GAME_OVER;
		}
	}
	public boolean isGameOver(){
		for(int i = 0;i < flyings.length;i++){   //撞上了，
			if(hero.hit(flyings[i])){
				hero.subtractLife();          //生命减1
				hero.setDoubleFire(0);        //火力值清零
				
				//相撞之后，交换缩容
				FlyingObject t = flyings[i];
				flyings[i] = flyings[flyings.length - 1];
				flyings[flyings.length  - 1] = t;
				flyings = Arrays.copyOf(flyings, flyings.length - 1);
			}
		}
		return hero.getLife() <= 0;  //英雄机的命<=0,游戏结束
	}
	
	//启动执行代码
	public void action(){
		MouseAdapter l = new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				if(state == RUNNING){  //运行状态下执行
				int x = e.getX();      //鼠标Y坐标
				int y = e.getY();      //鼠标X坐标
				hero.moveTo(x, y);     //英雄机随着鼠标移动而移动
				}
			}
			//鼠标的点击事件
			public void mouseClicked(MouseEvent e){
				switch(state){
				case START:
					state = RUNNING;
					break;
				case GAME_OVER:
					hero = new Hero();//清理现场
					flyings = new FlyingObject[0];
					bullets = new Bullet[0];
					score = 0;
					state = START;
					break;
				}
			}
			public void mouseEntered(MouseEvent e){
				if(state == PAUSE){
					state = RUNNING;
				}
			}
			public void mouseExited(MouseEvent e){
				if(state == RUNNING){
					state = PAUSE;
				}
			}
		};
		this.addMouseListener(l);     //处理鼠标操作事件
		this.addMouseMotionListener(l);//处理鼠标移动事件
		
		timer = new Timer();   //创建定时器对象
		timer.schedule(new TimerTask(){
			public void run(){  //定时干的那个事--10毫秒走一次
				if(state == RUNNING){        //运行状态下执行
				enterAction();
				stepAction();//飞行物走一步
				shootAction();//子弹入场
				outOfBoundsAction();//删除越界飞行物
				bangAction();  //子弹与敌人撞
				checkGameOverAction();
				}
				repaint();    //重画，调用paint()
				
			}
		},intervel,intervel);
	}
		
	//重写paint()方法  g：表示画笔	
	public void paint(Graphics g){
		g.drawImage(background,0,0,null); //画背景图
		paintHero(g);
		paintFlyingObjects(g);
		paintBullets(g);
		paintScore(g);      //画分，画名
		paintState(g);
	}
	//画状态
	public void paintState(Graphics g){
		switch(state){
		case START:           //启动状态画启动图
			g.drawImage(start,0,0,null);
			break;
		case PAUSE:           //暂停状态画暂停图
			g.drawImage(pause,0,0,null);
			break;
		case GAME_OVER:       //结束状态画结束图
			g.drawImage(gameover,0,0,null);
			break;
			
		}
	}
	public void paintHero(Graphics g){
		g.drawImage(hero.image,hero.x,hero.y,null);  //画英雄机对象
	}
	public void paintFlyingObjects(Graphics g){
		for(int i =0;i < flyings.length;i++){
			FlyingObject f = flyings[i];
			g.drawImage(f.image,f.x,f.y,null);
		}
	}
	public void paintBullets(Graphics g){
		for(int i = 0;i < bullets.length;i++){
			Bullet b = bullets[i];
			g.drawImage(b.image,b.x,b.y,null);
		}
		
	}
		
	public void paintScore(Graphics g){    //画分，画命
		g.setColor(new Color(0xFF0000));
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
		g.drawString("SCORE: " + score,20,25);
		g.drawString("LIFE: " + hero.getLife(),20, 45);				                    
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Fly");  //窗口对象
		ShootGame game = new ShootGame();  //面板
		frame.add(game);                   //将面板添加到窗口中
		frame.setSize(WIDTH, HEIGHT);      //设置窗口的宽和高
		frame.setAlwaysOnTop(true);        //设置一直在最上面
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置默认关闭的操作：窗口关闭时退出程序
		frame.setLocationRelativeTo(null);  //设置窗口初始位置（居中)
		frame.setVisible(true);             //设置窗体可见
		
		game.action();   //启动执行
	}

}
