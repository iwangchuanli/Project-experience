package SY_8;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;

//扫雷

class SaoLei extends JFrame{
	JMenuBar menubar;
	JMenu game,help;
	JPanel jp;
	JLabel jl1,jl2;
	JTextField text1,text2;
	private JButton[][] button=new JButton[9][9];
	public SaoLei(){
		init();
		setVisible(true);//设置窗口可见
		setBounds(100,100,380,430);//设置窗口位置和大小
		setIconImage(new ImageIcon("images/1.png").getImage());//修改窗口左上角的图标
		setTitle("扫雷");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭当前窗口所在的应用程序
	}
	void init(){
		setLayout(null);//设置空布局
		jp=new JPanel();//初始化面板
		jp.setLayout(null);
		jp.setBounds(0,0,360,400);
		jp.setBackground(Color.lightGray);//设置面板的背景色
		menubar=new JMenuBar();//初始化菜单条
		menubar.setBounds(0,0,380,30);
		menubar.setBackground(new Color(220,225,240));//设置背景颜色
		game=new JMenu("游戏(G)");
		game.setFont(new Font("幼圆",Font.BOLD,16));
		menubar.add(game);
		help=new JMenu("帮助(H)");
		help.setFont(new Font("幼圆",Font.BOLD,16));
		menubar.add(help);
		jp.add(menubar);
		int x=50,y=70;
		for(int i=0;i<9;i++){//循环添加9*9个按钮
			for(int j=0;j<9;j++){
				button[i][j] = new JButton();
				button[i][j].setBounds(x, y, 28, 28);
				button[i][j].setBackground(Color.green);
				x=x+28;
				jp.add(button[i][j]);
			}
			x=50;
			y=y+28;
		}
		//将图片添加到标签里
		ImageIcon icon1 = new ImageIcon("images/11.png");
		icon1.setImage(icon1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT));//设置图片大小
		jl1 = new JLabel(icon1,SwingConstants.CENTER);
		jl1.setBounds(50, 330, 30, 30);
		jp.add(jl1);
		//添加文本框
		text1=new JTextField("   0");
		text1.setBounds(85,330,50,30);
		text1.setFont(new Font("幼圆",0,18));
		text1.setForeground(Color.white);
		text1.setBackground(Color.green);
		add(text1);
		//添加文本框
		text2=new JTextField("  10");
		text2.setBounds(217,330,50,30);
		text2.setFont(new Font("幼圆",0,18));
		text2.setForeground(Color.white);
		text2.setBackground(Color.green);
		add(text2);
		//将图片添加到标签里
		ImageIcon icon2=new ImageIcon("images/22.png");
		icon2.setImage(icon2.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));//设置图片大小
		jl2=new JLabel(icon2,SwingConstants.CENTER);
		jl2.setBounds(272,330,30,30);
		jp.add(jl2);
		add(jp);//添加面板
	}
}
public class SY_8_6 {
	public static void main(String[] args) {
		SaoLei s=new SaoLei();
	}
}