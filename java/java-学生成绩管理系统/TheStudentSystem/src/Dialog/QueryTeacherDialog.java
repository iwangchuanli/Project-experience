package Dialog;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DataBase.DBcon;
/*---------�������д����ѧ�������е�ѧ����Ϣ��ѯģ��-----------------��*/
@SuppressWarnings("serial")
public class QueryTeacherDialog extends JDialog implements ActionListener{
	private JTable jTable1;
	public  JButton query1,exit;
	TextField t1;
	public JPanel p1,p2; 
	public QueryTeacherDialog(JFrame owner)
	{
		super(owner,"��ʦ��Ϣ��ѯ");
		t1=new TextField(10);
		query1=new JButton("��ѯ");
		exit=new JButton("�˳�");
		this.setModal(true);
		/*������ѯ������*/
		TableModel jTable1Model = new DefaultTableModel(new String[0][0] ,	new String[] { "����", "����","�Ա�","�ν̰༶" });
		jTable1 = new JTable();
		jTable1.setModel(jTable1Model);
		JScrollPane jScrollPane1 = new JScrollPane(jTable1);
		
		/*������ť���*/
		p1=new JPanel();
		p1.add(new JLabel("���ţ�"));
		p2=new JPanel();
		p1.add(t1);
		p1.add(query1);
		p1.add(exit);
		query1.addActionListener(this);
		exit.addActionListener(this);
		add(p1,BorderLayout.NORTH);
		getContentPane().add(jScrollPane1, BorderLayout.CENTER);
		setBounds(500,300,400,300);
		setVisible(true);
	}
 public void actionPerformed(ActionEvent e) {
	DefaultTableModel tm = new DefaultTableModel(new String[0][0] ,	new String[] { "����", "����","�Ա�","�ν̰༶" });
	jTable1.setModel(tm);
		if(e.getSource()==query1)
		{
		    try{
			   Class.forName(DBcon.DBDRIVER);
		    }
		    catch(ClassNotFoundException a){
		   	 System.out.println(""+a.getMessage());
			}
		    if(t1.getText()==null){
		    	JOptionPane.showMessageDialog(null, "���Ų���Ϊ�գ�");
		    }else{
		    	//JOptionPane.showMessageDialog(null, "���Ų���Ϊ�գ�");
		    	try{ 
			   	     Connection con;
			         String id=t1.getText();
			         String no="";
					 String name="";
					 String sexy="";
					 String classgrade="";
					 con=DriverManager.getConnection(DBcon.DBURL,DBcon.DBUSER,DBcon.DBPASS);
					 Statement sql=con.createStatement();
					 String s="select * from users where userid='"+id+"' and usertype='��ʦ'";
					 ResultSet rs=sql.executeQuery(s);
					 rs.next();
					 no=rs.getString("userid").trim();
					 name = rs.getString("username").trim();
					 sexy = rs.getString("sexy").trim();
					 classgrade = rs.getString("classgrade").trim();
					 tm.addRow(new String[]{no,name,sexy,classgrade});
					 rs.close();
					 con.close(); 
					   }
					catch(SQLException a)
					{
						JOptionPane.showMessageDialog(null, "�û������ڣ�");
						System.out.println(a.getMessage());
					}
		    }
		}
		else if(e.getSource()==exit){
			this.dispose();
		}
		  
}
}

	

