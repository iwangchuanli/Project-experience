package com.sf.minesweeper.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.sf.minesweeper.frame.SartFrame;
import com.sf.minesweeper.tools.Tools;

public class MineState extends JPanel {

	private JLabel newGame;
	private JLabel usedtimeG, usedtimeS, usedtimeB;
	private JLabel totalBobmG, totalBobmS, totalBobmB;
	Box b;
	SartFrame sartFrame;
	public MineState(SartFrame sartFrame){
	this.sartFrame=sartFrame;
		
		this.setLayout(new BorderLayout());
		b = Box.createHorizontalBox();
		b.setBackground(Color.darkGray);
		Border borderOut = BorderFactory.createEmptyBorder(5, 10, 10, 10);
		Border borderIn = BorderFactory.createLoweredBevelBorder();
		Border borderGroup = BorderFactory.createCompoundBorder(borderOut,
				borderIn);
		b.setBorder(borderGroup);
		
		setBackground(Color.LIGHT_GRAY);
		
		newGame = new JLabel();
		newGame = new JLabel();
		newGame.setIcon(Tools.iiface0);
		newGame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				newGame.setIcon(Tools.iiface1);

			}

			public void mouseReleased(MouseEvent arg0) {
				 newGame.setIcon(Tools.iiface0);
				 MineState.this.sartFrame.restart();
			}
		});
		newGame.setIcon(Tools.iiface0);
		
		totalBobmG = new JLabel();
		totalBobmS = new JLabel();
		totalBobmB = new JLabel();
		
		setTotalMineG(Tools.totalMine);
		
		usedtimeS = new JLabel();
		usedtimeS.setIcon(Tools.timeCount[0]);
		
		usedtimeG = new JLabel();
		usedtimeG.setIcon(Tools.timeCount[0]);
		
		usedtimeB = new JLabel();
		usedtimeB.setIcon(Tools.timeCount[0]);
		
		//b.add(Box.createHorizontalStrut(10));
		b.add(totalBobmB);
		b.add(totalBobmS);
		b.add(totalBobmG);
		
		b.add(Box.createVerticalStrut(35));
		b.add(Box.createGlue());
		
		b.add(newGame);
		b.add(Box.createVerticalStrut(35));
		b.add(Box.createGlue());
		
		
		b.add(usedtimeB);
		b.add(usedtimeS);
		b.add(usedtimeG);
		
		
		this.add(b,BorderLayout.CENTER);
		
		
	}
	
	/**
	 * 根据当前旗子数计算剩余雷数
	 * 
	 * @param count
	 */
	public void setTotalMineG(int count){
		
		int g = count%10;
		int s = count/10%10;
		int b = count/100;
		if(count<0){
			totalBobmG.setIcon(Tools.timeCount[-g]);
			totalBobmS.setIcon(Tools.timeCount[-s]);
			totalBobmB.setIcon(Tools.timeCount[10]);
			
		}else{
			totalBobmG.setIcon(Tools.timeCount[g]);
			totalBobmS.setIcon(Tools.timeCount[s]);
			totalBobmB.setIcon(Tools.timeCount[b]);
		}
	}

	public JLabel getNewGame() {
		return newGame;
	}

	public JLabel getUsedtimeG() {
		return usedtimeG;
	}

	public JLabel getUsedtimeS() {
		return usedtimeS;
	}

	public JLabel getUsedtimeB() {
		return usedtimeB;
	}

	public JLabel getTotalBobmG() {
		return totalBobmG;
	}

	public JLabel getTotalBobmS() {
		return totalBobmS;
	}

	public JLabel getTotalBobmB() {
		return totalBobmB;
	}

	public Box getB() {
		return b;
	}
	
	
}
