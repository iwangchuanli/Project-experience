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
/*---------�������д����ѧ�������е�ѧ����Ϣ���ģ��-----------------��*/
@SuppressWarnings("serial")
public class AddTeacherDialog extends JDialog implements ActionListener{
	//��������
	private JTextField jtfStudentId;
	private JTextField jtfStudentName;
	private JPasswordField jtfStudentPassword;
	private JTextField jtfSexy;
	private JTextField jtfClass;
	private JTextField jtfStudentType;
	JButton Save;
	JButton Reset;
	JButton Cancle;
	
	//���췽��
	public AddTeacherDialog(JFrame owner){
		super(owner,"��ʦ��Ϣ���");
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);
		//��������
		JPanel jPanel1 = new JPanel();
		jtfStudentId=new JTextField(10);
		jtfStudentName = new JTextField(10);
		jtfStudentPassword = new JPasswordField(10);
		jtfSexy = new JTextField(10);
		jtfClass = new JTextField(10);
		jtfStudentType = new JTextField(10);
		jtfStudentType.setText("��ʦ");
		jPanel1.add(new JLabel("���ţ�"));
		jPanel1.add(jtfStudentId);
		jPanel1.add(new JLabel("������"));
		jPanel1.add(jtfStudentName);
		jPanel1.add(new JLabel("���룺"));
		jPanel1.add(jtfStudentPassword);
		jPanel1.add(new JLabel("�Ա�"));
		jPanel1.add(jtfSexy);
		jPanel1.add(new JLabel("�ν̰༶��"));
		jPanel1.add(jtfClass);
		jPanel1.add(new JLabel("���ͣ�"));
		jPanel1.add(jtfStudentType);
		jtfStudentType.setEditable(false);
		jPanel1.setLayout(new GridLayout(6, 2));
		getContentPane().add(jPanel1);
		//������ť���
		JPanel jPanel2 = new JPanel();
		Save = new JButton("�ύ");
		Reset = new JButton("����");
		Cancle = new JButton("ȡ��");
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Save){
			try{
				Class.forName(DBcon.DBDRIVER);
			}catch(ClassNotFoundException a){
				 System.out.println(""+a.getMessage());
			}try{
				Connection con;
				 String id=jtfStudentId.getText().trim();
				 String name=jtfStudentName.getText().trim();
		         @SuppressWarnings("deprecation")
				String password=jtfStudentPassword.getText().trim();
		         String sexy = jtfSexy.getText().trim();
		         String fclass = jtfClass.getText().trim();
		         String type=jtfStudentType.getText().trim();
		         con=DriverManager.getConnection(DBcon.DBURL,DBcon.DBUSER,DBcon.DBPASS);
		         Statement sql=con.createStatement();
		         String s="INSERT INTO users VALUES('"+id+"','"+name+"','"+password+"','"+sexy+"','"+fclass+"','"+type+"')";
		         //String str="INSERT INTO studentmanager VALUES('"+id+"','"+name+"')";
		         sql.executeUpdate(s);
		         sql.close();
		         con.close();
		         JOptionPane.showMessageDialog(null, "��ʦ��ӳɹ�");
			}
			 catch(SQLException a){
				System.out.println(a.getMessage());		
			}
		}else if(e.getSource()==Reset){
			jtfStudentId.setText("");
			jtfStudentName.setText("");
			jtfStudentPassword.setText("");
			jtfStudentType.setText("��ʦ");
			
		}else if(e.getSource()==Cancle){
			this.dispose();
		}
		
	}

}

