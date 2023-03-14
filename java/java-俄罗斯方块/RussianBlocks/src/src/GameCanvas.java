/**
 * File: GameCanvas.java
 * User: Administrator
 * Date: Jan 15, 2003
 * Describe: ����˹����� Java ʵ��
 */

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * �����࣬����<����> * <����>��������ʵ����
 * �̳���JPanel�ࡣ
 * ErsBlock�߳��ද̬�ı仭����ķ�����ɫ��������ͨ��
 * ��鷽����ɫ������ErsBlock����ƶ������
 */
class GameCanvas extends JPanel {
	private Color backColor = Color.black, frontColor = Color.orange;
	private int rows, cols, score = 0, scoreForLevelUpdate = 0;
	private ErsBox[][] boxes;
	private int boxWidth, boxHeight;

	/**
	 * ������Ĺ��캯��
	 * @param rows int, ����������
	 * @param cols int, ����������
	 * ���������������Ż���ӵ�з������Ŀ
	 */
	public GameCanvas(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;

		boxes = new ErsBox[rows][cols];
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[i].length; j++) {
				boxes[i][j] = new ErsBox(false);
			}
		}

		setBorder(new EtchedBorder(
		        EtchedBorder.RAISED, Color.white, new Color(148, 145, 140)));
	}

	/**
	 * ������Ĺ��캯��
	 * @param rows ��public GameCanvas(int rows, int cols)ͬ
	 * @param cols ��public GameCanvas(int rows, int cols)ͬ
	 * @param backColor Color, ����ɫ
	 * @param frontColor Color, ǰ��ɫ
	 */
	public GameCanvas(int rows, int cols,
	                  Color backColor, Color frontColor) {
		this(rows, cols);
		this.backColor = backColor;
		this.frontColor = frontColor;
	}

	/**
	 * ������Ϸ����ɫ��
 	 * @param backColor Color, ����ɫ��
	 */
	public void setBackgroundColor(Color backColor) {
		this.backColor = backColor;
	}

	/**
	 * ȡ����Ϸ����ɫ��
 	 * @return Color, ����ɫ��
	 */
	public Color getBackgroundColor() {
		return backColor;
	}

	/**
	 * ������Ϸ����ɫ��
 	 * @param frontColor Color, ����ɫ��
	 */
	public void setBlockColor(Color frontColor) {
		this.frontColor = frontColor;
	}

	/**
	 * ȡ����Ϸ����ɫ��
 	 * @return Color, ����ɫ��
	 */
	public Color getBlockColor() {
		return frontColor;
	}

	/**
	 * ȡ�û����з��������
	 * @return int, ���������
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * ȡ�û����з��������
	 * @return int, ���������
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * ȡ����Ϸ�ɼ�
	 * @return int, ����
	 */
	public int getScore() {
		return score;
	}

	/**
	 * ȡ������һ��������Ļ���
	 * @return int, ��һ��������Ļ���
	 */
	public int getScoreForLevelUpdate() {
		return scoreForLevelUpdate;
	}

	/**
	 * �����󣬽���һ�����������Ļ�����0
	 */
	public void resetScoreForLevelUpdate() {
		scoreForLevelUpdate -= ErsBlocksGame.PER_LEVEL_SCORE;
	}

	/**
	 * �õ�ĳһ��ĳһ�еķ������á�
	 * @param row int, Ҫ���õķ������ڵ���
	 * @param col int, Ҫ���õķ������ڵ���
	 * @return ErsBox, ��row��col�еķ��������
	 */
	public ErsBox getBox(int row, int col) {
		if (row < 0 || row > boxes.length - 1
		        || col < 0 || col > boxes[0].length - 1)
			return null;
		return (boxes[row][col]);
	}

	/**
	 * ����JComponent��ĺ������������
	 * @param g ͼ���豸����
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(frontColor);
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[i].length; j++) {
				g.setColor(boxes[i][j].isColorBox() ? frontColor : backColor);
				g.fill3DRect(j * boxWidth, i * boxHeight,
				        boxWidth, boxHeight, true);
			}
		}
	}

	/**
	 * ���ݴ��ڵĴ�С���Զ���������ĳߴ�
	 */
	public void fanning() {
		boxWidth = getSize().width / cols;
		boxHeight = getSize().height / rows;
	}

	/**
	 * ��һ�б���Ϸ�ߵ����󣬽������������Ϊ��Ϸ�߼ӷ�
	 * @param row int, Ҫ������У�����ErsBoxesGame������
	 */
	public synchronized void removeLine(int row) {
		for (int i = row; i > 0; i--) {
			for (int j = 0; j < cols; j++)
				boxes[i][j] = (ErsBox) boxes[i - 1][j].clone();
		}

		score += ErsBlocksGame.PER_LINE_SCORE;
		scoreForLevelUpdate += ErsBlocksGame.PER_LINE_SCORE;
		repaint();
	}

	/**
	 * ���û������û���Ϊ0
	 */
	public void reset() {
		score = 0;
		scoreForLevelUpdate = 0;
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[i].length; j++)
				boxes[i][j].setColor(false);
		}

		repaint();
	}
}
