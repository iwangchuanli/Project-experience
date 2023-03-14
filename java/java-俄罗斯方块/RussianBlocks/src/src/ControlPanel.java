/**
 * File: ControlPanel.java
 * User: Administrator
 * Date: Jan 15, 2003
 * Describe: ����˹����� Java ʵ��
 */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * ��������࣬�̳���JPanel.
 * �ϱ߰���Ԥ�Դ��ڡ��ȼ����÷֡����ư�ť
 * ��Ҫ����������Ϸ���̡�
 */
class ControlPanel extends JPanel {
	private JTextField
	        tfLevel = new JTextField("" + ErsBlocksGame.DEFAULT_LEVEL),
	tfScore = new JTextField("0");

	private JButton
	        btPlay = new JButton("Play"),
	btPause = new JButton("Pause"),
	btStop = new JButton("Stop"),
	btTurnLevelUp = new JButton("Turn hard"),
	btTurnLevelDown = new JButton("Turn easy");

	private JPanel plTip = new JPanel(new BorderLayout());
	private TipPanel plTipBlock = new TipPanel();
	private JPanel plInfo = new JPanel(new GridLayout(4, 1));
	private JPanel plButton = new JPanel(new GridLayout(5, 1));

	private Timer timer;
	private ErsBlocksGame game;

	private Border border = new EtchedBorder(
	        EtchedBorder.RAISED, Color.white, new Color(148, 145, 140));

