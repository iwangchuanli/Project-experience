package SY_9;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

/*
 * 事件响应实例，书237页例子6，
 * 用第二种方式建立监视器（窗口容器作为实现事件处理接口的类）
 */
public class MyFrame8 extends JFrame
             implements KeyListener
{
	private static final long serialVersionUID = 1L;
	//定义组件
	JTextField text1,text2;
	Font f=new Font("宋体",Font.BOLD,35);
	//设置容器的基本属性
	public MyFrame8()
	{   init();	
		this.setTitle("事件处理实例");
		this.setBounds(500, 300, 300,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//选用什么布局，添加相应的组件
	public void init()
	{  this.setLayout(new FlowLayout());
	   text1=new JTextField(10);
	   text1.setFont(f);
	   text1.addKeyListener(this);
	   this.add(text1);
	   text2=new JTextField(10);
	   text2.setFont(f);
	   text2.setEditable(false);
	   this.add(text2);
	}
		public void keyPressed(KeyEvent arg0) {		
	}
	public void keyReleased(KeyEvent arg0) {		text2.setText(text1.getText());	
	}
		public void keyTyped(KeyEvent arg0) {		
	}	
}
