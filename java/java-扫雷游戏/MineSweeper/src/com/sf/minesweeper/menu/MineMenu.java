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
		
		JMenu fileMenu = new JMenu("��Ϸ(G)");
		fileMenu.setMnemonic('G');
		
		JMenuItem newGameItem = new JMenuItem("����(N)");
		newGameItem.setMnemonic('N');
		fileMenu.add(newGameItem);
		newGameItem.addActionListener(listener);
		
		JMenuItem lowItem = new JMenuItem("����(B)");
		lowItem.setMnemonic('B');
		fileMenu.add(lowItem);
		lowItem.addActionListener(listener);
		
		JMenuItem midItem = new JMenuItem("�м�(I)");
		midItem.setMnemonic('I');
		fileMenu.add(midItem);
		midItem.addActionListener(listener);
		
		JMenuItem highItem = new JMenuItem("�߼�(E)");
		highItem.setMnemonic('E');
		fileMenu.add(highItem);
		highItem.addActionListener(listener);
		
		JMenuItem orderItem = new JMenuItem("�Զ���(C)");
		orderItem.setMnemonic('C');
		fileMenu.add(orderItem);
		orderItem.addActionListener(listener);

		JMenuItem heroMenu = new JMenuItem("ɨ��Ӣ�۰�(T)");
		//JMenu heroMenu = new JMenu("ɨ��Ӣ�۰�(T)");
		heroMenu.setMnemonic('T');
		
		heroMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("sfgd");
				new ShowWin(getSartFrame());
			}
			 
	        });
		fileMenu.add(heroMenu);

		
		JMenuItem exitItem = new JMenuItem("�˳�(X)");        
		exitItem.addActionListener(new ActionListener() {    //��һ��ϵͳ�˳��Ĵ������
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		fileMenu.add(exitItem);
		
		JMenu aboutMenu = new JMenu("����(H)");
		aboutMenu.setMnemonic('H');
		

		JMenu aboutItem = new JMenu("��Ȩ��Ϣ(A)");
		aboutMenu.add(aboutItem);
		JMenuItem about2 = new JMenuItem("�汾��Ϊv0.01����Ȩ��ʮ������");
		aboutItem.add(about2);
		
		JMenuItem weiGuaItem = new JMenuItem("���(W)");
		aboutMenu.add(weiGuaItem);

		weiGuaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (MineMenu.this.sartFrame.isStart()) {
					// �ж�ÿһ���׿��Ƿ�����
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
