package charpter9;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

public class WindowColor extends JFrame implements ActionListener{
	JButton button;
	public WindowColor() {
		// TODO Auto-generated constructor stub
		button=new JButton("打开颜色对话框");
		button.addActionListener(this);
		setLayout(new FlowLayout());
		add(button);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Color newColor = JColorChooser.showDialog(this, "调色板", getContentPane().getBackground());
		if (newColor!=null) {
			getContentPane().setBackground(newColor);
		}
		
	}
	
}
