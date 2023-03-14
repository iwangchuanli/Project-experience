/**
 * File: ErsBlock.java
 * User: Administrator
 * Date: Jan 15, 2003
 * Describe: ����˹����� Java ʵ��
 */

/**
 * ���࣬�̳����߳��ࣨThread��
 * �� 4 * 4 ������ErsBox������һ���飬
 * ���ƿ���ƶ������䡢���ε�
 */
class ErsBlock extends Thread {
	/**
	 * һ����ռ��������4��
	 */
	public final static int BOXES_ROWS = 4;
	/**
	 * һ����ռ��������4��
	 */
	public final static int BOXES_COLS = 4;
	/**
	 * �������仯ƽ�������ӣ�������󼸼�֮����ٶ�����һ��
	 */
	public final static int LEVEL_FLATNESS_GENE = 3;
	/**
	 * ���������֮�䣬��ÿ����һ�е�ʱ����Ϊ����(����)
	 */
	public final static int BETWEEN_LEVELS_DEGRESS_TIME = 50;
	/**
	 * �������ʽ��ĿΪ7
	 */
	private final static int BLOCK_KIND_NUMBER = 7;
	/**
	 * ÿһ����ʽ�ķ���ķ�ת״̬����Ϊ4
	 */
	private final static int BLOCK_STATUS_NUMBER = 4;
	/**
	 * �ֱ��Ӧ��7��ģ�͵�28��״̬
	 */
	public final static int[][] STYLES = {// ��28��״̬
		{0x0f00, 0x4444, 0x0f00, 0x4444}, // �����͵�����״̬
		{0x04e0, 0x0464, 0x00e4, 0x04c4}, // 'T'�͵�����״̬
		{0x4620, 0x6c00, 0x4620, 0x6c00}, // ��'Z'�͵�����״̬
		{0x2640, 0xc600, 0x2640, 0xc600}, // 'Z'�͵�����״̬
		{0x6220, 0x1700, 0x2230, 0x0740}, // '7'�͵�����״̬
		{0x6440, 0x0e20, 0x44c0, 0x8e00}, // ��'7'�͵�����״̬
		{0x0660, 0x0660, 0x0660, 0x0660}, // ���������״̬
	};

	private GameCanvas canvas;
	private ErsBox[][] boxes = new ErsBox[BOXES_ROWS][BOXES_COLS];
	private int style, y, x, level;
	private boolean pausing = false, moving = true;

