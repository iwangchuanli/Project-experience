package cn.edu.hsxy;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyTomPanel extends JPanel implements Runnable,MouseListener{
	// ����ͼ
	BufferedImage image;
	//��ť
	BufferedImage eat,drink,cymbal,fart,pie,scratch;
	//�洢ͼƬ��·��   ����ͼƬ
	String[] images = new String[100];
	//ͼƬ��ת��ֵ ������ʾ�ڼ���ͼ
	int index = 0;
	//ͼƬ��������
	int photoIndex = 0;
	//
	boolean flag = false;
	Thread thread = null;
	//���췽��
	public MyTomPanel() {
		// TODO Auto-generated constructor stub
		try {
			image = ImageIO.read(MyTomPanel.class.getResource("Animations/Eat/eat_00.jpg"));
			eat = ImageIO.read(MyTomPanel.class.getResource("buttons/eat.png"));
			drink = ImageIO.read(MyTomPanel.class.getResource("buttons/drink.png"));
			cymbal = ImageIO.read(MyTomPanel.class.getResource("buttons/cymbal.png"));
			fart = ImageIO.read(MyTomPanel.class.getResource("buttons/fart.png"));
			pie = ImageIO.read(MyTomPanel.class.getResource("buttons/pie.png"));
			scratch = ImageIO.read(MyTomPanel.class.getResource("buttons/scratch.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		g.drawImage(image, 0, 0, 350, 600, null);
		
		g.drawImage(eat, 10, 200, 50, 50, null);
		g.drawImage(drink, 10, 250, 50, 50, null);
		g.drawImage(cymbal, 10, 300, 50, 50, null);
		
		g.drawImage(fart, 10, 350, 50, 50, null);
		g.drawImage(pie, 10, 400, 50, 50, null);
		g.drawImage(scratch, 10, 450, 50, 50, null);
		
//		g.drawRect(80, 60, 200, 150);//ͷ
//		g.drawRect(80, 220, 200, 100);
//		g.drawRect(100, 350, 150, 150);//����
//		g.drawRect(90, 500, 80, 80);//���
//		g.drawRect(180, 500, 80, 80);//�ҽ�
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(flag){
			//�޸�����ֵ		
				index++;
			if (index >= photoIndex-1) {
				index = 0;
				photoIndex = 0;
				flag = false;
			}
				try {
					this.image = ImageIO.read(MyTomPanel.class.getResource(images[index]));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			//
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//
			repaint();
			
			
		}
		
	}
	@Override//����
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//
		int mouseX = e.getX();
		int mouseY = e.getY();
		//��ͷ
		if (mouseX>80 && mouseX < 80+200 && mouseY > 60 && mouseY < 60+150) {
			//�ж�����Ҫ��ʾ
			this.photoIndex = 81;
			//ͼƬ·����ַ
			saveImage("Knockout");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		//����
		if (mouseX>80 && mouseX < 80+200 && mouseY > 220 && mouseY < 220+100) {
			//�ж�����Ҫ��ʾ
			this.photoIndex = 26;
			//ͼƬ·����ַ
			saveImage("Angry");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		//����
		if (mouseX>100 && mouseX < 80+150 && mouseY > 350 && mouseY < 350+150) {
			//�ж�����Ҫ��ʾ
			this.photoIndex = 34;
			//ͼƬ·����ַ
			saveImage("Stomach");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
//		g.drawRect(80, 60, 200, 150);//ͷ
//		g.drawRect(80, 220, 200, 100);
//		g.drawRect(100, 350, 150, 150);//����
//		g.drawRect(90, 500, 80, 80);//���
//		g.drawRect(180, 500, 80, 80);//�ҽ�
		//���
		if (mouseX>90 && mouseX < 90+80 && mouseY > 500 && mouseY < 500+80) {
			//�ж�����Ҫ��ʾ
			this.photoIndex = 30;
			//ͼƬ·����ַ
			saveImage("FootRight");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		//�ҽ�
		if (mouseX>180 && mouseX < 180+80 && mouseY > 500 && mouseY < 500+80) {
			//�ж�����Ҫ��ʾ
			this.photoIndex = 30;
			//ͼƬ·����ַ
			saveImage("FootLeft");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		
		
		
		//����
		if (mouseX>10 && mouseX < 10+50 && mouseY > 200 && mouseY < 200+50) {
			//�ж�����Ҫ��ʾ
			this.photoIndex = 40;
			//ͼƬ·����ַ
			saveImage("eat");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		//����
		if (mouseX>10 && mouseX < 10+50 && mouseY > 250 && mouseY < 250+50) {
			//�ж�����Ҫ��ʾ
			this.photoIndex = 80;
			//ͼƬ·����ַ
			saveImage("drink");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		//cymbal
		if (mouseX>10 && mouseX < 10+50 && mouseY > 300 && mouseY < 300+50) {
			//�ж�����Ҫ��ʾ
			this.photoIndex = 12;
			//ͼƬ·����ַ
			saveImage("cymbal");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		//fart
		if (mouseX>10 && mouseX < 10+50 && mouseY > 350 && mouseY < 350+50) {
			//�ж�����Ҫ��ʾ
			this.photoIndex = 27;
			//ͼƬ·����ַ
			saveImage("fart");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		
		if (mouseX>10 && mouseX < 10+50 && mouseY > 400 && mouseY < 400+50) {
			//�ж�����Ҫ��ʾ
			this.photoIndex = 23;
			//ͼƬ·����ַ
			saveImage("pie");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		
		if (mouseX>10 && mouseX < 10+50 && mouseY > 450 && mouseY < 450+50) {
			//�ж�����Ҫ��ʾ
			this.photoIndex = 55;
			//ͼƬ·����ַ
			saveImage("scratch");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		
	}
	@Override//���˫��
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override//����뿪
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override//����
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override//�ͷ�
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void saveImage(String name){
		for (int i = 0; i < photoIndex; i++) {
			if (i < 10) {
				images[i] = "Animations/"+name+"/"+name+"_0"+i+".jpg";
			}else {
				images[i] = "Animations/"+name+"/"+name+"_"+i+".jpg";
			}
		}
	}
	
}
