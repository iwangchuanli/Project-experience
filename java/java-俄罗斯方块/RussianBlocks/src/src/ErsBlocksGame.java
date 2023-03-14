/**
 * File: ErsBlocksGame.java
 * User: Administrator
 * Date: Jan 15, 2003
 * Describe: ����˹����� Java ʵ��
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ��Ϸ���࣬�̳���JFrame�࣬������Ϸ��ȫ�ֿ��ơ�
 * �ں�
 * 1, һ��GameCanvas�������ʵ�����ã�
 * 2, һ�����浱ǰ���(ErsBlock)ʵ�������ã�
 * 3, һ�����浱ǰ������壨ControlPanel��ʵ��������;
 */
public class ErsBlocksGame extends JFrame {
	/**
	 *  ÿ����һ�мƶ��ٷ�
	 */
	public final static int PER_LINE_SCORE = 100;
	/**
	 * �����ٷ��Ժ�������
	 */
	public final static int PER_LEVEL_SCORE = PER_LINE_SCORE * 20;
	/**
	 * �������10��
	 */
	public final static int MAX_LEVEL = 10;
	/**
	 * Ĭ�ϼ�����5
	 */
	public final static int DEFAULT_LEVEL = 5;

	private GameCanvas canvas;
	private ErsBlock block;
	private boolean playing = false;
	private ControlPanel ctrlPanel;

	private JMenuBar bar = new JMenuBar();
	private JMenu
	        mGame = new JMenu("��Ϸ"),
			mControl = new JMenu("����"),
			mWindowStyle = new JMenu("WindowStyle"),
			mInfo = new JMenu("Information");
	private JMenuItem
	        miNewGame = new JMenuItem("����Ϸ"),
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
	 * ����Ϸ��Ĺ��캯��
	 * @param title String�����ڱ���
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
	 * ����Ϸ����λ��
	 */
	public void reset() {
		ctrlPanel.reset();
		canvas.reset();
	}

	/**
	 * �ж���Ϸ�Ƿ��ڽ���
	 * @return boolean, true-�������У�false-�Ѿ�ֹͣ
	 */
	public boolean isPlaying() {
		return playing;
	}

	/**
	 * �õ���ǰ��Ŀ�
	 * @return ErsBlock, ��ǰ��������
	 */
	public ErsBlock getCurBlock() {
		return block;
	}

	/**
	 * �õ���ǰ����
	 * @return GameCanvas, ��ǰ����������
	 */
	public GameCanvas getCanvas() {
		return canvas;
	}

	/**
	 * ��ʼ��Ϸ
	 */
	public void playGame() {
		play();
		ctrlPanel.setPlayButtonEnable(false);
		miPlay.setEnabled(false);
		ctrlPanel.requestFocus();
	}

	/**
	 * ��Ϸ��ͣ
	 */
	public void pauseGame() {
		if (block != null) block.pauseMove();

		ctrlPanel.setPauseButtonLabel(false);
		miPause.setEnabled(false);
		miResume.setEnabled(true);
	}

	/**
	 * ����ͣ�е���Ϸ����
	 */
	public void resumeGame() {
		if (block != null) block.resumeMove();
		ctrlPanel.setPauseButtonLabel(true);
		miPause.setEnabled(true);
		miResume.setEnabled(false);
		ctrlPanel.requestFocus();
	}

	/**
	 * �û�ֹͣ��Ϸ
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
	 * �õ���ǰ��Ϸ�����õ���Ϸ�Ѷ�
	 * @return int, ��Ϸ�Ѷ�1��MAX_LEVEL
	 */
	public int getLevel() {
		return ctrlPanel.getLevel();
	}

	/**
	 * ���û�������Ϸ�Ѷ�
	 * @param level int, ��Ϸ�Ѷ�1��MAX_LEVEL
	 */
	public void setLevel(int level) {
		if (level < 11 && level > 0) ctrlPanel.setLevel(level);
	}

	/**
	 * �õ���Ϸ����
	 * @return int, ���֡�
	 */
	public int getScore() {
		if (canvas != null) return canvas.getScore();
		return 0;
	}

	/**
	 * �õ����ϴ�������������Ϸ���֣������Ժ󣬴˻�������
	 * @return int, ���֡�
	 */
	public int getScoreForLevelUpdate() {
		if (canvas != null) return canvas.getScoreForLevelUpdate();
		return 0;
	}

	/**
	 * �������ۼƵ�һ��������ʱ����һ�μ�
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
	 * ��Ϸ��ʼ
	 */
	private void play() {
		reset();
		playing = true;
		Thread thread = new Thread(new Game());
		thread.start();
	}

	/**
	 * ������Ϸ������
	 */
	private void reportGameOver() {
		JOptionPane.showMessageDialog(this, "Game Over!");
	}

	/**
	 * ���������ô��ڲ˵�
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
	 * �����ִ����ô������
	 * @param plaf String, ������۵�����
	 */
	private void setWindowStyle(String plaf) {
		try {
			UIManager.setLookAndFeel(plaf);
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
		}
	}

	/**
	 * һ����Ϸ���̣�ʵ����Runnable�ӿ�
	 * һ����Ϸ��һ����ѭ���������ѭ���У�ÿ��100���룬
	 * �����Ϸ�еĵ�ǰ���Ƿ��Ѿ������ˣ����û�У�
	 * �ͼ����ȴ�����������ˣ��Ϳ���û��ȫ�������У�
	 * ����о�ɾ��������Ϊ��Ϸ�߼ӷ֣�ͬʱ�������һ��
	 * �µĵ�ǰ�飬�����Զ����䡣
	 * ���²���һ����ʱ���ȼ�黭����ϵ�һ���Ƿ��Ѿ�
	 * ��ռ�ˣ�����ǣ������ж�Game Over�ˡ�
	 */
	private class Game implements Runnable {
		public void run() {
			int col = (int) (Math.random() * (canvas.getCols() - 3)),
			        style = ErsBlock.STYLES[(int) (Math.random() * 7)][(int) (Math.random() * 4)];

			while (playing) {
				if (block != null) {    //��һ��ѭ��ʱ��blockΪ��
					if (block.isAlive()) {
						try {
							Thread.currentThread().sleep(100);
						} catch (InterruptedException ie) {
							ie.printStackTrace();
						}
						continue;
					}
				}

				checkFullLine();        //����Ƿ���ȫ��������

				if (isGameOver()) {     //�����Ϸ�Ƿ�Ӧ�ý�����
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
		 * ��黭�����Ƿ���ȫ�������У�����о�ɾ��֮
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
		 * ��������Ƿ�ռ���ж���Ϸ�Ƿ��Ѿ������ˡ�
		 * @return boolean, true-��Ϸ�����ˣ�false-��Ϸδ����
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
	 * ������ں���
	 * @param args String[], �����������в���
	 */
	public static void main(String[] args) {
		new ErsBlocksGame("Russia Blocks by apple@radiantek.com");
	}
}
