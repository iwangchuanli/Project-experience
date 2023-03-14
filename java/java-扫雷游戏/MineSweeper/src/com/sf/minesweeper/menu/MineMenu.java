package com.sf.minesweeper.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.sf.minesweeper.bean.MineLabel;
import com.sf.minesweeper.dialog.ShowWin;
import com.sf.minesweeper.frame.SartFrame;
import com.sf.minesweeper.listener.MineMenuListener;
import com.sf.minesweeper.tools.Tools;

public class MineMenu extends JMenuBar {
	/**
	 * 
	 */

	private static final long serialVersionUID = -5997735339754706915L;
	//SartFrame sartFrame;
	public MineMenu(SartFrame sartFrame){
		this.sartFrame=sartFrame;
		
		MineMenuListener listener=new MineMenuListener(sartFrame);
		
		JMenu fileMenu = new JMenu("游戏(G)");
		fileMenu.setMnemonic('G');
		
		JMenuItem newGameItem = new JMenuItem("开局(N)");
		newGameItem.setMnemonic('N');
		fileMenu.add(newGameItem);
		newGameItem.addActionListener(listener);
		
		JMenuItem lowItem = new JMenuItem("初级(B)");
		lowItem.setMnemonic('B');
		fileMenu.add(lowItem);
		lowItem.addActionListener(listener);
		
		JMenuItem midItem = new JMenuItem("中级(I)");
		midItem.setMnemonic('I');
		fileMenu.add(midItem);
		midItem.addActionListener(listener);
		
		JMenuItem highItem = new JMenuItem("高级(E)");
		highItem.setMnemonic('E');
		fileMenu.add(highItem);
		highItem.addActionListener(listener);
		
		JMenuItem orderItem = new JMenuItem("自定义(C)");
		orderItem.setMnemonic('C');
		fileMenu.add(orderItem);
		orderItem.addActionListener(listener);

		JMenuItem heroMenu = new JMenuItem("扫雷英雄榜(T)");
		//JMenu heroMenu = new JMenu("扫雷英雄榜(T)");
		heroMenu.setMnemonic('T');
		
		heroMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("sfgd");
				new ShowWin(getSartFrame());
			}
			 
	        });
		fileMenu.add(heroMenu);

		
		JMenuItem exitItem = new JMenuItem("退出(X)");        
		exitItem.addActionListener(new ActionListener() {    //加一个系统退出的处理监听
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		fileMenu.add(exitItem);
		
		JMenu aboutMenu = new JMenu("帮助(H)");
		aboutMenu.setMnemonic('H');
		

		JMenu aboutItem = new JMenu("版权信息(A)");
		aboutMenu.add(aboutItem);
		JMenuItem about2 = new JMenuItem("版本号为v0.01，版权第十组所有");
		aboutItem.add(about2);
		
		JMenuItem weiGuaItem = new JMenuItem("外挂(W)");
		aboutMenu.add(weiGuaItem);

		weiGuaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (MineMenu.this.sartFrame.isStart()) {
					// 判断每一个雷块是否是雷
					for (MineLabel[] mineLabel : MineMenu.this.sartFrame
							.getMineField().getMineLabel()) {
						for (MineLabel m : mineLabel) {
							if (m.isMine()) {
								m.setIcon(Tools.iihole);
							}
						}
					}
				}
			}
		});
	
		
		aboutMenu.add(weiGuaItem);
		
		this.add(fileMenu);
		this.add(aboutMenu);
		
		
	}

	private SartFrame sartFrame;

	public SartFrame getSartFrame(){
		return sartFrame;
	}

}
