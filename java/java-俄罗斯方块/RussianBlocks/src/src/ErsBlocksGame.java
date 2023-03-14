/**
 * File: ErsBlocksGame.java
 * User: Administrator
 * Date: Jan 15, 2003
 * Describe: 俄罗斯方块的 Java 实现
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 游戏主类，继承自JFrame类，负责游戏的全局控制。
 * 内含
 * 1, 一个GameCanvas画布类的实例引用，
 * 2, 一个保存当前活动块(ErsBlock)实例的引用，
 * 3, 一个保存当前控制面板（ControlPanel）实例的引用;
 */
public class ErsBlocksGame extends JFrame {
	/**
	 *  每填满一行计多少分
	 */
	public final static int PER_LINE_SCORE = 100;
	/**
	 * 积多少分以后能升级
	 */
	public final static int PER_LEVEL_SCORE = PER_LINE_SCORE * 20;
	/**
	 * 最大级数是10级
	 */
	public final static int MAX_LEVEL = 10;
	/**
	 * 默认级数是5
	 */
	public final static int DEFAULT_LEVEL = 5;

	private GameCanvas canvas;
	private ErsBlock block;
	private boolean playing = false;
	private ControlPanel ctrlPanel;

	private JMenuBar bar = new JMenuBar();
	private JMenu
	        mGame = new JMenu("游戏"),
			mControl = new JMenu("控制"),
			mWindowStyle = new JMenu("WindowStyle"),
			mInfo = new JMenu("Information");
	private JMenuItem
	        miNewGame = new JMenuItem("新游戏"),
			miSetBlockColor = new JMenuItem("Set Block Color ..."),
			miSetBackColor = new JMenuItem("Set Background Color ..."),
			miTurnHarder = new JMenuItem("Turn up the level"),
			miTurnEasier = new JMenuItem("Turn down the level"),
			miExit = new JMenuItem("Eixt"),

			miPlay = new JMenuItem("Play"),
			miPause = new JMenuItem("Pause"),
			miResume = new JMenuItem("Resume"),
			miStop = new JMenuItem("Stop"),

			miAuthor = new JMenuItem("Author : apple@radiantek.com"),
			miSourceInfo = new JMenuItem("You can get the source code / document by email");


	private JCheckBoxMenuItem
	        miAsWindows = new JCheckBoxMenuItem("Windows"),
			miAsMotif = new JCheckBoxMenuItem("Motif"),
			miAsMetal = new JCheckBoxMenuItem("Metal", true);

