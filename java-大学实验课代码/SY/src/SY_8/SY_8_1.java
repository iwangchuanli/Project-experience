package SY_8;


import java.awt.*;
import javax.swing.*;

class jiSuanQi extends JFrame{
	JPanel jp;//面板
	JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8;//标签
	JButton button;//按钮
	JTextField text1,text2;//文本框
	JComboBox comBox1,comBox2,comBox3;//下拉列表
	JRadioButton radio1,radio2;//单选按钮
	ButtonGroup group;
	public jiSuanQi(){//构造函数
		init();		
		setVisible(true);//设置可见
		setBounds(100,100,500,320);//设置窗口大小
		setTitle("房贷计算器");//设置标题
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口所在的应用程序
	}
	void init(){
		setLayout(null);//设置空布局
		jp=new JPanel();//初始化面板
		jp.setLayout(null);//将面板设置为空布局
		jp.setBounds(15,15,480,270);//设置面板大小
		//添加标签
		jl1=new JLabel("贷款金额");
		jl1.setFont(new Font("幼圆",0,14));//设置字体，风格，字号
		jl1.setBounds(10,10,65,30);
		jp.add(jl1);
		//添加文本框
		text1=new JTextField();
		text1.setFont(new Font("幼圆",0,14));//设置字体，风格，字号
		text1.setBounds(76,10,100,30);
		jp.add(text1);
		//添加标签
		jl2=new JLabel(" 万元");
		jl2.setFont(new Font("幼圆",0,14));//设置字体，风格，字号
		jl2.setBounds(177,10,60,30);
		jp.add(jl2);
		//添加标签
		jl3=new JLabel("贷款期限");
		jl3.setFont(new Font("幼圆",0,14));//设置字体，风格，字号
		jl3.setBounds(10,50,65,30);
		jp.add(jl3);
		//添加下拉列表
		comBox1=new JComboBox();
		comBox1.setBounds(76,50,150,30);
		comBox1.addItem("20年(240)期");
		comBox1.setFont(new Font("幼圆",0,14));//设置字体，风格，字号
		jp.add(comBox1);
		//添加标签
		jl4=new JLabel("贷款利率");
		jl4.setFont(new Font("幼圆",0,14));//设置字体，风格，字号
		jl4.setBounds(10,90,65,30);
		jp.add(jl4);
		//添加下拉列表
		comBox2=new JComboBox();
		comBox2.setBounds(76,90,150,30);
		comBox2.addItem("最新基准利率");
		comBox2.setFont(new Font("幼圆",0,14));//设置字体，风格，字号
		jp.add(comBox2);
		//添加下拉列表
		comBox3=new JComboBox();
		comBox3.setBounds(230,90,100,30);
		comBox3.addItem("无折扣");
		comBox3.setFont(new Font("幼圆",0,14));//设置字体，风格，字号
		jp.add(comBox3);
		//添加标签
		jl5=new JLabel("=");
		jl5.setFont(new Font("幼圆",0,14));//设置字体，风格，字号
		jl5.setBounds(334,90,15,30);
		jp.add(jl5);
		text2=new JTextField("  5.15");
		text2.setFont(new Font("幼圆",0,14));//设置字体，风格，字号
		text2.setBounds(349,90,80,30);
		jp.add(text2);
		//添加标签
		jl6=new JLabel("%");
		jl6.setFont(new Font("幼圆",0,14));//设置字体，风格，字号
		jl6.setBounds(432,90,15,30);
		jp.add(jl6);
		//添加标签
		jl7=new JLabel("还款方式");
		jl7.setFont(new Font("幼圆",0,14));//设置字体，风格，字号
		jl7.setBounds(10,130,65,30);
		jp.add(jl7);
		//添加单选按钮
		group=new ButtonGroup();
		radio1=new JRadioButton("等额本息");
		radio1.setFont(new Font("幼圆",0,14));//设置字体，风格，字号
		radio1.setBounds(76,130,90,30);
		radio1.setSelected(true);
		radio2=new JRadioButton("等额本金");
		radio2.setFont(new Font("幼圆",0,14));//设置字体，风格，字号
		radio2.setBounds(178,130,90,30);
		group.add(radio1);
		group.add(radio2);
		jp.add(radio1);
		jp.add(radio2);
		//添加按钮
		button=new JButton("立即计算");
		button.setFont(new Font("幼圆",Font.BOLD,20));//设置字体，风格，字号
		button.setForeground(Color.white);//设置字体的颜色
		button.setBounds(20,180,150,40);
		button.setBackground(new Color(87,179,0));//设置按钮背景颜色
		jp.add(button);
		//添加图片标签
		ImageIcon icon=new ImageIcon("images/2.jpg");
		icon.setImage(icon.getImage().getScaledInstance(60, 30, Image.SCALE_DEFAULT));//设置图片的大小
		jl8=new JLabel(icon);
		jl8.setBounds(380,185,60,40);
		jp.add(jl8);
		add(jp);//将面板添加到底层容器中
	}
}

public class SY_8_1 {
	public static void main(String[] args) {
		jiSuanQi jsq=new jiSuanQi();
	}
}

