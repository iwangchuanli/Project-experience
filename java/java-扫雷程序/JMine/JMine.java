/**
 * This program is written by Jerry Shen(Shen Ji Feng)
 * use the technology of SWING GUI and the OO design
 *
 * @author Jerry Shen(jerry.shen@cognizant.com)
 * Distributed under the licience of GPLv3
 * all rights reserved.
 */ 
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class JMine extends JFrame implements MouseListener, ActionListener {
	private JMineArth mine;
	private JMineButton [][] mineButton;
    private GridBagConstraints constraints;
   	private JPanel pane;
    private GridBagLayout gridbag;
	private boolean gameStarted;
	private JCounter mineCounter;
	private JCounter timeCounter;

	private TimeCounterThread timerThread;
	public int numMine;
	public int numFlaged;

	private JMenuBar mb;
	private JMenu mGame;
	private JMenuItem miEasy;
	private JMenuItem miMiddle;
	private JMenuItem miHard;
	private JMenuItem miExit;
	private JMenu mHelp;
	private JMenuItem miAbout;
	private JPanel controlPane;
	private JButton bTest;
	
	private AboutFrame about;



	private ImageIcon [] mineNumIcon = { new ImageIcon("0.gif"), new ImageIcon("1.gif"),
					     new ImageIcon("2.gif"), new ImageIcon("3.gif"),
					     new ImageIcon("4.gif"), new ImageIcon("5.gif"),
					     new ImageIcon("6.gif"), new ImageIcon("7.gif"),
					     new ImageIcon("8.gif"), new ImageIcon("9.gif"),
					};
	private ImageIcon[] mineStatus = { new ImageIcon("0.gif"), new ImageIcon("flag.gif"), 
						new ImageIcon("question.gif") };
	private ImageIcon[] mineBombStatus = { new ImageIcon("0.gif"), new ImageIcon("mine.gif"), 
						new ImageIcon("wrongmine.gif"), new ImageIcon("bomb.gif") };
	private ImageIcon[] faceIcon = { new ImageIcon("smile.gif"), new ImageIcon("Ooo.gif")};
	
	// You lose
	private void bomb(int row, int col){
		//System.out.println("Bomb!");
		timerThread.stop();
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				mineButton[i][j].setIcon(mineBombStatus[0]);
				int toShow;
        		toShow = mine.mine[i][j]!=9? 0: 1;
				mineButton[i][j].setClickFlag(true);
 				if ( toShow == 1 && (i!=row || j!= col)) {
					mineButton[i][j].setIcon(mineBombStatus[toShow]);
					mineButton[i][j].setClickFlag(true);
				}
				else if (toShow == 1 && (i == row && j== col)) {
					mineButton[i][j].setIcon(mineBombStatus[3]);
					mineButton[i][j].setClickFlag(true);
				}
				else if (toShow == 0 && mineButton[i][j].getFlag() != 1) {
					mineButton[i][j].setEnabled(false); 
				}
				else if ( toShow == 0 && mineButton[i][j].getFlag() == 1) {
					mineButton[i][j].setIcon(mineBombStatus[2]);
					mineButton[i][j].setClickFlag(true);
				}
			}
		}
	}
	
	// check if you win() {
	private boolean isWin() {
		int minesCount=0;
		for (int i = 0; i < 10; i++) {
			for (int j=0; j <10; j++) {
				if(mine.mine[i][j]==9 && mineButton[i][j].getFlag() != 1) {
					return(false);
				}
				if(mine.mine[i][j]!=9 && mineButton[i][j].getFlag() == 1) {
					return(false);
				}
				if(mine.mine[i][j]!=9 && mineButton[i][j].getClickFlag() == false) {
					return(false);
				}				
			}
		}
		return(true);
	}
	
	// You Win
	private void win(){
		timerThread.stop();
		RestartRunner r = new RestartRunner();
		r.setMine(this);
		r.setTimer(timerThread);
		Thread t = new Thread(r);
		t.start();
		
	}
	
	// Constructor of the game 
	public JMine() {
		super("JMine Game");
		setSize(250, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Insets space = new Insets(0,0,0,0);
		
		// Game vars
		gameStarted = false;
		numMine = 12;
		numFlaged = 0;
		
		ImageIcon myIcon = new ImageIcon("0.gif");
		gridbag = new GridBagLayout();
		constraints = new GridBagConstraints();
		pane = new JPanel();
		pane.setLayout(gridbag);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;


		// Begin Menu Set
		mb = new JMenuBar();
		mGame = new JMenu("Game");
		miEasy = new JMenuItem("Easy");
		miEasy.addActionListener(this);
		miMiddle = new JMenuItem("Middle");
		miMiddle.addActionListener(this);
		miHard = new JMenuItem("Hard");
		miHard.addActionListener(this);
		miExit = new JMenuItem("Exit");
		miExit.addActionListener(this);
		mGame.add(miEasy);
		mGame.add(miMiddle);
		mGame.add(miHard);
		mGame.addSeparator();
		mGame.add(miExit);
		mb.add(mGame);

		mHelp = new JMenu("Help");
		miAbout = new JMenuItem("About...");
		mHelp.add(miAbout);
		miAbout.addActionListener(this);
		mb.add(mHelp);
		this.setJMenuBar(mb);
		// end of Menu Set

		// Control Panel
		controlPane = new JPanel();
		bTest = new JButton(faceIcon[0]);
		bTest.setSize(26, 27);
		bTest.setMargin(space);
		bTest.addMouseListener(this);
		bTest.setPressedIcon(faceIcon[1]);
	
		mineCounter = new JCounter(numMine);
		timeCounter = new JCounter();
		controlPane.add(mineCounter);
		controlPane.add(bTest);
		controlPane.add(timeCounter);
		buildConstraints(constraints, 0, 0, 10, 2, 100, 100);
		gridbag.setConstraints(controlPane, constraints);
		pane.add(controlPane);
			
		// Bottons
		mineButton = new JMineButton[10][10];		
		for (int i=0; i <10; i++) {
			for (int j=0; j < 10; j++) {
				mineButton[i][j] = new JMineButton(i,j, myIcon);
				mineButton[i][j].addMouseListener(this);
				mineButton[i][j].setMargin(space);
				buildConstraints(constraints, j, i+3, 1, 1, 100, 100);
				gridbag.setConstraints(mineButton[i][j], constraints);
				pane.add(mineButton[i][j]);
			}
		}

		// Content Pane
		setContentPane(pane);
		setLocation(200, 150);
		setVisible(true);
		
		// About Frame
		about = new AboutFrame("JMine About");
	}
	
	// Set the GUI objects positions
	void buildConstraints(GridBagConstraints gbc, int gx, int gy, int gw, int gh, int wx, int wy) {
		gbc.gridx = gx;
		gbc.gridy = gy;
		gbc.gridwidth = gw;
		gbc.gridheight = gh;
		gbc.weightx = wx;
		gbc.weighty = wy;
	}
	
	// the methods to check if there were mines, to be nested
	void checkMine(int row, int col) {
		int i,j;
		i=row<0?0:row;
		i=i>9?9:i;
		j=col<0?0:col;
		j=j>9?9:j;
		//System.out.println("Check Mine row:"+i + ",col:" +j);
		if (mine.mine[i][j] == 9) {
			bomb(i,j);
		}
		else if (mine.mine[i][j] == 0 && mineButton[i][j].getClickFlag() == false) {
			mineButton[i][j].setClickFlag(true);
            		showLabel(i,j);
			for (int ii= i -1; ii <= i+1; ii++)
				for (int jj = j-1; jj <= j+1; jj++)
					checkMine(ii,jj);
			
		}
		else {
			showLabel(i,j);
			mineButton[i][j].setClickFlag(true);
		}
		if (isWin()) {
			win();
		}
	}

	private void clearAll(int row, int col) {
		int top,bottom, left, right, count=0;
		top=row-1>0?row-1:0;
		bottom=row+1<10?row+1:9;
		left=col-1>0?col-1:0;
		right=col+1<10?col+1:9;
		for (int i=top; i<=bottom; i++) {
			for(int j=left; j<= right; j++) {
				if (mineButton[i][j].getFlag()!=1) checkMine(i,j);
			}
		}

	}
	
	private void resetAll() {
		for (int i=0; i<10; i++) {
			for(int j=0; j< 10; j++) {
				mineButton[i][j].setFlag(0);
				mineButton[i][j].setClickFlag(false);
				mineButton[i][j].setIcon(mineStatus[0]);
				mineButton[i][j].setEnabled(true);
				mineButton[i][j].setVisible(true);
			}
		}
	}

	
	// to flag the mine you want to flag out
	void flagMine(int row, int col) {
		//System.out.println("Jerry Arrives here!");
		int i,j;
		i=row<0?0:row;
		i=i>9?9:i;
		j=col<0?0:col;
		j=j>9?9:j;
		if(mineButton[i][j].getFlag() == 0) {
			numFlaged++;
		} else if(mineButton[i][j].getFlag() == 1){
			numFlaged--;
		}
		mineCounter.resetCounter(numMine - numFlaged>=0?numMine - numFlaged:0);
		mineButton[i][j].setFlag((mineButton[i][j].getFlag() + 1) %3) ;
		showFlag(i,j);
		if (isWin()) {
			win();
		}	
	}

	// show the numbers of the nearby mines
	void showLabel(int row, int col) {
		//System.out.println("ShowLabel row:" + row + ",col:" + col);
        	int toShow;
        	toShow = mine.mine[row][col];
		if (toShow != 0) {
			mineButton[row][col].setIcon(mineNumIcon[toShow]);
			mineButton[row][col].setClickFlag(true);
			//mineButton[row][col].setEnabled(false);
		} 
		else {
			//mineButton[row][col].setIcon(mineNumIcon[0]);
			//mineButton[row][col].setClickFlag(true);
			mineButton[row][col].setEnabled(false);
		}
	}
	
	// circle the flag with blank, flaged, questioned
	void showFlag(int row, int col) {			
		mineButton[row][col].setIcon(mineStatus[mineButton[row][col].getFlag()]);
	}
	
	// the mouse events listener methods
	public void mouseEntered(MouseEvent e) {
		//System.out.println("Jerry Test");

	}
	
	// method to start the new game
	private void startNewGame(int num, int row, int col) {
		mine = new JMineArth(num, row, col);
		//mine.printMine();
		gameStarted = true;

		timerThread = new TimeCounterThread(timeCounter);
		timerThread.start(); 
	}
	
	public void setNewGame(int num){
		resetAll();
		numMine = num;
		numFlaged = 0;
		gameStarted = false;
		mineCounter.resetCounter(numMine);
		timeCounter.resetCounter(0);
		timerThread.stop();
	}

	// the event handle to deal with the mouse click
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == bTest) {
			setNewGame(numMine);
			return;
		}
		int  row,col;
		row=((JMineButton)e.getSource()).getRow();
		col=((JMineButton)e.getSource()).getCol();
		if (!gameStarted) {
			startNewGame(numMine, row, col);
		}

		if (e.getModifiers() == (InputEvent.BUTTON1_MASK+InputEvent.BUTTON3_MASK)) {
			//System.out.println("HA");
			clearAll(row, col);
		}
		if (!mineButton[row][col].getClickFlag()) {

			if (e.getModifiers() == InputEvent.BUTTON1_MASK) { 
				//System.out.println("LeftButton");
				if (mineButton[row][col].getFlag() == 1 ) { 
					return;
				}
				else {
					checkMine(row, col);
				}
			}
			else if (e.getModifiers() == InputEvent.BUTTON3_MASK){
				//System.out.println("RightButton");
				flagMine(row, col);
			} else {
				//System.out.println("MiddleButton");
			}
		}
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == miEasy) {
			setNewGame(12);
			return;
		}
		if(e.getSource() == miMiddle) {
			setNewGame(24);
			return;
		}
		if(e.getSource() == miHard) {
			setNewGame(36 );
			return;
		}
		if(e.getSource() == miExit) {
			System.exit(0);
		}
		if(e.getSource() == miAbout) {
			about.setVisible(true);
		}
	}
}

class RestartRunner implements Runnable {
	private WinFrame win;
	private JMine mine;
	private boolean isMineSet;
	
	private TimeCounterThread timer;
	
	public void setMine(JMine mine) {
		this.mine = mine;
	}

	public void setTimer(TimeCounterThread timer) {
		this.timer = timer;
	}
	
	public void run(){
		isMineSet = false;
		win = new WinFrame("You win!");
		while (!this.win.getWinOk()||isMineSet) {
			//System.out.println("Not start!");
		}
		mine.numMine = win.getMineNum();
		mine.setNewGame(mine.numMine);
		timer.stop();
		win.setVisible(false);		
	}

}

class TimeCounterThread extends Thread {
	private JCounter timeCounter;

	TimeCounterThread (JCounter time) {
		timeCounter = time;
	}

	public void run() {
		while (true) {
			try {
				sleep(1000);
				timeCounter.resetCounter(timeCounter.getCounterNum() +1);
				//System.out.println("zzz");
			}
			catch(InterruptedException e) {}
		}	
	}
}





