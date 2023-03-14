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
 * 1��ע�ͼӿ��
 * 2�������ƶ�
 * 		���� background
 * 		��ʼ�� static
 * 		��ͼ
 * 		ͼƬ���ƶ�
 * 3��ս��
 * 
 * */

public class ShootPanel extends JPanel implements Runnable,MouseMotionListener{
	/**��һ���򣺱�����������*/
	// ��̬��  �������� ��Զ����
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
	
	//Ӣ�ۻ�
	Hero hero = new Hero();
	//�ӵ�
	static BufferedImage bullet;
	Bullet [] bullets={};
	//
	int flyIndex = 0;
	                
	//�л�ͼƬ
	static BufferedImage flys0;
	static BufferedImage flys1;
	static BufferedImage flys2;
	static BufferedImage flys3;
	static BufferedImage flys4;
	static BufferedImage flys5;
	//�л����� flys �������ĺ͵л�
	FlyObject [] flys ={};
	//Airplane airplane = new Airplane();
	//����
	static BufferedImage qq00;
	static BufferedImage qq01;
	static BufferedImage qq02;
	static BufferedImage qq03;
	static BufferedImage qq04;
	static BufferedImage qq05;
	static BufferedImage qq06;
	static BufferedImage qq07;
	static BufferedImage qq08;
	
	
	
	/**�ڶ����򣺾�̬ͼƬ��ʼ������*/
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

	//���췽��
	public ShootPanel() {
		// TODO Auto-generated constructor stub
	}
	
	/**�������򻭱ʷ�������*/
	//paint
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		// 3.1 ������ͼ��������
		paintBackground(g);
		// 3.2��Ӣ�ۻ�
		paintHero(g);
		//���ӵ�
		paintBullet(g);
		// 3.4 ��������
		paintFlyObject(g);
		
	}
	// 3.1 ������ͼ����
	public void paintBackground(Graphics g){
		backY++;
		g.drawImage(background, 0, backY, 512, 768, null);
		g.drawImage(background, 0, backY-768, 512, 768, null);
		if (backY > 768) {
			backY = 0;
		}
	}
	//3.2��Ӣ�ۻ�
	public void paintHero(Graphics g){
		g.drawImage(hero.image, hero.x, hero.y, hero.width, hero.height, null);
	}

	//���ӵ�
	public void paintBullet(Graphics g){
		for (int i = 0; i < bullets.length; i++) {
			Bullet bt = bullets[i];
			g.drawImage(bt.image, bt.x, bt.y, bt.width, bt.height, null);
		}
	}
	//��������
	public void paintFlyObject(Graphics g){
		for (int i = 0; i < flys.length; i++) {
			FlyObject object = flys[i];
			g.drawImage(object.image, object.x, object.y,object.width, object.height, null);
		}
	}
	
	/**��������ҵ���߼���������*/
	//�߳�
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			//1 �޸�����ֵ
			//4.1����������
			enterAction();
			//4.2��һ��
			stepAction();
			//4.3����
			outOfBoundsAction();
			// 4.4 ��ײ�ж�
			hitAction();
			
			//2 ����˯��
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//3 �����ػ�
			repaint();
		}	
	}
	
	//����
	public void enterAction(){
		flyIndex++;
		//���ɰ��ĺ͵л�
		if (flyIndex % 60 == 0) {
			FlyObject object = null;
			int type = (int)(Math.random()*20);
			if (type<4) {
				object = new Love();
			}else{
				object = new Airplane();
			}
			//�ŵ�flys��  ��������
			flys = Arrays.copyOf(flys,flys.length+1);
			flys[flys.length-1] = object;	
		}
		//����Ӣ���ӵ�
		if (flyIndex % 40 == 0) {
			//����  ˫��
			Bullet[] bs = hero.shootBy();
			//��������
			bullets = Arrays.copyOf(bullets,bullets.length+bs.length);
			//�����������
			//src srcPos dest  dest
			//ԭ����  ԭ������Ҫ�����￪ʼ���  Ŀ������   ��Ŀ�������ʲôλ�ÿ�ʼ  ��Ҫ���Ƽ�����Ŀ��������
			System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length);
//			Bullet bt = new Bullet(hero.x,hero.y);
//			//bullets�Ǹ��ƺ�����鳤��
//			//bullets-1 �����һ��������±��
//			bullets[bullets.length-1] = bt;
		}

		
	}
	//��һ��
	public void stepAction(){
		hero.step();
		/**��������һ��*/
		for (int i = 0; i < flys.length; i++) {
			FlyObject object = flys[i];
			object.step();
		}
		//�ӵ�
		for (int i = 0; i < bullets.length; i++) {
			Bullet bt = bullets[i];
			bt.step();
		}
	}
	// 4.3 ����
	public void outOfBoundsAction(){
		//�ӵ�����
		for (int i = 0; i < bullets.length; i++) {
			Bullet bt = bullets[i];
			if (bt.outOfBounds()) {
				//���������
				bullets[i] = bullets[bullets.length-1];
				bullets = Arrays.copyOf(bullets,bullets.length-1);
				break;
			}
		}
	}
	
	// 4.4 ��ײ
	public void hitAction(){
		for (int i = 0; i < bullets.length; i++) {
			Bullet bt =bullets[i];
			hit(bt,i);	//�ӵ�����  �±�		
		}
	}
	//��ײ�ж�
	public void hit(Bullet bt,int btIndex){
		//���� һ������
		int hitIndex = -1;
		//�����������ӵ����Ա�
		for (int i = 0; i < flys.length; i++) {
			FlyObject object = flys[i];
			//���÷������е���ײ�ж�
			//true����ײ
			if (object.hitBy(bt)) {
				//����ײ��������±��hitIndex
				//
				hitIndex = i;
				break;
			}
		}
		//�ж�
		if (hitIndex != -1) {
			FlyObject object = flys[hitIndex];
			if (object instanceof Airplane) {
				//�ӷ� score += object.getScore();
				
			}
			if (object instanceof Love) {
				int awardType = ((Love)object).getAward();
				switch (awardType) {
				case Award.LIFE:
					//����
					hero.addLife();
					break;
				case Award.DoubleFire:
					//�ӻ���.
					hero.addFire();
					break;

				}
			}
		/**�ӵ�������*/
		bullets[btIndex]=bullets[bullets.length-1];
		bullets =Arrays.copyOf(bullets, bullets.length-1);
		/**�����������*/
		flys[hitIndex]=flys[flys.length-1];
		flys =Arrays.copyOf(flys, flys.length-1);
		}
	}
	/**������������������*/

	@Override//�����ק
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override//����ƶ�
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int mouseX = e.getX();
		int mouseY = e.getY();
		hero.moveTo(mouseX,mouseY);
	}

}
