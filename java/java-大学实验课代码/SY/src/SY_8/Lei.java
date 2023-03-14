package SY_8;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Lei extends JFrame{
	JMenuBar menubar;
	JMenu menu1,menu2;
	JMenuItem item1,item2,item3,item4;
	GridLayout grid;
	JPanel board;
	JButton button;
	
	public Lei(){
		init();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board=new JPanel();
		grid=new GridLayout(12, 12);
		board.setLayout(grid);
		Label[][] label=new Label[12][12];
		for(int i=0;i<12;i++){
			for(int j=0;j<12;j++){
				label[i][j]=new Label();
				if((i+j)%2==0)
					label[i][j].setBackground(Color.black);
					else 
						label[i][j].setBackground(Color.blue);
					board.add(label[i][j]);
			}
		}
		
		add(board,BorderLayout.CENTER);
		button=new JButton("时间");
		add(button,BorderLayout.SOUTH);
		button=new JButton("雷数");
		add(button,BorderLayout.SOUTH);
	}
	
	void init(){
		menubar=new JMenuBar();
		  menu1=new JMenu("游戏(G)");
		  menu1.setMnemonic('F');//设置标记符
		  item1=new JMenuItem("新建");
		  menu1.add(item1);
		  menu2=new JMenu("帮助(H)");
		  menu2.setMnemonic('E');//设置标记符
		  menubar.add(menu1);
		  menubar.add(menu2);
		  setJMenuBar(menubar);
	}

}