	/**
	 * 主游戏类的构造函数
	 * @param title String，窗口标题
	 */
	public ErsBlocksGame(String title) {
		super(title);

		setSize(315, 392);
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((scrSize.width - getSize().width) / 2,
		        (scrSize.height - getSize().height) / 2);

		createMenu();

		Container container = getContentPane();
		container.setLayout(new BorderLayout(6, 0));

		canvas = new GameCanvas(20, 12);
		ctrlPanel = new ControlPanel(this);

		container.add(canvas, BorderLayout.CENTER);
		container.add(ctrlPanel, BorderLayout.EAST);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				stopGame();
				System.exit(0);
			}
		});

		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent ce) {
				canvas.fanning();
			}
		});

		show();
		canvas.fanning();
	}

	/**
	 * 让游戏“复位”
	 */
	public void reset() {
		ctrlPanel.reset();
		canvas.reset();
	}

	/**
	 * 判断游戏是否还在进行
	 * @return boolean, true-还在运行，false-已经停止
	 */
	public boolean isPlaying() {
		return playing;
	}

	/**
	 * 得到当前活动的块
	 * @return ErsBlock, 当前活动块的引用
	 */
	public ErsBlock getCurBlock() {
		return block;
	}

	/**
	 * 得到当前画布
	 * @return GameCanvas, 当前画布的引用
	 */
	public GameCanvas getCanvas() {
		return canvas;
	}

	/**
	 * 开始游戏
	 */
	public void playGame() {
		play();
		ctrlPanel.setPlayButtonEnable(false);
		miPlay.setEnabled(false);
		ctrlPanel.requestFocus();
	}

	/**
	 * 游戏暂停
	 */
	public void pauseGame() {
		if (block != null) block.pauseMove();

		ctrlPanel.setPauseButtonLabel(false);
		miPause.setEnabled(false);
		miResume.setEnabled(true);
	}

	/**
	 * 让暂停中的游戏继续
	 */
	public void resumeGame() {
		if (block != null) block.resumeMove();
		ctrlPanel.setPauseButtonLabel(true);
		miPause.setEnabled(true);
		miResume.setEnabled(false);
		ctrlPanel.requestFocus();
	}

	/**
	 * 用户停止游戏
	 */
	public void stopGame() {
		playing = false;
		if (block != null) block.stopMove();
		miPlay.setEnabled(true);
		miPause.setEnabled(true);
		miResume.setEnabled(false);
		ctrlPanel.setPlayButtonEnable(true);
		ctrlPanel.setPauseButtonLabel(true);
	}

	/**
	 * 得到当前游戏者设置的游戏难度
	 * @return int, 游戏难度1－MAX_LEVEL
	 */
	public int getLevel() {
		return ctrlPanel.getLevel();
	}

	/**
	 * 让用户设置游戏难度
	 * @param level int, 游戏难度1－MAX_LEVEL
	 */
	public void setLevel(int level) {
		if (level < 11 && level > 0) ctrlPanel.setLevel(level);
	}

	/**
	 * 得到游戏积分
	 * @return int, 积分。
	 */
	public int getScore() {
		if (canvas != null) return canvas.getScore();
		return 0;
	}

	/**
	 * 得到自上次升级以来的游戏积分，升级以后，此积分清零
	 * @return int, 积分。
	 */
	public int getScoreForLevelUpdate() {
		if (canvas != null) return canvas.getScoreForLevelUpdate();
		return 0;
	}

	/**
	 * 当分数累计到一定的数量时，升一次级
	 * @return boolean, ture-update successufl, false-update fail
	 */
	public boolean levelUpdate() {
		int curLevel = getLevel();
		if (curLevel < MAX_LEVEL) {
			setLevel(curLevel + 1);
			canvas.resetScoreForLevelUpdate();
			return true;
		}
		return false;
	}

	/**
	 * 游戏开始
	 */
	private void play() {
		reset();
		playing = true;
		Thread thread = new Thread(new Game());
		thread.start();
	}

	/**
	 * 报告游戏结束了
	 */
	private void reportGameOver() {
		JOptionPane.showMessageDialog(this, "Game Over!");
	}

	/**
	 * 建立并设置窗口菜单
	 */
	private void createMenu() {
		bar.add(mGame);
		bar.add(mControl);
		bar.add(mWindowStyle);
		bar.add(mInfo);

		mGame.add(miNewGame);
		mGame.addSeparator();
		mGame.add(miSetBlockColor);
		mGame.add(miSetBackColor);
		mGame.addSeparator();
		mGame.add(miTurnHarder);
		mGame.add(miTurnEasier);
		mGame.addSeparator();
		mGame.add(miExit);

		mControl.add(miPlay);
		mControl.add(miPause);
		mControl.add(miResume);
		mControl.add(miStop);

		mWindowStyle.add(miAsWindows);
		mWindowStyle.add(miAsMotif);
		mWindowStyle.add(miAsMetal);

		mInfo.add(miAuthor);
		mInfo.add(miSourceInfo);

		setJMenuBar(bar);

		miPause.setAccelerator(
		        KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK));
		miResume.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));

		miNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				stopGame();
				reset();
				setLevel(DEFAULT_LEVEL);
			}
		});
		miSetBlockColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Color newFrontColor =
				        JColorChooser.showDialog(ErsBlocksGame.this,
				                "Set color for block", canvas.getBlockColor());
				if (newFrontColor != null)
					canvas.setBlockColor(newFrontColor);
			}
		});
		miSetBackColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Color newBackColor =
				        JColorChooser.showDialog(ErsBlocksGame.this,
				                "Set color for block", canvas.getBackgroundColor());
				if (newBackColor != null)
					canvas.setBackgroundColor(newBackColor);
			}
		});
		miTurnHarder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int curLevel = getLevel();
				if (curLevel < MAX_LEVEL) setLevel(curLevel + 1);
			}
		});
		miTurnEasier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int curLevel = getLevel();
				if (curLevel > 1) setLevel(curLevel - 1);
			}
		});
		miExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		miPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				playGame();
			}
		});
		miPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				pauseGame();
			}
		});
		miResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resumeGame();
			}
		});
		miStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				stopGame();
			}
		});
		miAsWindows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String plaf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
				setWindowStyle(plaf);
				canvas.fanning();
				ctrlPanel.fanning();
				miAsWindows.setState(true);
				miAsMetal.setState(false);
				miAsMotif.setState(false);
			}
		});
		miAsMotif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String plaf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
				setWindowStyle(plaf);
				canvas.fanning();
				ctrlPanel.fanning();
				miAsWindows.setState(false);
				miAsMetal.setState(false);
				miAsMotif.setState(true);
			}
		});
		miAsMetal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String plaf = "javax.swing.plaf.metal.MetalLookAndFeel";
				setWindowStyle(plaf);
				canvas.fanning();
				ctrlPanel.fanning();
				miAsWindows.setState(false);
				miAsMetal.setState(true);
				miAsMotif.setState(false);
			}
		});
	}

	/**
	 * 根据字串设置窗口外观
	 * @param plaf String, 窗口外观的描述
	 */
	private void setWindowStyle(String plaf) {
		try {
			UIManager.setLookAndFeel(plaf);
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
		}
	}

	/**
	 * 一轮游戏过程，实现了Runnable接口
	 * 一轮游戏是一个大循环，在这个循环中，每隔100毫秒，
	 * 检查游戏中的当前块是否已经到底了，如果没有，
	 * 就继续等待。如果到底了，就看有没有全填满的行，
	 * 如果有就删除它，并为游戏者加分，同时随机产生一个
	 * 新的当前块，让它自动下落。
	 * 当新产生一个块时，先检查画布最顶上的一行是否已经
	 * 被占了，如果是，可以判断Game Over了。
	 */
	private class Game implements Runnable {
		public void run() {
			int col = (int) (Math.random() * (canvas.getCols() - 3)),
			        style = ErsBlock.STYLES[(int) (Math.random() * 7)][(int) (Math.random() * 4)];

			while (playing) {
				if (block != null) {    //第一次循环时，block为空
					if (block.isAlive()) {
						try {
							Thread.currentThread().sleep(100);
						} catch (InterruptedException ie) {
							ie.printStackTrace();
						}
						continue;
					}
				}

				checkFullLine();        //检查是否有全填满的行

				if (isGameOver()) {     //检查游戏是否应该结束了
					miPlay.setEnabled(true);
					miPause.setEnabled(true);
					miResume.setEnabled(false);
					ctrlPanel.setPlayButtonEnable(true);
					ctrlPanel.setPauseButtonLabel(true);

					reportGameOver();
					return;
				}

				block = new ErsBlock(style, -1, col, getLevel(), canvas);
				block.start();

				col = (int) (Math.random() * (canvas.getCols() - 3));
				style = ErsBlock.STYLES[(int) (Math.random() * 7)][(int) (Math.random() * 4)];

				ctrlPanel.setTipStyle(style);
			}
		}

		/**
		 * 检查画布中是否有全填满的行，如果有就删除之
		 */
		public void checkFullLine() {
			for (int i = 0; i < canvas.getRows(); i++) {
				int row = -1;
				boolean fullLineColorBox = true;
				for (int j = 0; j < canvas.getCols(); j++) {
					if (!canvas.getBox(i, j).isColorBox()) {
						fullLineColorBox = false;
						break;
					}
				}
				if (fullLineColorBox) {
					row = i--;
					canvas.removeLine(row);
				}
			}
		}

		/**
		 * 根据最顶行是否被占，判断游戏是否已经结束了。
		 * @return boolean, true-游戏结束了，false-游戏未结束
		 */
		private boolean isGameOver() {
			for (int i = 0; i < canvas.getCols(); i++) {
				ErsBox box = canvas.getBox(0, i);
				if (box.isColorBox()) return true;
			}
			return false;
		}
	}

	/**
	 * 程序入口函数
	 * @param args String[], 附带的命令行参数
	 */
	public static void main(String[] args) {
		new ErsBlocksGame("Russia Blocks by apple@radiantek.com");
	}
}
