package StudentManageSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



@SuppressWarnings("serial")
public class StudentManageSystem1 extends JFrame implements ActionListener{
	Container contentPane;
	JMenuBar myMenuBar = new JMenuBar();
	JMenu aboutSystem;
	JMenu searchModel;
	
	SearchPane1 searchPane1;
	
	public StudentManageSystem1(JFrame owner)
	{
		super("ѧ���ɼ���ѯ");
		contentPane	= getContentPane();
		contentPane.setLayout(new BorderLayout());
		//setTitle("ѧ���ɼ�����ϵͳ");

		/*��Ӳ˵����Ͳ˵���*/
		
		setJMenuBar(myMenuBar);
		searchModel  = new JMenu("�����ѯ");
		JRadioButtonMenuItem local  = new JRadioButtonMenuItem("��ѧ��������ѯ");
		searchModel.add(local);
		
		local.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int i;
				for(i = 0;i<searchModel.getItemCount();i++)
					searchModel.getItem(i).setSelected(false);
				((JRadioButtonMenuItem)e.getSource()).setSelected(true);
				CardLayout cl = (CardLayout)(searchPane1.getLayout());
				cl.show(searchPane1, e.getActionCommand());
			}
		});
		
		aboutSystem = new JMenu("����ϵͳ");
		myMenuBar.add(searchModel);
		//myMenuBar.add(aboutSystem);

		/*��ӱ������*/
		searchPane1 = new SearchPane1(this);
		contentPane.add(searchPane1,BorderLayout.SOUTH);
		
		setSize(550,300);
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