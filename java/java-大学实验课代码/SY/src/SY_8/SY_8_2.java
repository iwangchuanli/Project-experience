package SY_8;

import java.awt.*;
import javax.swing.*;

/*
 * 车贷计算器
 */
class cheDaiJiSuanQi extends JFrame{
	JPanel jp1,jp2,jp3;
	JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10,jl11,jl12,jl13,jl14,jl15;
	JLabel label1,label2,label3,label4,label5,label6;
	JTextField text1,text2,text3,text4,text5;
	JRadioButton radio1,radio2;
	ButtonGroup group;
	JButton button1,button2;
	JComboBox comBox1,comBox2,comBox3,comBox4;
	public cheDaiJiSuanQi(){
		init();
		setVisible(true);
		setBounds(100,100,670,600);
		setTitle("车贷计算器");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void init(){
		setLayout(null);
		jp1=new JPanel();
		jp1.setLayout(null);
		jp1.setBounds(0,0,460,320);
		//添加标签
		jl1=new JLabel("输入数据");
		jl1.setFont(new Font("幼圆",0,14));//设置字体，风格，字号
		jl1.setBounds(10,10,65,20);
		jp1.add(jl1);
		//添加标签
		jl2=new JLabel("所在地");
		jl2.setFont(new Font("幼圆",0,16));//设置字体，风格，字号
		jl2.setBounds(15,40,68,30);
		jp1.add(jl2);
		//添加下拉列表
		comBox1=new JComboBox();
		comBox1.addItem("安徽");
		comBox1.setFont(new Font("幼圆",0,16));//设置字体，风格，字号
		comBox1.setBounds(85,40,80,30);
		jp1.add(comBox1);
		//添加标签
		jl3=new JLabel("车价");
		jl3.setFont(new Font("幼圆",0,16));//设置字体，风格，字号
		jl3.setBounds(15, 80, 68, 30);
		jp1.add(jl3);
		//添加文本框
		text1=new JTextField();
		text1.setBounds(85,80,130,30);
		jp1.add(text1);
		//添加标签
		jl4=new JLabel("元");
		jl4.setFont(new Font("幼圆",0,16));
		jl4.setBounds(218,80,20,30);
		jp1.add(jl4);
		//添加标签
		jl5=new JLabel("搜车贷");
		jl5.setFont(new Font("幼圆",0,14));
		jl5.setForeground(new Color(110,127,144));//设置字体颜色
		jl5.setBounds(245,80,50,30);
		jp1.add(jl5);
		//添加标签
		jl6=new JLabel("座位数");
		jl6.setFont(new Font("幼圆",0,16));
		jl6.setBounds(15,120,68,30);
		jp1.add(jl6);
		//添加单选按钮
		group=new ButtonGroup();
		radio1=new JRadioButton("6座以下");
		radio1.setFont(new Font("幼圆",0,16));
		radio1.setBounds(85,120,90,30);
		radio1.setSelected(true);//设置默认选项
		radio2=new JRadioButton("6座及以上");
		radio2.setFont(new Font("幼圆",0,16));
		radio2.setBounds(180,120,100,30);
		group.add(radio1);
		group.add(radio2);
		jp1.add(radio1);
		jp1.add(radio2);
		//添加标签
		jl7=new JLabel("首付");
		jl7.setFont(new Font("幼圆",0,16));
		jl7.setBounds(15,160,68,30);
		jp1.add(jl7);
		//添加文本框
		text2=new JTextField("  3成");
		text2.setFont(new Font("幼圆",0,16));
		text2.setBackground(Color.lightGray);//设置背景颜色
		text2.setBounds(85,160,100,30);
		jp1.add(text2);
		//添加标签
		jl8=new JLabel("贷款期限");
		jl8.setFont(new Font("幼圆",0,16));
		jl8.setBounds(15,200,68,30);
		jp1.add(jl8);
		//添加文本框
		text3=new JTextField("  1年");
		text3.setFont(new Font("幼圆",0,16));
		text3.setBackground(Color.lightGray);//设置背景颜色
		text3.setBounds(85,200,100,30);
		jp1.add(text3);
		//添加标签
		jl9=new JLabel("年利率");
		jl9.setFont(new Font("幼圆",0,16));
		jl9.setBounds(15,240,68,30);
		jp1.add(jl9);
		//添加文本框
		text4=new JTextField("最新基准利率");
		text4.setFont(new Font("幼圆",0,16));
		text4.setBackground(Color.lightGray);//设置背景颜色
		text4.setBounds(85,240,160,30);
		jp1.add(text4);
		//添加文本框
		text5=new JTextField("  6.00%");
		text5.setBounds(248,240,80,30);
		text5.setFont(new Font("幼圆",0,16));
		jp1.add(text5);
		//添加标签
		jl10=new JLabel("银行利率比较");
		jl10.setFont(new Font("幼圆",0,16));
		jl10.setForeground(new Color(110,127,144));//设置字体颜色
		jl10.setBounds(335,240,110,30);
		jp1.add(jl10);
		//添加按钮
		button1=new JButton("计 算");
		button1.setFont(new Font("幼圆",0,16));
		button1.setBackground(new Color(83,133,224));//设置背景颜色
		button1.setBounds(85,280,100,35);
		jp1.add(button1);
		//添加按钮
		button2=new JButton("重 置");
		button2.setFont(new Font("幼圆",0,16));
		button2.setBounds(195,280,100,35);
		jp1.add(button2);
		add(jp1);
		
		jp2=new JPanel();
		jp2.setLayout(null);
		jp2.setBounds(0,320,670,260);
		//添加标签
		jl11=new JLabel("---------------------------------------------");
		jl11.setForeground(Color.blue);
		jl11.setBounds(10,10,180,5);
		jp2.add(jl11);
		jl12=new JLabel("---------------------------------------------");
		jl12.setForeground(Color.blue);
		jl12.setBounds(191,10,180,5);
		jp2.add(jl12);
		jl13=new JLabel("---------------------------------------------");
		jl13.setForeground(Color.blue);
		jl13.setBounds(372,10,180,5);
		jp2.add(jl13);
		jl14=new JLabel("---------------------");
		jl14.setForeground(Color.blue);
		jl14.setBounds(553,10,90,5);
		jp2.add(jl14);
		//添加下拉列表
		comBox2=new JComboBox();
		comBox2.addItem("基本税费         ￥0元");
		comBox2.setFont(new Font("幼圆",0,16));
		comBox2.setBackground(new Color(245,246,246));//设置背景颜色
		comBox2.setBounds(10,25,630,45);
		jp2.add(comBox2);
		//添加下拉列表
		comBox3=new JComboBox();
		comBox3.addItem("商业保险         ￥0元");
		comBox3.setFont(new Font("幼圆",0,16));
		comBox3.setBackground(new Color(245,246,246));//设置背景颜色
		comBox3.setBounds(10,80,630,45);
		jp2.add(comBox3);
		//添加下拉列表
		comBox4=new JComboBox();
		comBox4.addItem("购车总费用      ￥0元         首付   ￥0元");
		comBox4.setFont(new Font("幼圆",0,16));
		comBox4.setBackground(new Color(255,248,225));//设置背景颜色
		comBox4.setBounds(10,135,630,45);
		jp2.add(comBox4);
		//添加标签
		jl15=new JLabel("此结果仅供参考，实际应缴费以当地为准");
		jl15.setFont(new Font("幼圆",0,14));
		jl15.setForeground(Color.gray);//设置字体颜色
		jl15.setBounds(15,195,300,30);
		jp2.add(jl15);
		add(jp2);
		
		jp3=new JPanel();
		jp3.setLayout(null);
		jp3.setBounds(460,20,180,240);
		jp3.setBackground(Color.WHITE);//设置背景颜色
		jp3.setBorder(BorderFactory.createLineBorder(Color.black));//设置边框线
		//添加标签
		label1=new JLabel("买车知识");
		label1.setFont(new Font("宋体",0,20));
		label1.setBounds(15,15,80,30);
		jp3.add(label1);
		label2=new JLabel("* 省钱购车的4种方法");
		label2.setFont(new Font("幼圆",0,16));
		label2.setBounds(15,50,150,30);
		jp3.add(label2);
		label3=new JLabel("* 三种购车方式比较");
		label3.setFont(new Font("幼圆",0,16));
		label3.setBounds(15,85,150,30);
		jp3.add(label3);
		label4=new JLabel("* 购车贷款申请流程");
		label4.setFont(new Font("幼圆",0,16));
		label4.setBounds(15,120,150,30);
		jp3.add(label4);
		label5=new JLabel("* 贷款买车值不值？");
		label5.setFont(new Font("幼圆",0,16));
		label5.setBounds(15,155,150,30);
		jp3.add(label5);
		label6=new JLabel("* 贷款买车注意事项");
		label6.setFont(new Font("幼圆",0,16));
		label6.setBounds(15,190,150,30);
		jp3.add(label6);
		add(jp3);
	}
}
public class SY_8_2 {
	public static void main(String[] args) {
		new cheDaiJiSuanQi();
	}
}