	/**
	 * ���캯��������һ���ض��Ŀ�
	 * @param style �����ʽ����ӦSTYLES��28��ֵ�е�һ��
	 * @param y ��ʼλ�ã����Ͻ���canvas�е�������
	 * @param x ��ʼλ�ã����Ͻ���canvas�е�������
	 * @param level ��Ϸ�ȼ������ƿ�������ٶ�
	 * @param canvas ����
	 */
	public ErsBlock(int style, int y, int x, int level, GameCanvas canvas) {
		this.style = style;
		this.y = y;
		this.x = x;
		this.level = level;
		this.canvas = canvas;

		int key = 0x8000;
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[i].length; j++) {
				boolean isColor = ((style & key) != 0);
				boxes[i][j] = new ErsBox(isColor);
				key >>= 1;
			}
		}

		display();
	}

	/**
	 * �߳����run()�������ǣ�����飬ֱ���鲻��������
	 */
	public void run() {
		while (moving) {
			try {
				sleep(BETWEEN_LEVELS_DEGRESS_TIME
				        * (ErsBlocksGame.MAX_LEVEL - level + LEVEL_FLATNESS_GENE));
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			//��ߵ�moving�Ǳ�ʾ�ڵȴ���100����䣬movingû���ı�
			if (!pausing) moving = (moveTo(y + 1, x) && moving);
		}
	}

	/**
	 * �������ƶ�һ��
	 */
	public void moveLeft() {
		moveTo(y, x - 1);
	}

	/**
	 * �������ƶ�һ��
	 */
	public void moveRight() {
		moveTo(y, x + 1);
	}

	/**
	 * ��������һ��
	 */
	public void moveDown() {
		moveTo(y + 1, x);
	}

	/**
	 * �����
	 */
	public void turnNext() {
		for (int i = 0; i < BLOCK_KIND_NUMBER; i++) {
			for (int j = 0; j < BLOCK_STATUS_NUMBER; j++) {
				if (STYLES[i][j] == style) {
					int newStyle = STYLES[i][(j + 1) % BLOCK_STATUS_NUMBER];
					turnTo(newStyle);
					return;
				}
			}
		}
	}

	/**
	 * ��ͣ������䣬��Ӧ��Ϸ��ͣ
	 */
	public void pauseMove() {
		pausing = true;
	}

	/**
	 * ����������䣬��Ӧ��Ϸ����
	 */
	public void resumeMove() {
		pausing = false;
	}

	/**
	 * ֹͣ������䣬��Ӧ��Ϸֹͣ
	 */
	public void stopMove() {
		moving = false;
	}

	/**
	 * ����ǰ��ӻ����Ķ�Ӧλ���Ƴ���Ҫ�ȵ��´��ػ�����ʱ���ܷ�ӳ����
	 */
	private void earse() {
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[i].length; j++) {
				if (boxes[i][j].isColorBox()) {
					ErsBox box = canvas.getBox(i + y, j + x);
					if (box == null) continue;
					box.setColor(false);
				}
			}
		}
	}

	/**
	 * �õ�ǰ������ڻ����Ķ�Ӧλ���ϣ�Ҫ�ȵ��´��ػ�����ʱ���ܿ���
	 */
	private void display() {
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[i].length; j++) {
				if (boxes[i][j].isColorBox()) {
					ErsBox box = canvas.getBox(y + i, x + j);
					if (box == null) continue;
					box.setColor(true);
				}
			}
		}
	}

	/**
	 * ��ǰ���ܷ��ƶ���newRow/newCol��ָ����λ��
	 * @param newRow int, Ŀ�ĵ�������
	 * @param newCol int, Ŀ�ĵ�������
	 * @return boolean, true-���ƶ���false-����
	 */
	private boolean isMoveAble(int newRow, int newCol) {
		earse();
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[i].length; j++) {
				if (boxes[i][j].isColorBox()) {
					ErsBox box = canvas.getBox(newRow + i, newCol + j);
					if (box == null || (box.isColorBox())) {
						display();
						return false;
					}
				}
			}
		}
		display();
		return true;
	}

	/**
	 * ����ǰ���ƶ���newRow/newCol��ָ����λ��
	 * @param newRow int, Ŀ�ĵ�������
	 * @param newCol int, Ŀ�ĵ�������
	 * @return boolean, true-�ƶ��ɹ���false-�ƶ�ʧ��
	 */
	private synchronized boolean moveTo(int newRow, int newCol) {
		if (!isMoveAble(newRow, newCol) || !moving) return false;

		earse();
		y = newRow;
		x = newCol;

		display();
		canvas.repaint();

		return true;
	}

	/**
	 * ��ǰ���ܷ���newStyle��ָ���Ŀ���ʽ����Ҫ��Ҫ����
	 * �߽��Լ��������鵲ס�������ƶ������
	 * @param newStyle int,ϣ���ı�Ŀ���ʽ����ӦSTYLES��28��ֵ�е�һ��
	 * @return boolean,true-�ܸı䣬false-���ܸı�
	 */
	private boolean isTurnAble(int newStyle) {
		int key = 0x8000;
		earse();
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[i].length; j++) {
				if ((newStyle & key) != 0) {
					ErsBox box = canvas.getBox(y + i, x + j);
					if (box == null || box.isColorBox()) {
						display();
						return false;
					}
				}
				key >>= 1;
			}
		}
		display();
		return true;
	}

	/**
	 * ����ǰ����newStyle��ָ���Ŀ���ʽ
	 * @param newStyle int,��Ҫ�ı�ɵĿ���ʽ����ӦSTYLES��28��ֵ�е�һ��
	 * @return boolean,true-�ı�ɹ���false-�ı�ʧ��
	 */
	private boolean turnTo(int newStyle) {
		if (!isTurnAble(newStyle) || !moving) return false;

		earse();
		int key = 0x8000;
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes[i].length; j++) {
				boolean isColor = ((newStyle & key) != 0);
				boxes[i][j].setColor(isColor);
				key >>= 1;
			}
		}
		style = newStyle;

		display();
		canvas.repaint();

		return true;
	}
}
