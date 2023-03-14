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
//�����������
public class ShootGame extends JPanel {
	public static final int WIDTH = 400;  //���ڵĿ�
	public static final int HEIGHT = 654; //���ڵĸ�
	//��̬��Դ
	public static BufferedImage background; //����ͼ
	public static BufferedImage start;     //��ʼͼ
	public static BufferedImage pause;      //��ͣͼ
	public static BufferedImage gameover;   //��Ϸ����ͼ
	public static BufferedImage airplane;   //�л�ͼ
	public static BufferedImage bee;        //�۷�ͼ
	public static BufferedImage bullet;     //�ӵ�ͼ
	public static BufferedImage hero0;      //Ӣ�ۻ�0ͼ
	public static BufferedImage hero1;      //Ӣ�ۻ�1ͼ
	
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;
	private int state = 0;   //��ǰ״̬
	
	private Hero hero = new Hero();         //Ӣ�ۻ�
	private Bullet[] bullets = {};          //�ӵ�����
	private FlyingObject[] flyings = {};     //��������
	
	
	private Timer timer;
	private int intervel = 10;              //���ʱ�䣺��λ--����
	
	
	//��̬��
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
		int type = rand.nextInt(20);  //����0��19�������
		if(type == 0){                //�����Ϊ0������bee;���򷵻صл�
			return new Bee();
		}else{
			return new Airplane();
		}
	}
		
	int flyEnteredIndex = 0;
	
	//���˵ǳ�
	public void enterAction(){//10������һ��
		flyEnteredIndex++;  //ÿ10������1
		if(flyEnteredIndex % 40 == 0){
			FlyingObject obj = nextOne();
			flyings = Arrays.copyOf(flyings,flyings.length + 1);
			flyings[flyings.length - 1] = obj;//�����˸�ֵ��flyings��������һ��Ԫ��
		}
	}
	
	public void stepAction(){        //10������һ��
		hero.step();                 //Ӣ�ۻ���һ��
		for(int i = 0;i < flyings.length;i++){
			flyings[i].step();       //������һ��
		}
		for(int i = 0;i < bullets.length;i++){
			bullets[i].step();       //�ӵ���һ��
		}
	}
	int shootIndex = 0;
	public void shootAction(){         //10������һ��
		shootIndex++;                 //ÿ10������1
		if(shootIndex % 30 == 0){     //300���뷢��һ���ӵ�			
			Bullet[] bs = hero.shoot();//��ȡ�ӵ�����
			bullets = Arrays.copyOf(bullets,bullets.length + bs.length);
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length,bs.length);
		}
	}
	
	//ɾ��Խ�������
	public void outOfBoundsAction(){
		int index = 0;
		FlyingObject[] flyingLives = new FlyingObject[flyings.length];
		for(int i = 0;i < flyings.length;i++){
			FlyingObject f = flyings[i];
			if(!f.outOfBounds()){
				flyingLives[index] = f;//��Խ�磬����װ��flyingLives[]������
				index++;
			}			
		}		
		flyings = Arrays.copyOf(flyingLives,index);
		
		index = 0;
		Bullet[] bulletsLives = new Bullet[bullets.length];
		for(int i = 0;i < bullets.length;i++){
			Bullet bs = bullets[i];
			if(!bs.outOfBounds()){
				bulletsLives[index] = bs;//��Խ�磬����װ��flyingLives[]������
				index++;
			}			
		}
		bullets = Arrays.copyOf(bulletsLives,index);
	
	}
	
	int score = 0;        //�÷�
	//�����ӵ������е���ײ
	public void bangAction(){
		for(int i = 0;i < bullets.length;i++){
			bang(bullets[i]);
		}
	}
	
	//һ���ӵ������е���ײ
	public void bang(Bullet b){
		int index = -1;//��ײ���˵�����
		for(int i = 0;i < flyings.length;i++){//�������еĵ���
			if(flyings[i].shootBy(b)){//�ж��Ƿ�ײ��
				index = i;                    //��¼��ײ���˵�����
				break;
			}
		}
		if(index != -1){//ײ����
			FlyingObject one = flyings[index]; 
			if(one instanceof Enemy){
				Enemy e = (Enemy)one;
				score += e.getScore();
			}
			if(one instanceof Award){
				Award a = (Award)one;
				int type = a.getType();
				switch(type){
				case Award.DOUBLE_FIRE:      //��������ֵ
					hero.addDoubleFire();    //Ӣ�ۻ����ӻ���
					break;
				case Award.LIFE:             //������
					hero.addLife();          //Ӣ�ۻ�����
					break;				
				}
			}
			//��ײ������flyings�����е����һ��Ԫ�ؽ���
			FlyingObject t = flyings[index];
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = t;
			//���ݣ�ɾ�����һ��Ԫ��---����ײ�Ķ���
			flyings = Arrays.copyOf(flyings, flyings.length  - 1);
		}
	}

	public void checkGameOverAction(){
		if(isGameOver()){    //������Ϸ
			state = GAME_OVER;
		}
	}
	public boolean isGameOver(){
		for(int i = 0;i < flyings.length;i++){   //ײ���ˣ�
			if(hero.hit(flyings[i])){
				hero.subtractLife();          //������1
				hero.setDoubleFire(0);        //����ֵ����
				
				//��ײ֮�󣬽�������
				FlyingObject t = flyings[i];
				flyings[i] = flyings[flyings.length - 1];
				flyings[flyings.length  - 1] = t;
				flyings = Arrays.copyOf(flyings, flyings.length - 1);
			}
		}
		return hero.getLife() <= 0;  //Ӣ�ۻ�����<=0,��Ϸ����
	}
	
	//����ִ�д���
	public void action(){
		MouseAdapter l = new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				if(state == RUNNING){  //����״̬��ִ��
				int x = e.getX();      //���Y����
				int y = e.getY();      //���X����
				hero.moveTo(x, y);     //Ӣ�ۻ���������ƶ����ƶ�
				}
			}
			//���ĵ���¼�
			public void mouseClicked(MouseEvent e){
				switch(state){
				case START:
					state = RUNNING;
					break;
				case GAME_OVER:
					hero = new Hero();//�����ֳ�
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
		this.addMouseListener(l);     //�����������¼�
		this.addMouseMotionListener(l);//��������ƶ��¼�
		
		timer = new Timer();   //������ʱ������
		timer.schedule(new TimerTask(){
			public void run(){  //��ʱ�ɵ��Ǹ���--10������һ��
				if(state == RUNNING){        //����״̬��ִ��
				enterAction();
				stepAction();//��������һ��
				shootAction();//�ӵ��볡
				outOfBoundsAction();//ɾ��Խ�������
				bangAction();  //�ӵ������ײ
				checkGameOverAction();
				}
				repaint();    //�ػ�������paint()
				
			}
		},intervel,intervel);
	}
		
	//��дpaint()����  g����ʾ����	
	public void paint(Graphics g){
		g.drawImage(background,0,0,null); //������ͼ
		paintHero(g);
		paintFlyingObjects(g);
		paintBullets(g);
		paintScore(g);      //���֣�����
		paintState(g);
	}
	//��״̬
	public void paintState(Graphics g){
		switch(state){
		case START:           //����״̬������ͼ
			g.drawImage(start,0,0,null);
			break;
		case PAUSE:           //��ͣ״̬����ͣͼ
			g.drawImage(pause,0,0,null);
			break;
		case GAME_OVER:       //����״̬������ͼ
			g.drawImage(gameover,0,0,null);
			break;
			
		}
	}
	public void paintHero(Graphics g){
		g.drawImage(hero.image,hero.x,hero.y,null);  //��Ӣ�ۻ�����
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
		
	public void paintScore(Graphics g){    //���֣�����
		g.setColor(new Color(0xFF0000));
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
		g.drawString("SCORE: " + score,20,25);
		g.drawString("LIFE: " + hero.getLife(),20, 45);				                    
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Fly");  //���ڶ���
		ShootGame game = new ShootGame();  //���
		frame.add(game);                   //�������ӵ�������
		frame.setSize(WIDTH, HEIGHT);      //���ô��ڵĿ�͸�
		frame.setAlwaysOnTop(true);        //����һֱ��������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //����Ĭ�ϹرյĲ��������ڹر�ʱ�˳�����
		frame.setLocationRelativeTo(null);  //���ô��ڳ�ʼλ�ã�����)
		frame.setVisible(true);             //���ô���ɼ�
		
		game.action();   //����ִ��
	}

}
