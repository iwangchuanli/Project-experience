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
public class DeleteStudentDialog extends JDialog implements ActionListener{
	private JTable jTable1;
	public  JButton query1,delete;
	TextField t1;
	public JPanel p1,p2; 
	public DeleteStudentDialog(JFrame owner)
	{
		super(owner,"ɾ��ѧ����Ϣ");
		t1=new TextField(10);
		query1=new JButton("��ѯ");
		delete=new JButton("ɾ��");
		this.setModal(true);
		/*������ѯ������*/
		TableModel jTable1Model = new DefaultTableModel(new String[0][0] ,	new String[] { "ѧ��", "����","�Ա�","�༶" });
		jTable1 = new JTable();
		jTable1.setModel(jTable1Model);
		JScrollPane jScrollPane1 = new JScrollPane(jTable1);
		
		/*������ť���*/
		p1=new JPanel();
		p1.add(new JLabel("ѧ�ţ�"));
		p2=new JPanel();
		p1.add(t1);
		p1.add(query1);
		p1.add(delete);
		query1.addActionListener(this);
		delete.addActionListener(this);
		add(p1,BorderLayout.NORTH);
		getContentPane().add(jScrollPane1, BorderLayout.CENTER);
		setBounds(500,300,400,300);
		setVisible(true);
	}
 public void actionPerformed(ActionEvent e) {
	DefaultTableModel tm = new DefaultTableModel(new String[0][0] ,	new String[] { "ѧ��", "����","�Ա�","�༶" });
	jTable1.setModel(tm);
		if(e.getSource()==query1)
		{
		    try{
			   Class.forName(DBcon.DBDRIVER);
		    }
		    catch(ClassNotFoundException a){
		   	 System.out.println(""+a.getMessage());
			}
		   try{ 
	   	     Connection con;
	         String id=t1.getText();
	         String no="";
			 String name="";
			 String sexy="";
			 String tclass="";
			 con=DriverManager.getConnection(DBcon.DBURL,DBcon.DBUSER,DBcon.DBPASS);
			 Statement sql=con.createStatement();
			 String s="select * from users where userid='"+id+"' and usertype='ѧ��'";
			 ResultSet rs=sql.executeQuery(s);
			 rs.next();
			 no=rs.getString("userid").trim();
			 name = rs.getString("username").trim();
			 sexy = rs.getString("sexy").trim();
			 tclass = rs.getString("classgrade").trim();
			 tm.addRow(new String[]{no,name,sexy,tclass});
			 rs.close();
			 con.close(); 
			   }
			catch(SQLException a)
			{
				JOptionPane.showMessageDialog(null, "�û������ڣ�");
				System.out.println(a.getMessage());
			}
		}
		else if(e.getSource()==delete){
			try{
				   Class.forName(DBcon.DBDRIVER);
			    }
			    catch(ClassNotFoundException a){
			   	 System.out.println(""+a.getMessage());
				}
			   try{ 
		   	     Connection con;
		         String id=t1.getText();
				 con=DriverManager.getConnection(DBcon.DBURL,DBcon.DBUSER,DBcon.DBPASS);
				 Statement sql=con.createStatement();
				 String s="delete from users where userid='"+id+"'";
				 String str="delete from studentmanager where ID='"+id+"'";
				 sql.execute(s);
				 sql.execute(str);
				 JOptionPane.showMessageDialog(null, "ѧ����Ϣɾ���ɹ���");
				 sql.close();
				 con.close(); 
				   }
				catch(SQLException a)
				{
					System.out.println(a.getMessage());
				}
			//this.dispose();
		}
		  
}
}