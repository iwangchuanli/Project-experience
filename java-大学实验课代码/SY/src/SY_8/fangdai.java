package SY_8;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.naming.InitialContext;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class fangdai extends JFrame{
	JTextField text;
	JButton button;
	JRadioButton radio1,radio2;
	JComboBox comBox;
	ButtonGroup group;
	
	public fangdai(){
		init();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void init(){
		setLayout(new FlowLayout());
		add(new JLabel("商业贷款金额："));
		text=new JTextField(12);
		add(text);
		add(new JLabel("万元"));
		
		add(new JLabel("贷款期限："));
		comBox=new JComboBox();
		comBox.addItem("20年");
		comBox.addItem("10年");
		comBox.addItem("5年");
		add(comBox);
		
		add(new JLabel("商业贷款利率："));
		comBox=new JComboBox();
		comBox.addItem("最新基准利率");
		comBox.addItem("之前基准利率");
		add(comBox);
		comBox=new JComboBox();
		comBox.addItem("无折扣");
		comBox.addItem("有折扣");
		add(comBox);
		text=new JTextField(5);
		add(text);
		add(new JLabel("%"));
		add(new JLabel("还款方式："));
		group=new ButtonGroup();
		radio1=new JRadioButton("等额本金");
		radio2=new JRadioButton("等额本息");
		group.add(radio1);
		group.add(radio2);
		add(radio1);
		add(radio2);
		button=new JButton("立即计算");
		add(button);
	}
}
