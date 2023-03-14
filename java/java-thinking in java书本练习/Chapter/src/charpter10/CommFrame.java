package charpter10;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CommFrame extends JFrame implements ActionListener{
	File file=null;
	JMenuBar bar;
	JMenu fileMenu;
	JMenuItem inputMenuItem,showMenuItem;
	JTextArea show;
	InputArea inputMessage;
	CardLayout card=null;
	JPanel pCenter;
	
	public CommFrame() {
		// TODO Auto-generated constructor stub
		file=new File("");
		inputMenuItem=new JMenuItem("");
		showMenuItem=new JMenuItem("");
		bar=new JMenuBar();
		fileMenu=new JMenu("");
		fileMenu.add(inputMenuItem);
		fileMenu.add(showMenuItem);
		bar.add(fileMenu);
		setJMenuBar(bar);
		inputMenuItem.addActionListener(this);
		showMenuItem.addActionListener(this);
		inputMessage=new InputArea(file);
		show=new JTextArea(12,20);
		card=new CardLayout();
		pCenter=new JPanel();
		pCenter.setLayout(card);
		pCenter.add("inputMenuItem", inputMessage);
		pCenter.add("showMenuItem", show);
		add(pCenter,BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(100, 50, 420, 380);
		validate();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==inputMenuItem) {
			card.show(pCenter, "inputMenuItem");
		}
		else if (e.getSource()==showMenuItem) {
			int number
		}
		
	}
	
	
}
