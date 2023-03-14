package com.sf.minesweeper.tools;
//Download by http://www.codefans.net
import java.awt.Image;

import javax.swing.ImageIcon;

public class Tools {
	
	public static ImageIcon start = new ImageIcon("./image/start.png");
	/**
	 * totalx��totaly��totalBobm �ֱ��Ӧ����������������������������
	 */
	public static int totalx = 9;
	public static int totaly = 9;
	public static int totalMine = 10;
	
	
	public static int time = 0;
	public static int time1= 999;
	public static int time2= 999;
	public static int time3= 999;
	public static String name1="����";
	public static String name2="����";
	public static String name3="����";
	

	/**
	 * ��Ϸ�ȼ�
	 */
	public static final String LOWER_LEVEL = "����";
	public static final String MIDDLE_LEVEL = "�м�";
	public static final String HEIGHT_LEVEL = "�߼�";
	public static final String CUSTOM_LEVEL = "�Զ���";
	/**
	 * ��Ϸ��ǰ�ȼ�
	 */
	public static String currentLevel = LOWER_LEVEL;


	/**
	 *  �������0-8���ֱ�ǩͼƬ
	 * 
	 */

	public static ImageIcon mineCount[];

	/**
	 *  ������� d0-d10ʱ���ǩͼƬ
	 * 
	 */

	public static ImageIcon timeCount[];
	static {

		mineCount = new ImageIcon[9];
		for (int i = 0; i <= 8; i++) {
			mineCount[i] = new ImageIcon("./image/" + i + ".gif");
		}

		timeCount = new ImageIcon[11];
		for (int i = 0; i < 10; i++) {
			timeCount[i] = new ImageIcon("./image/d" + i + ".gif");
		}
		timeCount[10] = new ImageIcon("./image/d10.gif");
	}

	/**
	 * �ֱ��Ӧface0-face9�����ǩͼƬ
	 * 
	 */

	public static ImageIcon iiface0 = new ImageIcon("./image/face0.gif");

	public static ImageIcon iiface1 = new ImageIcon("./image/face1.gif");

	public static ImageIcon iiface2 = new ImageIcon("./image/face2.gif");

	public static ImageIcon iiface3 = new ImageIcon("./image/face3.gif");

	public static ImageIcon iiface4 = new ImageIcon("./image/face4.gif");

	public static ImageIcon iiface5 = new ImageIcon("./image/face5.gif");

	public static ImageIcon iiface6 = new ImageIcon("./image/face6.gif");

	public static ImageIcon iiface7 = new ImageIcon("./image/face7.gif");

	public static ImageIcon iiface8 = new ImageIcon("./image/face8.gif");

	public static ImageIcon iiface9 = new ImageIcon("./image/face9.gif");

	/**
	 * �ֱ��Ӧ mine-mine3�ױ����ǩͼƬ
	 * 
	 */

	public static ImageIcon iimine = new ImageIcon("./image/mine.gif");

	public static ImageIcon iimine0 = new ImageIcon("./image/mine0.gif");

	public static ImageIcon iimine1 = new ImageIcon("./image/mine1.gif");

	public static ImageIcon iimine2 = new ImageIcon("./image/mine2.gif");

	public static ImageIcon iimine3 = new ImageIcon("./image/mine3.gif");

	/**
	 * �ֱ��Ӧ ask-ask2�ʺű����ǩͼƬ
	 * 
	 */

	public static ImageIcon iiask = new ImageIcon("./image/ask.gif");

	public static ImageIcon iiask0 = new ImageIcon("./image/ask0.gif");

	public static ImageIcon iiask1 = new ImageIcon("./image/ask1.gif");

	public static ImageIcon iiask2 = new ImageIcon("./image/ask2.gif");

	public static ImageIcon iiconTemp = new ImageIcon("./image/icon.gif");

	public static Image iicon = iiconTemp.getImage();

	public static ImageIcon iiblank = new ImageIcon("./image/blank.gif");

	public static ImageIcon iiblood = new ImageIcon("./image/blood.gif");

	public static ImageIcon iierror = new ImageIcon("./image/error.gif");

	/**
	 * iiflag ��Ӧ���ӱ����ǩͼƬ
	 * 
	 */

	public static ImageIcon iiflag = new ImageIcon("./image/flag.gif");

	/**
	 * iihole ��Ӧ������ǩͼƬ
	 * 
	 */

	public static ImageIcon iihole = new ImageIcon("./image/hole.gif");

	/**
	 * Ƥ��
	 */
	public static final String metal = "javax.swing.plaf.metal.MetalLookAndFeel";

	public static final String motif = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";

	public static final String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

}
