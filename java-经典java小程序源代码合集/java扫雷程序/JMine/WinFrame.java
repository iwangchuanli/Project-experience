import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class WinFrame extends JFrame implements MouseListener{
	private JPanel winPane;
	private JLabel msg1;
	private JLabel msg2;
	private JLabel msg3;
	private JButton easy;
	private JButton middle;
	private JButton hard;
	private int level;
	private boolean isOk;

	public WinFrame(String strName) {
		super(strName);
		setSize(150, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		level = 1;
		isOk = false;
		
		winPane = new JPanel();
		msg1 = new JLabel("  Congratulation!  ");
		msg2 = new JLabel("   You win.   ");
		msg3 = new JLabel("  Click to restart!  ");
		easy = new JButton("Easy");
		easy.addMouseListener(this);
		middle = new JButton("Middle");
		middle.addMouseListener(this);
		hard = new JButton("Hard");
		hard.addMouseListener(this);
		winPane.add(msg1);
		winPane.add(msg2);
		winPane.add(msg3);
		winPane.add(easy);
		winPane.add(middle);
		winPane.add(hard);

		setContentPane(winPane);
		setLocation(250,220);
		this.setVisible(true);
	}
	
	public int getLevel() {
		return(level);
	}
	
	public int getMineNum() {	
		return(level*12);
	}
	
	public boolean getWinOk() {
		return(isOk);
	}

	// the event handle to deal with the mouse click
	public void mouseClicked(MouseEvent e) {
		//System.out.println("Jerry Click");
		if(e.getSource() == easy) {
			level = 1;
		}
		if(e.getSource() == middle) {
			level = 2;
		}
		if(e.getSource() == hard) {
			level = 3;
		}
		isOk = true;	
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
		WinFrame win = new WinFrame("Win");
		win.show();
	}
}
