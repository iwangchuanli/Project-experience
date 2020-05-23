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
public class LoginJframe extends JFrame {    // 登录界面
private static final long serialVersionUID = 8086983321056040840L;
private JPasswordField password; //密码输入框
private JTextField username;     //用户名输入框
private JButton studentLogin;    //学生登录按钮
private JButton teacherLogin; 
private JButton reset;          //重置按钮

    public LoginJframe() { //登录界面构造器
     super();
     final BorderLayout borderLayout = new BorderLayout();
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     borderLayout.setVgap(10);
     getContentPane().setLayout(borderLayout);
     setTitle("学生成绩管理查询系统");
     setBounds(400, 250, 285, 194);
     final JPanel panel = new JPanel();     //图片显示区域
     panel.setLayout(new BorderLayout());
     panel.setBorder(new EmptyBorder(0, 0, 0, 0));
     getContentPane().add(panel);
     final JPanel panel_2 = new JPanel();         //登录区域
     final GridLayout gridLayout = new GridLayout(0, 2);
     gridLayout.setHgap(5);
     gridLayout.setVgap(20);
     panel_2.setLayout(gridLayout);
     panel.add(panel_2);
     final JLabel label = new JLabel();    //用户名标签
     label.setHorizontalAlignment(SwingConstants.CENTER);
     label.setPreferredSize(new Dimension(0, 0));
     label.setMinimumSize(new Dimension(0, 0));
     panel_2.add(label);
     label.setText("用 户 名：");
     username = new JTextField(20);
     username.setText("01");               //测试用代码行
     username.setPreferredSize(new Dimension(0, 0));
     panel_2.add(username);
     final JLabel label_1 = new JLabel();   //密码标签
     label_1.setHorizontalAlignment(SwingConstants.CENTER);
     panel_2.add(label_1);
     label_1.setText("密      码：");
     password = new JPasswordField(20);
     password.setText("01");           //测试用代码行
     password.setEchoChar('*');//设置密码框的回显字符
     panel_2.add(password);
     final JPanel panel_1 = new JPanel();
     panel.add(panel_1, BorderLayout.SOUTH);
     studentLogin=new JButton();
     studentLogin.addActionListener(new StudentLoginButtonAction());
     studentLogin.setText("学生登录"); //定义学生登录按钮
     panel_1.add(studentLogin);

     teacherLogin = new JButton();
     teacherLogin.setText("教师登录");   //定义教师登录按钮
     panel_1.add(teacherLogin);
     teacherLogin.addActionListener(new TeacherLoginButtonAction());

     reset=new JButton();
     reset.addActionListener(new ResetAction());
     reset.setText("重置");
     panel_1.add(reset);
     final JLabel tupianLabel = new JLabel();   //图片标签
     ImageIcon loginIcon=new ImageIcon("res/login.gif");
     tupianLabel.setIcon(loginIcon);
     tupianLabel.setOpaque(true);
     tupianLabel.setBackground(Color.GREEN);
     tupianLabel.setPreferredSize(new Dimension(260, 60));
     panel.add(tupianLabel, BorderLayout.NORTH);

     setVisible(true);
     setResizable(false);
     }
    
    private class ResetAction implements ActionListener {     //重置按钮的监听器
   public void actionPerformed(final ActionEvent e){
    username.setText("");
    password.setText(""); 
   }
}

class TeacherLoginButtonAction implements ActionListener {   //教师登录按钮的监听器
   public void actionPerformed(final ActionEvent e) {
    checkTeacher(username.getText(),password.getText());
    }
   }
class StudentLoginButtonAction implements ActionListener {   //学生登录按钮的监听器
   public void actionPerformed(final ActionEvent e) {
     checkStudent(username.getText(),password.getText()); 
    }
   }
    public Student checkStudent(String id,String password){ //检测学生可否登录的方法
   Student student = new Student(id);
   if(Student.id == null){
    JOptionPane.showMessageDialog(null, "用户名不存在,请重新输入!");
    username.setText("");
    this.password.setText("");
   }else{
    if(Student.password.equals(password)){
    new StudentMainFrame(); 
    LoginJframe.this.setVisible(false);
    }else{
     JOptionPane.showMessageDialog(null, "密码不正确,请重新输入!"); 
     this.password.setText("");
    }
   }
   return student;
  
}

public Teacher checkTeacher(String id,String password){ //检测教师用户可否登录的方法
   Teacher teacher = new Teacher(id);
   if( teacher.id == null){
    JOptionPane.showMessageDialog(null, "用户名不存在,请重新输入!");
    username.setText("");
    this.password.setText("");
   }else{
    if( teacher.password.equals(password)){
    new TeacherMainFrame(); 
    LoginJframe.this.setVisible(false);
    }else{
     JOptionPane.showMessageDialog(null, "密码不正确,请重新输入!");
     this.password.setText("");
    }
   }
   return teacher;
}
}
