import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class AboutFrame extends JFrame implements MouseListener{
	private JPanel aboutPane;
	private JLabel msg;
	private JLabel msg1;
	private JLabel msg2;
	private JButton exit;

	public AboutFrame(String strName) {
		super(strName);
		setSize(180, 170);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		aboutPane = new JPanel();
		msg = new JLabel("JMine written by Jerry Shen.");
		msg1 = new JLabel("         Enjoy!           ");
		msg2 = new JLabel("Vision 1.0                ");
		exit = new JButton("Exit");
		exit.addMouseListener(this);
		aboutPane.add(msg);
		aboutPane.add(msg1);
		aboutPane.add(msg2);
		aboutPane.add(exit);

		setContentPane(aboutPane);
		setLocation(250,220);
	}


	// the event handle to deal with the mouse click
	public void mouseClicked(MouseEvent e) {
		this.setVisible(false);	
	}

	public void mousePressed(MouseEvent e) {
		//System.out.println("Jerry Press");

	}
	public void mouseReleased(MouseEvent e) {
		//System.out.println("Jerry Release");
	}
	public void mouseExited(MouseEvent e) {
		//System.out.println("Jerry Exited");

	}
	public void mouseEntered(MouseEvent e) {
		//System.out.println("Jerry Entered");

	}

	public static void main(String[] args) {
		AboutFrame about = new AboutFrame("Win");
		about.show();
	}
}
