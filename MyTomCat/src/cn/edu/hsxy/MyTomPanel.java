package cn.edu.hsxy;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyTomPanel extends JPanel implements Runnable,MouseListener{
	// 背景图
	BufferedImage image;
	//按钮
	BufferedImage eat,drink,cymbal,fart,pie,scratch;
	//存储图片的路径   动作图片
	String[] images = new String[100];
	//图片集转换值 现在显示第几张图
	int index = 0;
	//图片数量总量
	int photoIndex = 0;
	//
	boolean flag = false;
	Thread thread = null;
	//构造方法
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
		
//		g.drawRect(80, 60, 200, 150);//头
//		g.drawRect(80, 220, 200, 100);
//		g.drawRect(100, 350, 150, 150);//肚子
//		g.drawRect(90, 500, 80, 80);//左脚
//		g.drawRect(180, 500, 80, 80);//右脚
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(flag){
			//修改坐标值		
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
	@Override//单击
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//
		int mouseX = e.getX();
		int mouseY = e.getY();
		//点头
		if (mouseX>80 && mouseX < 80+200 && mouseY > 60 && mouseY < 60+150) {
			//有多少张要显示
			this.photoIndex = 81;
			//图片路径地址
			saveImage("Knockout");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		//生气
		if (mouseX>80 && mouseX < 80+200 && mouseY > 220 && mouseY < 220+100) {
			//有多少张要显示
			this.photoIndex = 26;
			//图片路径地址
			saveImage("Angry");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		//肚子
		if (mouseX>100 && mouseX < 80+150 && mouseY > 350 && mouseY < 350+150) {
			//有多少张要显示
			this.photoIndex = 34;
			//图片路径地址
			saveImage("Stomach");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
//		g.drawRect(80, 60, 200, 150);//头
//		g.drawRect(80, 220, 200, 100);
//		g.drawRect(100, 350, 150, 150);//肚子
//		g.drawRect(90, 500, 80, 80);//左脚
//		g.drawRect(180, 500, 80, 80);//右脚
		//左脚
		if (mouseX>90 && mouseX < 90+80 && mouseY > 500 && mouseY < 500+80) {
			//有多少张要显示
			this.photoIndex = 30;
			//图片路径地址
			saveImage("FootRight");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		//右脚
		if (mouseX>180 && mouseX < 180+80 && mouseY > 500 && mouseY < 500+80) {
			//有多少张要显示
			this.photoIndex = 30;
			//图片路径地址
			saveImage("FootLeft");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		
		
		
		//吃鸟
		if (mouseX>10 && mouseX < 10+50 && mouseY > 200 && mouseY < 200+50) {
			//有多少张要显示
			this.photoIndex = 40;
			//图片路径地址
			saveImage("eat");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		//喝奶
		if (mouseX>10 && mouseX < 10+50 && mouseY > 250 && mouseY < 250+50) {
			//有多少张要显示
			this.photoIndex = 80;
			//图片路径地址
			saveImage("drink");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		//cymbal
		if (mouseX>10 && mouseX < 10+50 && mouseY > 300 && mouseY < 300+50) {
			//有多少张要显示
			this.photoIndex = 12;
			//图片路径地址
			saveImage("cymbal");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		//fart
		if (mouseX>10 && mouseX < 10+50 && mouseY > 350 && mouseY < 350+50) {
			//有多少张要显示
			this.photoIndex = 27;
			//图片路径地址
			saveImage("fart");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		
		if (mouseX>10 && mouseX < 10+50 && mouseY > 400 && mouseY < 400+50) {
			//有多少张要显示
			this.photoIndex = 23;
			//图片路径地址
			saveImage("pie");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		
		if (mouseX>10 && mouseX < 10+50 && mouseY > 450 && mouseY < 450+50) {
			//有多少张要显示
			this.photoIndex = 55;
			//图片路径地址
			saveImage("scratch");
			flag = true;
			thread = new Thread(this);
			thread.start();	
		}
		
	}
	@Override//鼠标双击
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override//鼠标离开
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override//按下
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override//释放
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
