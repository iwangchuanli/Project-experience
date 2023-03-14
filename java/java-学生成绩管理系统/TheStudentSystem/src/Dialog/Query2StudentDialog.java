package Dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DataBase.DBcon;
import Users.User;
/*---------�������д���ǿγ̹����еĿ���Ŀ¼ģ��-----------------��*/
@SuppressWarnings("serial")
public class Query2StudentDialog extends JDialog implements ActionListener{
	private JButton jbtCancel;
	private JButton jbtInput;
	private JTable jTable1;
	String length;
	int i=0;
	public ArrayList<User> list=new ArrayList<User>();
	public Query2StudentDialog(JFrame owner){
		super(owner,"ѧ����Ϣ��ѯ");
		this.setSize(350, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);
		/*--------------------�����忪ʼ��ѯ------------------*/
		JPanel jPanel1 = new JPanel();
		jbtInput = new JButton("һ����ѯ");
		jPanel1.add(jbtInput);
		getContentPane().add(jPanel1, BorderLayout.NORTH);
		
		/*--------------------��Ӱ�ť���------------------*/
		JPanel jPanel2 = new JPanel();
		jbtCancel = new JButton("�˳�");
		jPanel2.add(jbtCancel);		
		getContentPane().add(jPanel2, BorderLayout.SOUTH);
/*--------------------���������еı��------------------*/
		TableModel jTable1Model = new DefaultTableModel(new String[0][0] ,	new String[] {  "ѧ��","����","�Ա�","�༶" });
		jTable1 = new JTable();		
		jTable1.setModel(jTable1Model);
		JScrollPane jScrollPane1 = new JScrollPane(jTable1);
		getContentPane().add(jScrollPane1, BorderLayout.CENTER);	
		
		/*--------------------��Ӽ�����ť------------------*/
		jbtInput.addActionListener(this);
		jbtCancel.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbtInput){
			 try{
				   Class.forName(DBcon.DBDRIVER);
			      }
			    catch(ClassNotFoundException a){
			   	 System.out.println(""+a.getMessage());
					
				  }
			   try{ 
				   /*----------��δ����Ǵ����ݿ��н������ѧ����Ϣȡ����----------*/
			   	     Connection con;
			   	     String no="";
					 String name="";
					 String sexy="";
					 String classgrade="";
			         con=DriverManager.getConnection(DBcon.DBURL,DBcon.DBUSER,DBcon.DBPASS);
			         Statement sql=con.createStatement();
			         String sq="select count(*) from users where usertype='ѧ��'";
			         String q="SELECT userid,username,sexy,classgrade from users where usertype='ѧ��'";
			         ResultSet rs=sql.executeQuery(sq);
			         while(rs.next()){
			        	  length=rs.getString(1);
			         }
			         rs.close();
			         
			         /*��δ����ǽ�ȡ������ֵ���ΰ���˳�����*/
			         ResultSet rs1=sql.executeQuery(q);
			         User []user=new User[Integer.parseInt(length)];
			         while(rs1.next()){
			        	no=rs1.getString(1);
			        	name=rs1.getString(2);
			        	sexy=rs1.getString(3);
			        	classgrade=rs1.getString(4);
			        	user[i]=new User(no, name, sexy,classgrade,null,null,null);
			        	list.add(user[i]);			        	
			        }			     
			        DefaultTableModel tm = new DefaultTableModel(new String[0][0] ,	new String[] { "ѧ��","����","�Ա�","�༶" });
					jTable1.setModel(tm);
			         
			     	for(User  s : list){
			     		tm.addRow(new String[]{s.getUserNo(),s.getName(),s.getSexy(),s.getClassGrade()});
			     	}
					sql.close();
				    con.close();
			   }
			    catch(SQLException a)
			    {
			    	System.out.println(a.getMessage());
			    }
		}
		else if(e.getSource()==jbtCancel){
			this.dispose();
		}
	}

}
