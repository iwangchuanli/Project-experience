package com.sf.minesweeper.frame;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.UIManager;

import com.sf.minesweeper.menu.MineMenu;
import com.sf.minesweeper.panel.MineField;
import com.sf.minesweeper.panel.MineState;
import com.sf.minesweeper.timer.Timers;
import com.sf.minesweeper.tools.Tools;

public class SartFrame extends JFrame {

	private static final long serialVersionUID = -1585043387266273492L;

	private MineState mineState; // ������
	private MineField mineField; // ����labble������
	private MineMenu mineMenu;
	private Timer timer;
	private Timers timers;
	/**
	 * ��Ϸ�Ƿ�ʼ
	 */
	private boolean isStart;
	JLabel jLabel_start = new JLabel(); // ��ʼͼƬ


	public SartFrame() {
		//�ı�ϵͳĬ������
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		
		//�������  ����  �ر�   λ��   ��С   
		this.setTitle("ɨ��");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setIconImage(Tools.iicon); // ����tools����������
		this.setResizable(false); // �����ô��ڲ���ɷŴ�
		
		
		// ״̬��
		mineState = new MineState(this);
		this.add(mineState, BorderLayout.NORTH);

		// ����
		mineField = new MineField(this);
		this.add(mineField, BorderLayout.CENTER);
		jLabel_start.setIcon(Tools.start);
		this.add(jLabel_start, BorderLayout.CENTER);

		// �˵���
		mineMenu = new MineMenu(this);
		this.setJMenuBar(mineMenu);
		
		// ʱ��
		Tools.time = 0;
		timers = new Timers(mineState);
		timer = new Timer(1000, timers);

		// ����
//		AudioClip s1=loadSound("alarm1.wav");                           //AudioClip��Ķ���s1ͨ������ loadSound()װ������
//		public AudioClip loadSound(String filename){                        //����һ��AudioClip����
//		   URL url=null;                                                                   //��ΪnewAudioClip()�Ĳ���ΪURL��
//		   try{
//		    url=new URL("file:"+filename);                                //ָ���ļ�����file:"������
//		   }
//		   catch(MalformedURLException e){ }
//		   return Applet.newAudioClip(url);                           //ͨ��newAudioClip( )����װ���������˷���ΪJDK����ӵķ�����̫�ϵ�JDK�����û��
//		}
//		
		pack();
		this.setVisible(true);
	}


	// ���¿�ʼ�����²���
	public void restart() {

		this.remove(mineState);

		this.remove(mineField);

		this.remove(jLabel_start);
		
		// ״̬��
		mineState = new MineState(this);
		this.add(mineState, BorderLayout.NORTH);
		
		// ����
		mineField = new MineField(this);
		this.add(mineField, BorderLayout.CENTER);

		// ʱ��
		Tools.time = 0;
		Timers timers = new Timers(mineState);
		timer = new Timer(1000, timers);
		
		pack();
		validate();//ˢ�´���
	}

	/**����*/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MineState getMineState() {
		return mineState;
	}

	public MineField getMineField() {
		return mineField;
	}
	
	public MineMenu getMineMenu() {
		return mineMenu;
	}

	public Timer getTimer() {
		return timer;
	}

	public Timers getTimers() {
		return timers;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}
	
	/**main������ */
	public static void main(String[] args) {
		new SartFrame();//����
	}
	
	
	
	

	
	
}
