package com.sf.minesweeper.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sf.minesweeper.bean.Own;
import com.sf.minesweeper.frame.SartFrame;
import com.sf.minesweeper.tools.Tools;

public class About extends JDialog {
	private JLabel labx,laby,labmine;
	private JTextField jTextField1,jTextField2,jTextField3;
	private JButton butyes,butno;
	SartFrame sartFrame;
	public About(SartFrame sartFrame) {
		this.sartFrame = sartFrame;
		this.setTitle("");
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setSize(new Dimension(200,200));
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.init();
		this.setVisible(true);
	}

	/**自定义雷区*/
	private void init() {
		// TODO Auto-generated method stub
		JPanel jPanel = new JPanel();
		 labx = new JLabel("");
		 jTextField1=new JTextField(12);
		 jTextField2=new JTextField(12);
		 jTextField3=new JTextField(12);
		 
		 JLabel jLabelTotalx=new JLabel("行数:");
		 JLabel jLabelTotaly=new JLabel("列数:");
		 JLabel jLabelTotalMine=new JLabel("雷数:");

		jPanel.add(jLabelTotalx);
		jPanel.add(jTextField1);
		
		jPanel.add(jLabelTotaly);
		jPanel.add(jTextField2);
		
		jPanel.add(jLabelTotalMine);
		jPanel.add(jTextField3);

		
		butyes = new JButton("确定");
		butno = new JButton("取消");
		jPanel.add(butyes);
		jPanel.add(butno);
		
		butyes.addActionListener(new ActionListener() {
        
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					Tools.totalx=Integer.parseInt(jTextField1.getText());
					Tools.totaly=Integer.parseInt(jTextField2.getText());
					Tools.totalMine=Integer.parseInt(jTextField3.getText());
					if(Tools.totalx>9&&Tools.totalx<30){
						if(Tools.totaly>9&&Tools.totaly<30){
							if(Tools.totalMine>=10&&Tools.totalMine<Tools.totalx*Tools.totaly*4/5){
								sartFrame.restart();
								About.this.dispose();
							}else{
								JOptionPane.showMessageDialog(null, "布雷有误请重试!!!\n布雷数应少于总格子数!!!", "提示消息", JOptionPane.NO_OPTION);	
							}
						}else {
							JOptionPane.showMessageDialog(null, "您输入的列数有误\n请重试！！！", "提示消息", JOptionPane.NO_OPTION);		
						}				
					}else {
						JOptionPane.showMessageDialog(null, "您输入的行数有误\n请重试！！！", "提示消息", JOptionPane.NO_OPTION);	

					}		
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "必须是数字类型", "提示消息", JOptionPane.NO_OPTION);
					return;
				}
				
			}
		});
		
		butno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About.this.dispose();
			}
		});
		this.add(jPanel);
		
		
		
	}

}
