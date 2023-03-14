package Dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DataBase.DBcon;
/*---------�������д�����û������е������޸�ģ��-----------------��*/
@SuppressWarnings("serial")
public class UpdateInputPower extends JDialog implements ActionListener{
	private JTextField jtfStudentID;
	private JPasswordField jtfStudentOldpassword;
	private JTextField jtfType;
	JButton Save;
	JButton Reset;
	JButton Cancle;
	public UpdateInputPower(JFrame owner){
		super(owner,"Ȩ���޸�");
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);
		//��������
		JPanel jPanel1 = new JPanel();
		jtfStudentID=new JTextField(10);
		jtfStudentOldpassword = new JPasswordField(10);
		jtfType = new JTextField(10);
		jPanel1.add(new JLabel("��ʦ�˺ţ�"));
		jPanel1.add(jtfStudentID);
		jPanel1.add(new JLabel("���룺"));
		jPanel1.add(jtfStudentOldpassword);
		jPanel1.add(new JLabel("Ȩ���޸�Ϊ��"));
		jPanel1.add(jtfType);
		jPanel1.setLayout(new GridLayout(4, 2));
		getContentPane().add(jPanel1);
		//������ť���
		JPanel jPanel2 = new JPanel();
		Save = new JButton("ȷ��");
		Reset = new JButton("����");
		Cancle = new JButton("�˳�");
		jPanel2.add(Save);
		jPanel2.add(Reset);		
		jPanel2.add(Cancle);
		getContentPane().add(jPanel1);
		getContentPane().add(jPanel2, BorderLayout.SOUTH);
		
		//��Ӽ�����ť
		Save.addActionListener(this);
		Reset.addActionListener(this);
		Cancle.addActionListener(this);
		this.setVisible(true);
}
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if(jtfStudentID.getText()==null&&jtfStudentOldpassword.getText()==null&&jtfType.getText()==null)
		{
			JOptionPane.showMessageDialog(null, "�Բ������ݲ���Ϊ��", "Welcome", JOptionPane.YES_NO_OPTION);
		}
		else{
			if(e.getSource()==Save)
		{
					try{
						 Class.forName(DBcon.DBDRIVER);
				          }
				       catch(ClassNotFoundException a){
				           System.out.println(""+a.getMessage());
				          }
				       try{ 
				           Connection con;
				           String id=jtfStudentID.getText();
				           String pass = jtfStudentOldpassword.getText();
				           String oldtype = jtfType.getText().trim();
				           @SuppressWarnings("unused")
						int newtype = Integer.parseInt(oldtype);
				           con=DriverManager.getConnection(DBcon.DBURL,DBcon.DBUSER,DBcon.DBPASS);
				           Statement sql=con.createStatement();
				           String s="UPDATE users SET type='"+oldtype+"' where userid='"+id+"' and userpwd='"+pass+"'";
				           sql.executeUpdate(s);
				           sql.close();
				           con.close();
				           JOptionPane.showMessageDialog(null, "Ȩ���޸ĳɹ�", "Welcome", JOptionPane.YES_NO_OPTION);
				           }
				          catch(SQLException a)
				         {
				        	  System.out.println(a.getMessage());
				         }
		}
			else if(e.getSource()==Reset){
				jtfStudentID.setText("");
				jtfStudentOldpassword.setText("");
				jtfType.setText("");
			}else if(e.getSource()==Cancle){
				this.dispose();
			}
		}
	}
}
