package com.sf.minesweeper.dialog;
 
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sf.minesweeper.bean.Own;
import com.sf.minesweeper.frame.SartFrame;
import com.sf.minesweeper.tools.Tools;
/**扫雷排行榜的界面 弹出窗口*/
public class ShowWin extends JDialog {
	//ublic class Hero extends JDialog{
		private JLabel jlabel1;	
		private JLabel jlabel2;
		private JLabel jlabel3;
		private JLabel jlabel4;
		private JLabel jlabel5;
		private JLabel jlabel6;
		private JLabel time1;
		private JLabel time2;
		private JLabel time3;
		private JLabel name1;
		private JLabel name2;
		private JLabel name3;
		private JButton jbutton1;
		private JButton jbutton2;
		private SartFrame sartsrame;
		private JPanel jpanel;
		
		public ShowWin(SartFrame sartsrame){
			this.sartsrame=sartsrame;
			this.setTitle("扫雷排行榜");
			this.setVisible(true);
			this.setSize(220,210);
			this.setResizable(false);
			this.setLocationRelativeTo(sartsrame);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			init();
		}
		public JLabel getJLabel1() {
			return jlabel1;
		}
		public void setJLabel1(JLabel jlabel1) {
			this.jlabel1 = jlabel1;
		}
		public JLabel getJLabel2() {
			return jlabel2;
		}
		public void setJLabel2(JLabel jlabel2) {
			this.jlabel2 = jlabel2;
		}
		public JLabel getJLabel3() {
			return jlabel3;
		}
		public void setJLabel3(JLabel jlabel3) {
			this.jlabel3 = jlabel3;
		}
		public JLabel getJLabel4() {
			return jlabel4;
		}
		public void setJLabel4(JLabel jlabel4) {
			this.jlabel4 = jlabel4;
		}
		public JLabel getJLabel5() {
			return jlabel5;
		}
		public void setJLabel5(JLabel jlabel5) {
			this.jlabel5 = jlabel5;
		}
		public JLabel getJLabel6() {
			return jlabel6;
		}
		public void setJLabel6(JLabel jlabel6) {
			this.jlabel6 = jlabel6;
		}
		public JLabel getTime1() {
			return time1;
		}
		public void setTime1(JLabel time1) {
			this.time1 = time1;
		}
		public JLabel getTime2() {
			return time2;
		}
		public void setTime2(JLabel time2) {
			this.time2 = time2;
		}
		public JLabel getTime3() {
			return time3;
		}
		public void setTime3(JLabel time3) {
			this.time3 = time3;
		}
		public JLabel getName1() {
			return name1;
		}
		public void setName1(JLabel name1) {
			this.name1 = name1;
		}
		public JLabel getName2() {
			return name2;
		}
		public void setName2(JLabel name2) {
			this.name2 = name2;
		}
		public JLabel getName3() {
			return name3;
		}
		public void setName3(JLabel name3) {
			this.name3 = name3;
		}
		public void init(){
			HeroListener heroListener = new HeroListener();
			jlabel1 = new JLabel(" 级别");
			jlabel2 = new JLabel(" 成绩");
			jlabel3 = new JLabel(" 玩家");
			jlabel4 = new JLabel(" 初级：");
			jlabel5 = new JLabel(" 中级：");
			jlabel6 = new JLabel(" 高级：");
			time1 = new JLabel(""+Tools.time1);
			time2 = new JLabel(""+Tools.time2);
			time3 = new JLabel(""+Tools.time3);
			name1 = new JLabel(" "+Tools.name1);
			name2 = new JLabel(" "+Tools.name2);
			name3 = new JLabel(" "+Tools.name3);
			jbutton1=new JButton("确定");
			jbutton1.addActionListener(heroListener);
			jbutton2=new JButton("重新设置");
			jbutton2.addActionListener(heroListener);
			jpanel=new JPanel();
			Box box1 = Box.createVerticalBox();
			box1.add(jlabel1);
			box1.add(Box.createVerticalStrut(10));
			box1.add(jlabel4);
			box1.add(Box.createVerticalStrut(10));
			box1.add(jlabel5);
			box1.add(Box.createVerticalStrut(10));
			box1.add(jlabel6);
			Box box2 = Box.createVerticalBox();
			box2.add(jlabel2);
			box2.add(Box.createVerticalStrut(10));
			box2.add(time1);
			box2.add(Box.createVerticalStrut(10));
			box2.add(time2);
			box2.add(Box.createVerticalStrut(10));
			box2.add(time3);
			Box box3 = Box.createVerticalBox();
			box3.add(jlabel3);
			box3.add(Box.createVerticalStrut(10));
			box3.add(name1);
			box3.add(Box.createVerticalStrut(10));
			box3.add(name2);
			box3.add(Box.createVerticalStrut(10));
			box3.add(name3);
			Box box4 = Box.createHorizontalBox();
			box4.add(jbutton1);
			box4.add(Box.createHorizontalStrut(20));
			box4.add(jbutton2);
			Box box5 = Box.createHorizontalBox();
			box5.add(box1);
			box5.add(box2);
			box5.add(box3);
			Box box6 = Box.createVerticalBox();
			box6.add(Box.createVerticalStrut(20));
			box6.add(box5);
			box6.add(Box.createVerticalStrut(20));
			box6.add(box4);
			jpanel.add(box6);
			this.add(jpanel);
		}
		class HeroListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				String command = e.getActionCommand();
				if(command.equals("确定")){
					dispose();
				}
				else {
					Tools.time1 = 999;
					Tools.time2 = 999;
					Tools.time3 = 999;
					Tools.name1 = " 匿名";
					Tools.name2 = " 匿名";
					Tools.name3 = " 匿名";			
					time1.setText(""+Tools.time1);
					name1.setText(Tools.name1);				
					time2.setText(""+Tools.time2);
					name2.setText(Tools.name2);			
					time3.setText(""+Tools.time3);
					name3.setText(Tools.name3);
					//dispose();
				}
			}
		}
	}

	

