package com.sf.minesweeper.dialog;
 
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sf.minesweeper.bean.Own;
import com.sf.minesweeper.frame.SartFrame;
import com.sf.minesweeper.tools.Tools;

public class Win extends JDialog {
	SartFrame sartFrame;
	private JTextField text;
	TreeSet<Own> LOWER = new TreeSet<Own>();
	TreeSet<Own> MIDDLE = new TreeSet<Own>();
	TreeSet<Own> HEIGHT = new TreeSet<Own>();
	
	public Win(SartFrame sartFrame){
		this.sartFrame = sartFrame;
		this.setTitle("提示框");
		this.setLocationRelativeTo(null);
		this.setSize(200, 150);
		this.init();
		this.setVisible(true);
	}

	private void init() {
		// TODO Auto-generated method stub
		/**
		 * 存放记入
		 * 
		 */
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,1));
		JLabel label = new JLabel("请留下你的大名");
		
		panel.add(label);
		
		text = new JTextField();
		panel.add(text);
		//times = sartFrame.getTimer().getTimes();
		JLabel labTime = new JLabel("你所使用的时间："+Tools.time);
		panel.add(labTime);
		
		JButton butys = new JButton("保存");
		butys.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				if(Tools.currentLevel.equals("初级")){
					if(Tools.time1>=Tools.time){
						Tools.time1 = Tools.time;
						Tools.name1=text.getText();
					}
				}
				if(Tools.currentLevel.equals("中级")){				
					if(Tools.time2>=Tools.time){
						Tools.time2= Tools.time;
						Tools.name2=text.getText();
					}
				}
				if(Tools.currentLevel.equals("高级")){				
					if(Tools.time3>=Tools.time){
						Tools.time3 = Tools.time;
						Tools.name3=text.getText();
					}
				}
				
				Win.this.dispose();
				
			}
		});
		panel.add(butys);
		this.add(panel);
		
	}
	public JTextField getText() {
		return text;
	}

}