	/**
	 * ���������Ĺ��캯��
	 * @param game ErsBlocksGame, ErsBoxesGame���һ��ʵ�����ã�
	 * ����ֱ�ӿ���ErsBoxesGame�����Ϊ��
	 */
	public ControlPanel(final ErsBlocksGame game) {
		setLayout(new GridLayout(3, 1, 0, 4));
		this.game = game;

		plTip.add(new JLabel("Next block"), BorderLayout.NORTH);
		plTip.add(plTipBlock);
		plTip.setBorder(border);

		plInfo.add(new JLabel("Level"));
		plInfo.add(tfLevel);
		plInfo.add(new JLabel("Score"));
		plInfo.add(tfScore);
		plInfo.setBorder(border);

		tfLevel.setEditable(false);
		tfScore.setEditable(false);

		plButton.add(btPlay);
		plButton.add(btPause);
		plButton.add(btStop);
		plButton.add(btTurnLevelUp);
		plButton.add(btTurnLevelDown);
		plButton.setBorder(border);

		add(plTip);
		add(plInfo);
		add(plButton);

		addKeyListener(new ControlKeyListener());

		btPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				game.playGame();
			}
		});
		btPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (btPause.getText().equals(new String("Pause"))) {
					game.pauseGame();
				} else {
					game.resumeGame();
				}
			}
		});
		btStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				game.stopGame();
			}
		});
		btTurnLevelUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					int level = Integer.parseInt(tfLevel.getText());
					if (level < ErsBlocksGame.MAX_LEVEL)
						tfLevel.setText("" + (level + 1));
				} catch (NumberFormatException e) {
				}
				requestFocus();
			}
		});
		btTurnLevelDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					int level = Integer.parseInt(tfLevel.getText());
					if (level > 1)
						tfLevel.setText("" + (level - 1));
				} catch (NumberFormatException e) {
				}
				requestFocus();
			}
		});

		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent ce) {
				plTipBlock.fanning();
			}
		});

		timer = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tfScore.setText("" + game.getScore());
				int scoreForLevelUpdate =
				        game.getScoreForLevelUpdate();
				if (scoreForLevelUpdate >= ErsBlocksGame.PER_LEVEL_SCORE
				        && scoreForLevelUpdate > 0)
					game.levelUpdate();
			}
		});
		timer.start();
	}

	/**
	 * ����Ԥ�Դ��ڵ���ʽ��
	 * @param style int,��ӦErsBlock���STYLES�е�28��ֵ
	 */
	public void setTipStyle(int style) {
		plTipBlock.setStyle(style);
	}

	/**
	 * ȡ���û����õ���Ϸ�ȼ���
	 * @return int, �Ѷȵȼ���1������ErsBlocksGame.MAX_LEVEL
	 */
	public int getLevel() {
		int level = 0;
		try {
			level = Integer.parseInt(tfLevel.getText());
		} catch (NumberFormatException e) {
		}
		return level;
	}

	/**
	 * ���û��޸���Ϸ�Ѷȵȼ���
	 * @param level �޸ĺ����Ϸ�Ѷȵȼ�
	 */
	public void setLevel(int level) {
		if (level > 0 && level < 11) tfLevel.setText("" + level);
	}

	/**
	 * ����"��ʼ"��ť��״̬��
	 */
	public void setPlayButtonEnable(boolean enable) {
		btPlay.setEnabled(enable);
	}

	public void setPauseButtonLabel(boolean pause) {
		btPause.setText(pause ? "Pause" : "Continue");
	}

	/**
	 * ���ÿ������
	 */
	public void reset() {
		tfScore.setText("0");
		plTipBlock.setStyle(0);
	}

	/**
	 * ���¼���TipPanel���boxes[][]���С��Ĵ�С
	 */
	public void fanning() {
		plTipBlock.fanning();
	}

	/**
	 * Ԥ�Դ��ڵ�ʵ��ϸ����
	 */
	private class TipPanel extends JPanel {
		private Color backColor = Color.darkGray, frontColor = Color.lightGray;
		private ErsBox[][] boxes =
		        new ErsBox[ErsBlock.BOXES_ROWS][ErsBlock.BOXES_COLS];

		private int style, boxWidth, boxHeight;
		private boolean isTiled = false;

		/**
		 * Ԥ�Դ����๹�캯��
		 */
		public TipPanel() {
			for (int i = 0; i < boxes.length; i++) {
				for (int j = 0; j < boxes[i].length; j++)
					boxes[i][j] = new ErsBox(false);
			}
		}

		/**
		 * Ԥ�Դ����๹�캯��
		 * @param backColor Color, ���ڵı���ɫ
		 * @param frontColor Color, ���ڵ�ǰ��ɫ
		 */
		public TipPanel(Color backColor, Color frontColor) {
			this();
			this.backColor = backColor;
			this.frontColor = frontColor;
		}

		/**
		 * ����Ԥ�Դ��ڵķ�����ʽ
		 * @param style int,��ӦErsBlock���STYLES�е�28��ֵ
		 */
		public void setStyle(int style) {
			this.style = style;
			repaint();
		}

		/**
		 * ����JComponent��ĺ������������
		 * @param g ͼ���豸����
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			if (!isTiled) fanning();

			int key = 0x8000;
			for (int i = 0; i < boxes.length; i++) {
				for (int j = 0; j < boxes[i].length; j++) {
					Color color = (((key & style) != 0) ? frontColor : backColor);
					g.setColor(color);
					g.fill3DRect(j * boxWidth, i * boxHeight,
					        boxWidth, boxHeight, true);
					key >>= 1;
				}
			}
		}

		/**
		 * ���ݴ��ڵĴ�С���Զ���������ĳߴ�
		 */
		public void fanning() {
			boxWidth = getSize().width / ErsBlock.BOXES_COLS;
			boxHeight = getSize().height / ErsBlock.BOXES_ROWS;
			isTiled = true;
		}
	}

	private class ControlKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent ke) {
			if (!game.isPlaying()) return;

			ErsBlock block = game.getCurBlock();
			switch (ke.getKeyCode()) {
				case KeyEvent.VK_DOWN:
					block.moveDown();
					break;
				case KeyEvent.VK_LEFT:
					block.moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
					block.moveRight();
					break;
				case KeyEvent.VK_UP:
					block.turnNext();
					break;
				default:
					break;
			}
		}
	}
}
