package Dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import javax.swing.JTextField;

import DataBase.DBcon;
/*---------�������д�����û������еĽ�ʦ����ģ��-----------------��*/
@SuppressWarnings("serial")
public class TeacherDialog extends JDialog implements ActionListener{
	private JTextField jtfTeacherId;
	private JTextField jtfTeachername;
	private JTextField jtfTeacherPassword;
	private JTextField jtfSexy;
	private JTextField jtfClass;
	
	JButton Find;
	JButton Save;
	JButton Reset;
	JButton Cancle;
	public  TeacherDialog(JFrame owner ){
		super(owner, "��ʦ��Ϣ�޸�");
		this.setSize(300, 200);
		this.setModal(true);//modal - ָ�� dialog �Ƿ���ֹ����ʾ��ʱ������������������,��ģʽ����ζ�Ÿô��ڴ�ʱ�������ڶ���������
		JPanel jPanel1 = new JPanel();
		jtfTeacherId=new JTextField(10);
		jtfTeachername = new JTextField(10);
		jtfTeacherPassword = new JTextField(10);
		jtfSexy = new JTextField(10);
		jtfClass = new JTextField(10);
		jPanel1.add(new JLabel("���ţ�"));
		jPanel1.add(jtfTeacherId);
		jPanel1.add(new JLabel("������"));
		jPanel1.add(jtfTeachername);
		jPanel1.add(new JLabel("���룺"));
		jPanel1.add(jtfTeacherPassword);
		jPanel1.add(new JLabel("�Ա�"));
		jPanel1.add(jtfSexy);
		jPanel1.add(new JLabel("�ν̰༶��"));
		jPanel1.add(jtfClass);
		jPanel1.setLayout(new GridLayout(6, 2));
		getContentPane().add(jPanel1);


		JPanel jPanel2 = new JPanel();
		Find = new JButton("��ѯ");
		Save = new JButton("�ύ");
		Reset = new JButton("����");
		Cancle = new JButton("ȡ��");
		jPanel2.add(Find);
		jPanel2.add(Save);
		jPanel2.add(Reset);		
		jPanel2.add(Cancle);		
		getContentPane().add(jPanel2, BorderLayout.SOUTH);
		
		//�¼�����
		Find.addActionListener(this);
		Save.addActionListener(this);
		Reset.addActionListener(this);
		Cancle.addActionListener(this);
		
		jtfTeachername.setEditable(false);
		jtfTeacherPassword.setEditable(false);
		jtfSexy.setEditable(false);
		jtfClass.setEditable(false);
		setBounds(500,300,300,200);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Find){
			jtfTeachername.setEditable(true);
			jtfTeacherPassword.setEditable(true);
			jtfSexy.setEditable(true);
			jtfClass.setEditable(true);
			try{
				   Class.forName(DBcon.DBDRIVER);
		            }
		       catch(ClassNotFoundException a){
		   	       System.out.println(""+a.getMessage());
			       }
		        try{ 
		   	     Connection con;
		   	     String id=jtfTeacherId.getText().trim();
		         con=DriverManager.getConnection(DBcon.DBURL,DBcon.DBUSER,DBcon.DBPASS);
		         Statement sql=con.createStatement();
		         String s="SELECT username,userpwd,sexy,classgrade from users where userid='"+id+"'";
		         ResultSet rs=sql.executeQuery(s);
		         rs.next();
		         String name = rs.getString("username").trim();
		         String pwd = rs.getString("userpwd").trim();
		         //String type = rs.getString("usertype").trim();
		         String sexy = rs.getString("sexy").trim();
		         String fclass = rs.getString("classgrade").trim();
		         jtfTeachername.setText(name);
		         jtfTeacherPassword.setText(pwd);
		         jtfSexy.setText(sexy);
		         jtfClass.setText(fclass);
		         sql.close(); 
		         rs.close();
		         con.close();
		           
		        }
		        catch(SQLException a)
		        {
		        	System.out.println(a.getMessage());
		        }
			
		}else if(e.getSource()==Save){
			try{
				 Class.forName(DBcon.DBDRIVER);
		          }
		       catch(ClassNotFoundException a){
		           System.out.println(""+a.getMessage());
		          }
		       try{ 
		           Connection con;
		           String id=jtfTeacherId.getText().trim();
		           String name=jtfTeachername.getText().trim();
		           String pwd=jtfTeacherPassword.getText().trim();
		           String sexy1 = jtfSexy.getText().trim();
		           String fclass1 = jtfClass.getText().trim();

		           con=DriverManager.getConnection(DBcon.DBURL,DBcon.DBUSER,DBcon.DBPASS);
		            Statement sql=con.createStatement();
		           String s="UPDATE users SET username='"+name+"',userpwd='"+pwd+"',sexy='"+sexy1+"',classgrade='"+fclass1+"' where userid='"+id+"'";
		           sql.executeUpdate(s);
		           sql.close();
		           con.close();
		              JOptionPane.showMessageDialog(null, "�޸���Ϣ�ɹ�", "Welcome", JOptionPane.YES_NO_OPTION);
		             }
		          catch(SQLException a)
		         {
		        	  System.out.println(a.getMessage());
		         }
			   
		}else if(e.getSource()==Reset){
			jtfTeachername.setText("");
			jtfTeacherPassword.setText("");
			jtfSexy.setText("");
			jtfClass.setText("");
		}else if(e.getSource()==Cancle){
			this.dispose();
		}
	}
}
