package com.sf.minesweeper.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sf.minesweeper.panel.MineState;
import com.sf.minesweeper.tools.Tools;

public class Timers implements ActionListener{

	private int times;
	MineState mineState;
	public Timers(MineState mineState){
		this.mineState = mineState;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Tools.time++;
		if(Tools.time>999){
			Tools.time=999;
		}else{
			int g = Tools.time%10;
			int s = Tools.time/10%10;
			int b = Tools.time/100;
			
			mineState.getUsedtimeG().setIcon(Tools.timeCount[g]);
			mineState.getUsedtimeS().setIcon(Tools.timeCount[s]);
			mineState.getUsedtimeB().setIcon(Tools.timeCount[b]);
			
		}
	}
	
	

}
