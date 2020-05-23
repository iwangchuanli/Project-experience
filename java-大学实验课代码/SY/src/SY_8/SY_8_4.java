package SY_8;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;

/*
 * QQ登陆界面
 */
class QQ extends JFrame{
	JPanel jp;//面板
	JComboBox comBox;//下拉列表
	JPasswordField pass;//密码框
	JCheckBox checkBox1,checkBox2;//选择框
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;//标签
	JButton button;//按钮
	public QQ(){//构造函数
		init();
		setVisible(true);//设置为可见
		setIconImage(new ImageIcon("images/企鹅.PNG").getImage());//修改窗口左上角图标
		setTitle("QQ");//设置标题
		setBounds(100,100,558,465);//设置窗口大小
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//释放窗口
	}
	void init(){
		setLayout(null);//将底层容器设置成空布局
		jp=new JPanel();//初始化面板
		jp.setLayout(null);//将面板设置成空布局
		jp.setBounds(0,0,540,420);//设置面板大小
		//添加图片
		ImageIcon icon1=new ImageIcon("images/QQ.png");
		icon1.setImage(icon1.getImage().getScaledInstance(540, 220, Image.SCALE_DEFAULT));//设置图片的大小
		jl1=new JLabel(icon1);//初始化标签并将图片放在标签里
		jl1.setBounds(0,0,540,220);
		jp.add(jl1);
		//添加图片
		ImageIcon icon2=new ImageIcon("images/头像.png");
		icon2.setImage(icon2.getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));//设置图片的大小
		jl2=new JLabel(icon2);//初始化标签并将图片放在标签里
		jl2.setBounds(50,240,110,110);
		jp.add(jl2);
		//添加图片
		ImageIcon icon3=new ImageIcon("images/3.png");
		icon3.setImage(icon3.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		jl3=new JLabel(icon3);
		jl3.setBounds(5,375,35,35);
		jp.add(jl3);
		//添加图片
		ImageIcon icon4=new ImageIcon("images/4.png");
		icon4.setImage(icon4.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		jl4=new JLabel(icon4);
		jl4.setBounds(500,375,35,35);
		//添加下拉列表
		comBox=new JComboBox();
		String defaultMessage1 = "QQ号码/手机号/邮箱";
		comBox.setFont(new Font("幼圆",0,16));//设置字体，风格，字号
		comBox.setEditable(true);//设置下拉列表JComboBox处于可编辑状态
		ComboBoxEditor editor = comBox.getEditor();
	    comBox.configureEditor(editor,defaultMessage1);//将默认信息添加到下拉列表中
		comBox.setBackground(Color.white);//设置下拉列表的背景色
		comBox.setBounds(170,240,240,40);//设置下拉列表的位置和大小
		jp.add(comBox);//将下拉列表控件添加到面板上
		//添加密码框
		pass=new JPasswordField();
		pass.setFont(new Font("幼圆",0,16));
		pass.setBounds(170,280,241,40);
		jp.add(pass);
		//添加标签
		jp.add(jl4);
		jl5=new JLabel("注册账号");
		jl5.setFont(new Font("幼圆",0,16));
		jl5.setForeground(Color.blue);//设置字体颜色
		jl5.setBounds(420,240,70,40);
		//添加标签
		jp.add(jl5);
		jl6=new JLabel("找回密码");
		jl6.setFont(new Font("幼圆",0,16));
		jl6.setForeground(Color.blue);//设置字体颜色
		jl6.setBounds(420,280,70,40);
		jp.add(jl6);
		//添加选择框
		checkBox1=new JCheckBox("记住密码");
		checkBox1.setFont(new Font("幼圆",0,16));
		checkBox1.setBounds(170,325,90,30);
		jp.add(checkBox1);
		//添加选择框
		checkBox2=new JCheckBox("自动登录");
		checkBox2.setFont(new Font("幼圆",0,16));
		checkBox2.setBounds(325,325,90,30);
		jp.add(checkBox2);
		//添加按钮
		button=new JButton("登  录");
		button.setFont(new Font("幼圆",0,16));
		button.setForeground(Color.white);//设置字体颜色
		button.setBackground(new Color(9,163,220));//设置按钮的背景颜色
		button.setBorder(null);//设置按钮的边框为无
		button.setBounds(170,360,240,40);
		jp.add(button);
		add(jp);//将面板添加到底层容器中
	}
}
class QQRegister extends JFrame{
	JPanel jp1;//面板
	JLabel j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11;//标签
	JTextField text1,text2;//文本框
	JPasswordField pwd1,pwd2;//密码框
	JRadioButton radio1,radio2;//单选按钮
	ButtonGroup group;
	JComboBox comBox1,comBox2,comBox3,comBox4,comBox5,comBox6,comBox7;//下拉列表
	JButton button;//按钮
	JCheckBox checkBox1,checkBox2;//选择框
	public QQRegister(){//构造函数
		init();
		setVisible(true);//设置可见
		setIconImage(new ImageIcon("images/企鹅.PNG").getImage());//修改窗口左上角图标
		setTitle("QQ注册");
		setBounds(700,100,660,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口所在的应用程序
	}
	void init(){
		setLayout(null);//将底层容器设置成空布局
		jp1=new JPanel();//初始化面板
		jp1.setLayout(null);//将面板设置为空布局
		jp1.setBounds(0,0,640,550);//设置面板大小
		//添加标签
		j1 = new JLabel("昵称");
		j1.setFont(new Font("幼圆",0,18));//设置字体，风格，字号
		j1.setBounds(50,25,40,30);
		jp1.add(j1);
		//添加文本框
		text1 = new JTextField();
		text1.setFont(new Font("幼圆",0,18));//设置字体，风格，字号
		text1.setBounds(100,20,375,40);
		jp1.add(text1);
		//添加标签
		j2 = new JLabel("*昵称不可以为空");
		j2.setFont(new Font("幼圆",0,18));//设置字体，风格，字号
		j2.setForeground(new Color(254,116,116));//设置字体颜色
		j2.setBounds(485,25,135,30);
		jp1.add(j2);
		//添加标签
		j3 = new JLabel("密码");
		j3.setFont(new Font("幼圆",0,18));//设置字体，风格，字号
		j3.setBounds(50,75,40,30);
		jp1.add(j3);
		//添加密码框
		pwd1 = new JPasswordField();
		pwd1.setFont(new Font("幼圆",0,18));
		pwd1.setBounds(100,70,375,40);
		jp1.add(pwd1);
		//添加标签
		j4 = new JLabel("确认密码");
		j4.setFont(new Font("幼圆",0,18));
		j4.setBounds(15,125,75,30);
		jp1.add(j4);
		//添加密码框
		pwd2 = new JPasswordField();
		pwd2.setFont(new Font("幼圆",0,18));
		pwd2.setBounds(100,120,375,40);
		jp1.add(pwd2);
		//添加标签
		j5 = new JLabel("性别");
		j5.setFont(new Font("幼圆",0,18));
		j5.setBounds(50,170,40,30);
		jp1.add(j5);
		//添加单选按钮
		group = new ButtonGroup();
		radio1 = new JRadioButton("男");
		radio1.setFont(new Font("幼圆",0,18));
		radio1.setForeground(Color.blue);//设置字体颜色
		radio1.setBounds(100,170,50,30);
		radio1.setSelected(true);
		radio2 = new JRadioButton("女");
		radio2.setFont(new Font("幼圆",0,18));
		radio2.setForeground(Color.blue);//设置字体颜色
		radio2.setBounds(180,170,50,30);
		group.add(radio1);
		group.add(radio2);
		jp1.add(radio1);
		jp1.add(radio2);
		//添加标签
		j6 = new JLabel("生日");
		j6.setFont(new Font("幼圆",0,18));
		j6.setBounds(50,210,40,30);
		jp1.add(j6);
		//添加下拉列表
		comBox1 = new JComboBox();
		comBox1.addItem("公历");
		comBox1.setFont(new Font("幼圆",0,18));
		comBox1.setForeground(Color.blue);//设置字体颜色
		comBox1.setBackground(new Color(243,249,255));//设置下拉列表背景颜色
		comBox1.setBounds(100,210,80,30);
		jp1.add(comBox1);
		//添加下拉列表
		comBox2 = new JComboBox();
		comBox2.addItem("年");
		comBox2.setFont(new Font("幼圆",0,18));
		comBox2.setForeground(Color.blue);//设置字体颜色
		comBox2.setBackground(new Color(243,249,255));//设置下拉列表背景颜色
		comBox2.setBounds(185,210,120,30);
		jp1.add(comBox2);
		//添加下拉列表
		comBox3 = new JComboBox();
		comBox3.addItem("月");
		comBox3.setFont(new Font("幼圆",0,18));
		comBox3.setForeground(Color.blue);//设置字体颜色
		comBox3.setBackground(new Color(243,249,255));//设置下拉列表背景颜色
		comBox3.setBounds(310,210,80,30);
		jp1.add(comBox3);
		//添加下拉列表
		comBox4 = new JComboBox();
		comBox4.addItem("日");
		comBox4.setFont(new Font("幼圆",0,18));
		comBox4.setForeground(Color.blue);//设置字体颜色
		comBox4.setBackground(new Color(243,249,255));//设置下拉列表背景颜色
		comBox4.setBounds(395,210,80,30);
		jp1.add(comBox4);
		//添加标签
		j7 = new JLabel("所在地");
		j7.setFont(new Font("幼圆",0,18));
		j7.setBounds(30,250,60,30);
		jp1.add(j7);
		//添加下拉列表
		comBox5 = new JComboBox();
		comBox5.addItem("中国");
		comBox5.setFont(new Font("幼圆",0,18));
		comBox5.setForeground(Color.blue);//设置字体颜色
		comBox5.setBackground(new Color(243,249,255));//设置下拉列表背景颜色
		comBox5.setBounds(100,250,122,30);
		jp1.add(comBox5);
		//添加下拉列表
		comBox6 = new JComboBox();
		comBox6.addItem("安徽");
		comBox6.setFont(new Font("幼圆",0,18));
		comBox6.setForeground(Color.blue);//设置字体颜色
		comBox6.setBackground(new Color(243,249,255));//设置下拉列表背景颜色
		comBox6.setBounds(227,250,122,30);
		jp1.add(comBox6);
		//添加下拉列表
		comBox7 = new JComboBox();
		comBox7.addItem("黄山");
		comBox7.setFont(new Font("幼圆",0,18));
		comBox7.setForeground(Color.blue);//设置字体颜色
		comBox7.setBackground(new Color(243,249,255));//设置下拉列表背景颜色
		comBox7.setBounds(354,250,121,30);
		jp1.add(comBox7);
		//添加标签
		j8 = new JLabel("手机号码");
		j8.setFont(new Font("幼圆",0,18));
		j8.setBounds(15,295,75,30);
		jp1.add(j8);
		//添加文本框
		text2 = new JTextField();
		text2.setFont(new Font("幼圆",0,18));
		text2.setBounds(100,290,375,40);
		jp1.add(text2);
		//添加标签
		j9 = new JLabel("可通过该手机号码快速找回密码");
		j9.setFont(new Font("幼圆",0,16));
		j9.setBounds(100,340,225,20);
		jp1.add(j9);
		//添加标签
		j10 = new JLabel("中国大陆地区以外手机号码");
		j10.setFont(new Font("幼圆",0,16));
		j10.setBounds(100,365,195,20);
		jp1.add(j10);
		//添加标签
		j11 = new JLabel("点击这里");
		j11.setFont(new Font("幼圆",0,16));
		j11.setForeground(Color.blue);//设置字体颜色
		j11.setBounds(305,365,65,20);
		jp1.add(j11);
		//添加按钮
		button = new JButton("立即注册");
		button.setFont(new Font("幼圆",0,26));
		button.setForeground(Color.white);//设置字体颜色
		button.setBackground(new Color(105,185,70));
		button.setBounds(100,400,375,60);
		jp1.add(button);
		//添加选择框
		checkBox1 = new JCheckBox("同意开通QQ空间");
		checkBox1.setFont(new Font("幼圆",0,16));
		checkBox1.setBounds(100,480,150,20);
		checkBox1.setSelected(true);//设置为默认选中
		add(checkBox1);
		//添加选择框
		checkBox2 = new JCheckBox("我已阅读并同意相关服务条款和隐私政策");
		checkBox2.setFont(new Font("幼圆",0,16));
		checkBox2.setBounds(100,510,315,20);
		checkBox2.setSelected(true);//设置为默认选中
		add(checkBox2);
		add(jp1);//添加面板
	}
}
public class SY_8_4 {
	public static void main(String[] args) {
		new QQ();
		new QQRegister();
	}
}
