package main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

import DataBase.DBcon;
import Dialog.AddStudentDialog;
import Dialog.QueryStudentScore;
import Dialog.Query1StudentScore;
import Dialog.Query2StudentScore;
import Dialog.QueryStudentDialog;
import Dialog.TeacherDialog;
import Dialog.Update1PassDilog;
import Dialog.UpdateStudentDialog;
import StudentManageSystem.StudentManageSystem1;

@SuppressWarnings("serial")
public class StudentFrame extends JFrame implements ActionListener{
	JMenuBar  menubar = new JMenuBar();

	JMenu   gradeMenu = new JMenu("�ɼ���ѯ");
	JMenuItem query1MenuItem = new JMenuItem("����ѧ�Ż���������ѯ");
	JMenuItem query3MenuItem = new JMenuItem("���ݿ�Ŀ��ѯ");
	JMenuItem query2MenuItem = new JMenuItem("����ѧ�ڲ�ѯ");
	
	JMenu   studentMenu = new JMenu("��Ϣ����");
	JMenuItem queryStudentMenuItem = new JMenuItem("��Ϣ�޸�");
	
	JMenu   otherMenu = new JMenu("����");
	JMenuItem otherFirstMenuItem = new JMenuItem("�޸�����");
	JMenuItem otherSecondMenuItem = new JMenuItem("�˳�ϵͳ");
	
	JMenu   aboutSystemMenu = new JMenu("����ϵͳ");
	JMenuItem aboutMenuItem = new JMenuItem("����");
	JMenuItem helpMenuItem = new JMenuItem("����");
	
	JLabel  welcomeLabel = new JLabel("ѧ���ɼ�����ϵͳ");
	
	TeacherDialog teacherDialog;
	QueryStudentScore course;
	Query1StudentScore course1;
	Query2StudentScore course2;
	QueryStudentDialog queryStudentDialog;
	UpdateStudentDialog updateStudentDialog;
	Update1PassDilog updatePassword;
	AddStudentDialog addStudentDialog;
	StudentManageSystem1 localManage;
	
	JPanel panel =new JPanel();
	JLabel useridT,usernameT,sexT,GradeT;
	JLabel useridY,usernameY,sexY,GradeY;
	Container c;
	
	public StudentFrame(String s) throws Exception{
		this.setTitle("ѧ���ɼ�����ϵͳ---ѧ������");
		c=getContentPane();
		c.setLayout(new BorderLayout());
		
		panel.setLayout(new GridLayout(2,2,0,0));
		useridT=new JLabel("ѧ��:");
		usernameT=new JLabel("����:");
		sexT=new JLabel("�Ա�:");
		GradeT=new JLabel("�꼶:");
		DBcon db = new DBcon();
		ResultSet rs;
		rs = db.query("select * from users where userid='"+s+"'");
		rs.next();
		String username = rs.getString("username");
		String sex = rs.getString("sexy");
		String grade = rs.getString("classgrade");
		
		
		useridY=new JLabel(s);
		usernameY=new JLabel(username);
		sexY=new JLabel(sex);
		GradeY=new JLabel(grade);
		
		
		useridT.setFont(new java.awt.Font("DialogInput", 1, 20));
		usernameT.setFont(new java.awt.Font("DialogInput", 1, 20));
		sexT.setFont(new java.awt.Font("DialogInput", 1, 20));
		GradeT.setFont(new java.awt.Font("DialogInput", 1, 20));
		useridY.setFont(new java.awt.Font("DialogInput", 1, 20));
		usernameY.setFont(new java.awt.Font("DialogInput", 1, 20));
		sexY.setFont(new java.awt.Font("DialogInput", 1, 20));
		GradeY.setFont(new java.awt.Font("DialogInput", 1, 20));
		panel.add(useridT);
		panel.add(useridY);
		panel.add(usernameT);
		panel.add(usernameY);
		panel.add(sexT);
		panel.add(sexY);
		panel.add(GradeT);
		panel.add(GradeY);
		this.getContentPane().add(panel);
	
		//���컶ӭҳ��
		welcomeLabel.setFont(new java.awt.Font("DialogInput", 1, 48));
		welcomeLabel.setForeground(Color.blue);
		JPanel panel1=new JPanel();
		panel1.add(welcomeLabel);
		c.add(panel1,BorderLayout.SOUTH);
		c.add(panel,BorderLayout.CENTER);
		
		//�������˵�
		this.setJMenuBar(menubar);
		menubar.add(gradeMenu);
		menubar.add(studentMenu);
		menubar.add(otherMenu);
		menubar.add(aboutSystemMenu);

		//����ɼ���ѯ�˵�
		gradeMenu.add(query1MenuItem);
		gradeMenu.add(query3MenuItem);
		gradeMenu.add(query2MenuItem);
		//����ѧ������˵�
		studentMenu.add(queryStudentMenuItem);
		//���������˵�
		otherMenu.add(otherFirstMenuItem);
		otherMenu.add(otherSecondMenuItem);
		
		aboutSystemMenu.add(aboutMenuItem);
		aboutSystemMenu.add(helpMenuItem);

		//Ϊ���˵�ע�������
		query1MenuItem.addActionListener(this);
		query2MenuItem.addActionListener(this);
		query3MenuItem.addActionListener(this);
		queryStudentMenuItem.addActionListener(this);
		
		otherFirstMenuItem.addActionListener(this);
		otherSecondMenuItem.addActionListener(this);
		aboutMenuItem.addActionListener(this);
		helpMenuItem.addActionListener(this);
			
		//��ʾϵͳ������
		this.setSize(400, 240);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==query1MenuItem){
			//������ѧ�Ų�ѯ
			localManage = new StudentManageSystem1(this);
			//
		}else if(e.getSource()==query3MenuItem){
			//����Ŀ��ѯ
			course1 = new Query1StudentScore(this); 
		}else if(e.getSource()==query2MenuItem){
			//��ѧ�ڲ�ѯ
			course2 = new Query2StudentScore(this);
		}else if(e.getSource()==queryStudentMenuItem){//ѧ����ѯ
			//����ѧ����ѯ����
			queryStudentDialog = new QueryStudentDialog(this);
		}else if(e.getSource()==otherFirstMenuItem){//ѧ����ѯ
			
			updatePassword =new Update1PassDilog(this);
		}else if(e.getSource()==otherSecondMenuItem){
			
			if(JOptionPane.showConfirmDialog(this, "ȷ��Ҫ�˳�ϵͳ��","�˳�",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				System.exit(0);
			}
		}else if(e.getSource()==aboutMenuItem){//����
			//�����޸��������
			JOptionPane.showMessageDialog(null,"���ڣ�ѧ���ɼ�����ϵͳ Version 1.0\nϵͳ���������ߣ�\n��ɽѧԺ\n��Ϣ����ѧԺ\n15�����2��\n������\nѧ�ţ�21506031058");
		}else if(e.getSource()==helpMenuItem){//����
			//�����޸��������
			JOptionPane.showMessageDialog(null,
					"��ϵͳѧ���˵ĺ��Ĺ��ܣ�\n" +
					"�����Լ���ѧ�Ż���������ѯ�Լ��ĳɼ������а�����\n" +
					"���Ƶĳɼ���ƽ���֡��༶�����ȣ�\n" + 
					"                   ��лʹ�ñ�ϵͳ��"
					);
		}
		
	}
}
