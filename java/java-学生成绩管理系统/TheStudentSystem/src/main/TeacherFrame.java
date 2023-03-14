package main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

import DataBase.DBcon;
import Dialog.AddStudentDialog;
import Dialog.QueryStudentScore;
import Dialog.QueryStudentDialog;
import Dialog.TeacherDialog;
import Dialog.UpdatePassDilog;
import Dialog.UpdateStudentDialog;
import StudentManageSystem.TeacherManageSystem;

@SuppressWarnings("serial")
public class TeacherFrame extends JFrame implements ActionListener{
	JMenuBar  menubar = new JMenuBar();
	
	JMenu   userMenu = new JMenu("����");
	JMenuItem  passMenuItem = new JMenuItem("�޸�����");
	JMenuItem  exitMenuItem = new JMenuItem("�˳�ϵͳ");
	JMenu   gradeMenu = new JMenu("�ɼ�����");
	JMenuItem inputMenuItem = new JMenuItem("�������");
	JMenu   aboutSystemMenu = new JMenu("����ϵͳ");
	JMenuItem aboutMenuItem = new JMenuItem("����");
	JMenuItem helpMenuItem = new JMenuItem("����");
	JLabel  welcomeLabel = new JLabel("ѧ���ɼ�����ϵͳ");
	
	TeacherDialog teacherDialog;
	QueryStudentScore course;
	QueryStudentDialog queryStudentDialog;
	UpdateStudentDialog updateStudentDialog;
	UpdatePassDilog updatePassword;
	AddStudentDialog addStudentDialog;
	TeacherManageSystem teacherManager;
	
	JPanel panel =new JPanel();
	JLabel useridT,usernameT,sexT,GradeT;
	JLabel useridY,usernameY,sexY,GradeY;
	Container c;
	
	String a;
	public TeacherFrame(String s){
		try{
		a=s;
		this.setTitle("ѧ���ɼ�����ϵͳ---��ʦ����");
		//�������˵�
		this.setJMenuBar(menubar);
		c=getContentPane();
		c.setLayout(new BorderLayout());
	
		menubar.add(gradeMenu);
		menubar.add(userMenu);
		menubar.add(aboutSystemMenu);
		//�����û�����˵�
		userMenu.add(passMenuItem);
		userMenu.add(exitMenuItem);
		//����ɼ�����˵�
		gradeMenu.add(inputMenuItem);
		aboutSystemMenu.add(aboutMenuItem);
		aboutSystemMenu.add(helpMenuItem);
		
		panel.setLayout(new GridLayout(2,2,0,0));
		useridT=new JLabel("ְ����:");
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
		welcomeLabel.setFont(new java.awt.Font("Dialog", 0, 48));
		//welcomeLabel.setHorizontalAlignment(SwingConstants.SOUTH);
		welcomeLabel.setForeground(Color.blue);
		//this.getContentPane().add(welcomeLabel);
		JPanel panel1=new JPanel();
		panel1.add(welcomeLabel);
		c.add(panel1,BorderLayout.SOUTH);
		c.add(panel,BorderLayout.CENTER);
		
		//Ϊ���˵�ע�������
		passMenuItem.addActionListener(this);
		exitMenuItem.addActionListener(this);
		inputMenuItem.addActionListener(this);
		aboutMenuItem.addActionListener(this);
		helpMenuItem.addActionListener(this);
			
		//��ʾϵͳ������
		this.setSize(400, 230);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void actionPerformed(ActionEvent e) {
		//���˵�����
		if(e.getSource()==passMenuItem){//�޸�����
			//��������������
			updatePassword =new UpdatePassDilog(this);
			}	
		else if(e.getSource()==exitMenuItem){//�˳�ϵͳ
			if(JOptionPane.showConfirmDialog(this, "ȷ��Ҫ�˳�ϵͳ��","�˳�",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				System.exit(0);
			}			
		}else if(e.getSource()==inputMenuItem){//¼��ɼ�
			//�����ɼ�¼�����
			teacherManager = new TeacherManageSystem(this,a);
		}else if(e.getSource()==aboutMenuItem){//����
			//�����޸��������
			JOptionPane.showMessageDialog(null,"���ڣ�ѧ���ɼ�����ϵͳ Version 1.0\nϵͳ���������ߣ�\n��ɽѧԺ\n��Ϣ����ѧԺ\n15�����2��\n������\nѧ�ţ�21506031058");
		}else if(e.getSource()==helpMenuItem){//����
			//�����޸��������
			JOptionPane.showMessageDialog(null,
					"��ϵͳ��ʦ�˵ĺ��Ĺ��ܣ�\n" +
					"��ʦ���Ը���ȫ�ֲ�ѯ����ѧ���ĳɼ������а�����\n" +
					"¼��ѧ�����Ƶĳɼ�����ѧ���ɼ�����ȣ�����ʦ¼��\nѧ���ɼ�����Ȼ�����޸�ѧ���ɼ���һ���ύ�򲻿������޸ġ�\n" + 
					"                         ��лʹ�ñ�ϵͳ��"
					);
		}
	}
}
