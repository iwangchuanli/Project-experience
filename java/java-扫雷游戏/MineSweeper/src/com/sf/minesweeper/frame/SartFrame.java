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

	private MineState mineState; // 记雷数
	private MineField mineField; // 布置labble和雷数
	private MineMenu mineMenu;
	private Timer timer;
	private Timers timers;
	/**
	 * 游戏是否开始
	 */
	private boolean isStart;
	JLabel jLabel_start = new JLabel(); // 开始图片


	public SartFrame() {
		//改变系统默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		
		//界面设计  标题  关闭   位置   大小   
		this.setTitle("扫雷");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setIconImage(Tools.iicon); // 利用tools来做的作法
		this.setResizable(false); // 这样让窗口不会可放大
		
		
		// 状态栏
		mineState = new MineState(this);
		this.add(mineState, BorderLayout.NORTH);

		// 雷区
		mineField = new MineField(this);
		this.add(mineField, BorderLayout.CENTER);
		jLabel_start.setIcon(Tools.start);
		this.add(jLabel_start, BorderLayout.CENTER);

		// 菜单栏
		mineMenu = new MineMenu(this);
		this.setJMenuBar(mineMenu);
		
		// 时间
		Tools.time = 0;
		timers = new Timers(mineState);
		timer = new Timer(1000, timers);

		// 声音
//		AudioClip s1=loadSound("alarm1.wav");                           //AudioClip类的对象s1通过方法 loadSound()装载声音
//		public AudioClip loadSound(String filename){                        //返回一个AudioClip对象
//		   URL url=null;                                                                   //因为newAudioClip()的参数为URL型
//		   try{
//		    url=new URL("file:"+filename);                                //指定文件，“file:"不能少
//		   }
//		   catch(MalformedURLException e){ }
//		   return Applet.newAudioClip(url);                           //通过newAudioClip( )方法装载声音，此方法为JDK后添加的方法，太老的JDK里可能没有
//		}
//		
		pack();
		this.setVisible(true);
	}


	// 重新开始后重新布局
	public void restart() {

		this.remove(mineState);

		this.remove(mineField);

		this.remove(jLabel_start);
		
		// 状态栏
		mineState = new MineState(this);
		this.add(mineState, BorderLayout.NORTH);
		
		// 雷区
		mineField = new MineField(this);
		this.add(mineField, BorderLayout.CENTER);

		// 时间
		Tools.time = 0;
		Timers timers = new Timers(mineState);
		timer = new Timer(1000, timers);
		
		pack();
		validate();//刷新窗体
	}

	/**控制*/
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
	
	/**main主方法 */
	public static void main(String[] args) {
		new SartFrame();//调用
	}
	
	
	
	

	
	
}
