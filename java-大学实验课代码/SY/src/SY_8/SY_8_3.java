package SY_8;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

/*
 * BMI计算
 */
class BMI extends JFrame{
	JPanel jp;
	JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10,jl11,jl12,jl13;
	JRadioButton radio1,radio2;
	ButtonGroup group;
	JTextField text1,text2;
	JComboBox comBox;
	JCheckBox checkBox;
	JButton button;
	public BMI(){
		init();
		setVisible(true);//设置窗口可见
		setBounds(100,100,660,280);//设置窗口的位置和大小
		setTitle("BMI计算器");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭当前窗口所在的程序
	}
	void init(){
		setLayout(null);//空布局
		jp=new JPanel();//初始化面板
		jp.setLayout(null);
		jp.setBounds(0,0,640,235);
		//添加标签
		jl1=new JLabel("免费计算你的身体质量指数(BMI)");
		jl1.setFont(new Font("幼圆",0,15));//设置字体，风格，字号
		jl1.setBounds(10,10,220,20);
		add(jl1);
		//添加标签
		jl2=new JLabel("质量单位:");
		jl2.setFont(new Font("幼圆",0,13));//设置字体，风格，字号
		jl2.setBounds(10,40,60,20);
		add(jl2);
		//添加单选按钮
		group=new ButtonGroup();
		radio1=new JRadioButton("公制");
		radio1.setFont(new Font("幼圆",0,13));//设置字体，风格，字号
		radio1.setBounds(85, 40, 55, 20);
		radio1.setSelected(true);//设置默认选项
		radio2=new JRadioButton("英制");
		radio2.setFont(new Font("幼圆",0,13));//设置字体，风格，字号
		radio2.setBounds(145,40,55,20);
		group.add(radio1);
		group.add(radio2);
		jp.add(radio1);
		jp.add(radio2);
		//添加标签
		jl3=new JLabel("我的身高:");
		jl3.setFont(new Font("幼圆",0,13));
		jl3.setBounds(10,72,60,20);
		add(jl3);
		//添加文本框
		text1=new JTextField();
		text1.setFont(new Font("幼圆",0,13));
		text1.setBounds(85,70,120,25);
		jp.add(text1);
		//添加标签
		jl4=new JLabel("单位:厘米 cm");
		jl4.setFont(new Font("幼圆",0,13));
		jl4.setBounds(210,72,80,20);
		add(jl4);
		//添加标签
		jl5=new JLabel("我的体重:");
		jl5.setFont(new Font("幼圆",0,13));
		jl5.setBounds(10,102,60,20);
		add(jl5);
		//添加文本框
		text2=new JTextField();
		text2.setFont(new Font("幼圆",0,13));
		text2.setBounds(85,100,120,25);
		jp.add(text2);
		//添加标签
		jl6=new JLabel("单位:千克 kg");
		jl6.setFont(new Font("幼圆",0,13));
		jl6.setBounds(210,102,80,20);
		add(jl6);
		//添加标签
		jl7=new JLabel("BMI标准:");
		jl7.setFont(new Font("幼圆",0,13));
		jl7.setBounds(10,132,60,20);
		add(jl7);
		//添加下拉列表
		comBox=new JComboBox();
		comBox.addItem(" 中国标准");
		comBox.setFont(new Font("幼圆",0,13));
		comBox.setBounds(85,130,120,25);
		jp.add(comBox);
		//添加选择框
		checkBox=new JCheckBox("自动保存BMI历史记录");
		checkBox.setFont(new Font("宋体",0,11));
		checkBox.setBounds(80,160,140,20);
		checkBox.setSelected(true);//设置默认选中
		jp.add(checkBox);
		//添加按钮
		button=new JButton("计算BMI");
		button.setFont(new Font("幼圆",0,13));
		button.setBounds(83,185,122,25);
		jp.add(button);
		//添加标签
		jl8=new JLabel("BMI中国标准");
		jl8.setFont(new Font("幼圆",0,13));
		jl8.setBounds(390,10,85,20);
		jp.add(jl8);
		jl9=new JLabel("    分类                BMI范围");
		jl9.setFont(new Font("幼圆",0,13));
		jl9.setOpaque(true);//设置背景色不透明
		jl9.setBackground(new Color(141,246,248));//设置背景色
		jl9.setBounds(390,45,220,20);
		jp.add(jl9);
		jl10=new JLabel("    偏重                 <=18.4");
		jl10.setFont(new Font("幼圆",0,13));
		jl10.setOpaque(true);//设置背景色不透明
		jl10.setBackground(new Color(204,204,204));//设置背景色
		jl10.setBounds(390,65,220,20);
		jp.add(jl10);
		jl11=new JLabel("    正常              18.5 - 23.9");
		jl11.setFont(new Font("幼圆",0,13));
		jl11.setOpaque(true);//设置背景色不透明
		jl11.setBackground(new Color(102,204,0));//设置背景色
		jl11.setBounds(390,85,220,20);
		jp.add(jl11);
		jl12=new JLabel("    过重              24.0 - 27.9");
		jl12.setFont(new Font("幼圆",0,13));
		jl12.setOpaque(true);//设置背景色不透明
		jl12.setBackground(new Color(255,255,0));//设置背景色
		jl12.setBounds(390,105,220,20);
		jp.add(jl12);
		jl13=new JLabel("    肥胖                 >=28.0");
		jl13.setFont(new Font("幼圆",0,13));
		jl13.setOpaque(true);//设置背景色不透明
		jl13.setBackground(new Color(255,153,0));//设置背景色
		jl13.setBounds(390,125,220,20);
		jp.add(jl13);
		add(jp);//添加面板
	}
}
public class SY_8_3 {
	public static void main(String[] args) {
		new BMI();
	}
}

