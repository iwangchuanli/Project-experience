/*
*@author ougaoyan ,date:2008-9-27
*/
package com.system.jiemian;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.system.model.Student;
import com.system.model.Teacher;
public class LoginJframe extends JFrame {    // ��¼����
private static final long serialVersionUID = 8086983321056040840L;
private JPasswordField password; //���������
private JTextField username;     //�û��������
private JButton studentLogin;    //ѧ����¼��ť
private JButton teacherLogin; 
private JButton reset;          //���ð�ť

    public LoginJframe() { //��¼���湹����
     super();
     final BorderLayout borderLayout = new BorderLayout();
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     borderLayout.setVgap(10);
     getContentPane().setLayout(borderLayout);
     setTitle("ѧ���ɼ������ѯϵͳ");
     setBounds(400, 250, 285, 194);
     final JPanel panel = new JPanel();     //ͼƬ��ʾ����
     panel.setLayout(new BorderLayout());
     panel.setBorder(new EmptyBorder(0, 0, 0, 0));
     getContentPane().add(panel);
     final JPanel panel_2 = new JPanel();         //��¼����
     final GridLayout gridLayout = new GridLayout(0, 2);
     gridLayout.setHgap(5);
     gridLayout.setVgap(20);
     panel_2.setLayout(gridLayout);
     panel.add(panel_2);
     final JLabel label = new JLabel();    //�û�����ǩ
     label.setHorizontalAlignment(SwingConstants.CENTER);
     label.setPreferredSize(new Dimension(0, 0));
     label.setMinimumSize(new Dimension(0, 0));
     panel_2.add(label);
     label.setText("�� �� ����");
     username = new JTextField(20);
     username.setText("01");               //�����ô�����
     username.setPreferredSize(new Dimension(0, 0));
     panel_2.add(username);
     final JLabel label_1 = new JLabel();   //�����ǩ
     label_1.setHorizontalAlignment(SwingConstants.CENTER);
     panel_2.add(label_1);
     label_1.setText("��      �룺");
     password = new JPasswordField(20);
     password.setText("01");           //�����ô�����
     password.setEchoChar('*');//���������Ļ����ַ�
     panel_2.add(password);
     final JPanel panel_1 = new JPanel();
     panel.add(panel_1, BorderLayout.SOUTH);
     studentLogin=new JButton();
     studentLogin.addActionListener(new StudentLoginButtonAction());
     studentLogin.setText("ѧ����¼"); //����ѧ����¼��ť
     panel_1.add(studentLogin);

     teacherLogin = new JButton();
     teacherLogin.setText("��ʦ��¼");   //�����ʦ��¼��ť
     panel_1.add(teacherLogin);
     teacherLogin.addActionListener(new TeacherLoginButtonAction());

     reset=new JButton();
     reset.addActionListener(new ResetAction());
     reset.setText("����");
     panel_1.add(reset);
     final JLabel tupianLabel = new JLabel();   //ͼƬ��ǩ
     ImageIcon loginIcon=new ImageIcon("res/login.gif");
     tupianLabel.setIcon(loginIcon);
     tupianLabel.setOpaque(true);
     tupianLabel.setBackground(Color.GREEN);
     tupianLabel.setPreferredSize(new Dimension(260, 60));
     panel.add(tupianLabel, BorderLayout.NORTH);

     setVisible(true);
     setResizable(false);
     }
    
    private class ResetAction implements ActionListener {     //���ð�ť�ļ�����
   public void actionPerformed(final ActionEvent e){
    username.setText("");
    password.setText(""); 
   }
}

class TeacherLoginButtonAction implements ActionListener {   //��ʦ��¼��ť�ļ�����
   public void actionPerformed(final ActionEvent e) {
    checkTeacher(username.getText(),password.getText());
    }
   }
class StudentLoginButtonAction implements ActionListener {   //ѧ����¼��ť�ļ�����
   public void actionPerformed(final ActionEvent e) {
     checkStudent(username.getText(),password.getText()); 
    }
   }
    public Student checkStudent(String id,String password){ //���ѧ���ɷ��¼�ķ���
   Student student = new Student(id);
   if(Student.id == null){
    JOptionPane.showMessageDialog(null, "�û���������,����������!");
    username.setText("");
    this.password.setText("");
   }else{
    if(Student.password.equals(password)){
    new StudentMainFrame(); 
    LoginJframe.this.setVisible(false);
    }else{
     JOptionPane.showMessageDialog(null, "���벻��ȷ,����������!"); 
     this.password.setText("");
    }
   }
   return student;
  
}

public Teacher checkTeacher(String id,String password){ //����ʦ�û��ɷ��¼�ķ���
   Teacher teacher = new Teacher(id);
   if( teacher.id == null){
    JOptionPane.showMessageDialog(null, "�û���������,����������!");
    username.setText("");
    this.password.setText("");
   }else{
    if( teacher.password.equals(password)){
    new TeacherMainFrame(); 
    LoginJframe.this.setVisible(false);
    }else{
     JOptionPane.showMessageDialog(null, "���벻��ȷ,����������!");
     this.password.setText("");
    }
   }
   return teacher;
}
}
