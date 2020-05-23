package SY_9;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class Calculator extends JFrame{
	JMenuBar menuBar;
	JMenu menu1,menu2,menu3;
	
	   // 小容器
    private JLabel rq1;
    private JLabel rq2;
    JTextArea jsk;
    
    JButton bu0;
    JButton bu1;
    JButton bu2;
    JButton bu3;
    JButton bu4;
    JButton bu5;
    JButton bu6;
    JButton bu7;
    JButton bu8;
    JButton bu9;
    
    JButton op1;
    JButton op2;
    JButton op3;
    JButton op4;
    
    JButton js;
	
	public Calculator(){//构造方法
		this.setTitle("计算器");
        init();// 窗体组件初始化
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);    // 设置布局方式为绝对定位
        this.setBounds(0, 0, 500, 300);
        this.setResizable(false);      // 窗体大小不能改变
        this.setLocationRelativeTo(null); // 居中显示
        this.setVisible(true); // 窗体可见
    }
	
	public void init(){
        Container con = this.getContentPane();// 创建一个容器
        
        menuBar=new JMenuBar();
        menu1=new JMenu("查看");
        menu1.setAccelerator(KeyStroke.getKeyStroke('V'));
        menu2=new JMenu("编辑");
       menu2.setAccelerator(KeyStroke.getKeyStroke('E'));
        menu3=new JMenu("帮助");
        menu3.setAccelerator(KeyStroke.getKeyStroke('H'));
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        setJMenuBar(menuBar);
        
        jsk.setBounds(100, 100, 150, 20);
        con.add(jsk);
        
        bu0=new JButton("0");
        bu0.setBounds(0, 1, 2, 3);
        bu0.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub			
			}
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
        
	}
	
	 public static void main(String[] args) {
	        // 实例化对象
	        Calculator calculator = new Calculator();
	    }
	 
	}

