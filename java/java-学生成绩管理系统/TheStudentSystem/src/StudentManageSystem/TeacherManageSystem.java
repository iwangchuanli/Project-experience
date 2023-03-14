package StudentManageSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



@SuppressWarnings("serial")
public class TeacherManageSystem extends JFrame implements ActionListener{
	Container contentPane;
	JMenuBar myMenuBar = new JMenuBar();
	JMenu aboutSystem;
	JMenu searchModel;
	
	SearchPane2 searchPane2;
	
	public TeacherManageSystem(JFrame owner,String s)
	{
		super("ѧ���ɼ�����");
		contentPane	= getContentPane();
		contentPane.setLayout(new BorderLayout());
		//setTitle("ѧ���ɼ�����ϵͳ");

		/*��Ӳ˵����Ͳ˵���*/
		
		setJMenuBar(myMenuBar);
		searchModel  = new JMenu("¼�����");
		JRadioButtonMenuItem global = new JRadioButtonMenuItem("һ����ѯ����");
		//JRadioButtonMenuItem local  = new JRadioButtonMenuItem("�ֲ���ѯ����");
		global.setSelected(true);
		searchModel.add(global);
		//searchModel.add(local);
		global.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int i;
				for(i = 0;i<searchModel.getItemCount();i++)
					searchModel.getItem(i).setSelected(false);
				((JRadioButtonMenuItem)e.getSource()).setSelected(true);
				CardLayout cl = (CardLayout)(searchPane2.getLayout());
				cl.show(searchPane2, e.getActionCommand());
			}
		});
		
		aboutSystem = new JMenu("����ϵͳ");
		myMenuBar.add(searchModel);
		//myMenuBar.add(aboutSystem);

		/*��ӱ������*/
		searchPane2 = new SearchPane2(this,s);
		contentPane.add(searchPane2,BorderLayout.SOUTH);
		
		setSize(510,300);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		int x = (width-getWidth())/2;
		int y = (height-getHeight())/2;
		setLocation(x,y);
		setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//����仰���رգ���ر�����һ��������ʱ����һ��������Ҳ����֮�ر�
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
	}

}