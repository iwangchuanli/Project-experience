package SY_9;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class QQlogin extends JFrame{
	JTextField text;
	JButton button;
	JRadioButton radio1,radio2;
	ButtonGroup group;
	JComboBox comBox;
	JCheckBox checkBox1,checkBox2;
	ActionListener listener;
	
	public QQlogin(){
		init();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listener=new ReaderQQListen();
		
	}
	
	
	void init(){
		setLayout(new FlowLayout());
		add(new JLabel("昵称："));
		text=new JTextField(15);	
		text.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str=e.getActionCommand();
                if(str.length()==0){
                     JOptionPane.showConfirmDialog(null, "昵称不能为空！");
                }
            }
        });
		add(text);
	
		
		add(new JLabel("密码："));
		text=new JTextField(15);
		add(text);
		add(new JLabel("确认密码："));
		javax.swing.JTextField text1 = new JTextField(15);
		text1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str=e.getActionCommand();
                if(str==text){
                     JOptionPane.showConfirmDialog(null, "密码正确！");
                }
                else
                {
                	JOptionPane.showConfirmDialog(null, "密码不正确！");
                }
            }
        });
		add(text1);
		
		add(new JLabel("性别："));
		group=new ButtonGroup();
		radio1=new JRadioButton("     男       ");
		radio2=new JRadioButton("     女       ");
		group.add(radio1);
		group.add(radio2);
		add(radio1);
		add(radio2);
		
		add(new JLabel("生日："));
		comBox=new JComboBox();
		comBox.addItem("公历");
		comBox.addItem("农历");
		add(comBox);
		comBox=new JComboBox();
		comBox.addItem("年");
		add(comBox);
		comBox=new JComboBox();
		comBox.addItem("月");
		add(comBox);
		comBox=new JComboBox();
		comBox.addItem("日");
		add(comBox);
		
		add(new JLabel("地区："));
		comBox=new JComboBox();
		comBox.addItem("中国");
		add(comBox);
		comBox=new JComboBox();
		comBox.addItem("安徽");
		add(comBox);
		comBox=new JComboBox();
		comBox.addItem("黄山");
		add(comBox);
		
		add(new JLabel("手机号码："));
		text=new JTextField(10);
		add(text);
		
		add(new JLabel("可通过手机号码找回密码"));
		add(new JLabel("中国大陆地区以外手机号码"));
		button=new JButton("点击这里找回");
		add(button);
		
		button=new JButton("立即注册");
		add(button);
		
		checkBox1=new JCheckBox("同时开通QQ空间");
		checkBox2=new JCheckBox("我已阅读并同意相关服务条款及隐私政策");
		add(checkBox1);
		add(checkBox2);
	}


	private void JTextField(JLabel jLabel) {
		// TODO Auto-generated method stub
		
	}
}
