/*学生个人信息修改子界面*/
package com.system.jiemian;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import com.system.model.Student;

public class StudentModifyInfoIFrame extends JInternalFrame{
private static final long serialVersionUID = -3850431999574416393L;
     private JLabel nameLabel,idLabel,sexLabel,classLabel,departmentLabel;
        private JTextField nameField,idField,sexField,classField,departmentField;
     private JButton queding,tuichu;
  
  
   public StudentModifyInfoIFrame(){
    super();
    setMaximizable(true);        //使可最大化
    setIconifiable(true);
    setClosable(true);
    setTitle("个人信息修改");
    setBounds(100, 100, 500, 400); //设置子窗口的边界
    setVisible(true); 
   
    final JPanel panel = new JPanel();                           //总面板
    panel.setLayout(new BorderLayout());
    panel.setBorder(new EmptyBorder(0, 0, 0, 0));
    getContentPane().add(panel);
    final JPanel panel1 = new JPanel(); //个人信息显示区域
    panel1.setBorder(new LineBorder(Color.cyan,60,false));   //设置 panel1边界线的颜色大小,形状
    panel1.setBackground(Color.cyan);
    final GridLayout gridLayout = new GridLayout(0, 2,5,10);
    panel1.setLayout(gridLayout);
   
    departmentLabel = new JLabel();
    departmentLabel.setText("院 系:");
    classLabel = new JLabel();
         classLabel.setText("班 级:");
         nameLabel = new JLabel();
         nameLabel.setText("姓 名:");
         idLabel = new JLabel();
         idLabel.setText("学 号:");
         sexLabel = new JLabel();
         sexLabel.setText("性 别:");
         
         departmentField = new JTextField ();
         departmentField.setEditable(false);
         classField= new JTextField();
         classField.setEditable(false);
         nameField=new JTextField();
         //nameField.setEditable(false);
         nameField.setPreferredSize(new Dimension(0, 0));
         idField= new JTextField();
         idField.setEditable(false);
         sexField= new JTextField();
         //sexField.setEditable(false);
         
         departmentField.setText(Student.department);      //设置待显示的学生个人信息
     classField.setText(Student.sclass);
     nameField.setText(Student.name);
     idField.setText(Student.id);
     sexField.setText(Student.sex);
     
         panel1.add(departmentLabel);    
         panel1.add(departmentField);
         panel1.add(classLabel);
         panel1.add(classField);
         panel1.add(nameLabel);
         panel1.add(nameField);
         panel1.add(idLabel);
         panel1.add(idField);
         panel1.add(sexLabel);
         panel1.add(sexField);
         panel.add(panel1, BorderLayout.CENTER);
       
         final JPanel panel2 = new JPanel();                         //确认和相关按钮显示区域
    final GridLayout gridLayout2 = new GridLayout(0, 2);
    gridLayout2.setHgap(5);
    gridLayout2.setVgap(20);
    panel2.setLayout(gridLayout2);
   
    queding = new JButton("确定");                                //确定按钮的相关设置
    queding.addActionListener(new ActionListener(){         
     public void actionPerformed(ActionEvent arg0) {
      JOptionPane.showMessageDialog(null, "很抱歉,您无权修改信息!");
      departmentField.setText(Student.department);      //重新设置设置待显示的学生个人信息
       classField.setText(Student.sclass);
       nameField.setText(Student.name);
       idField.setText(Student.id);
       sexField.setText(Student.sex);
       
         }});
   
    tuichu = new JButton("退出");
    tuichu.addActionListener(new ActionListener(){         
     public void actionPerformed(ActionEvent arg0) {
      StudentModifyInfoIFrame.this.setVisible(false);
         }});
    panel2.add(queding);
    panel2.add(tuichu);
    panel.add(panel2, BorderLayout.SOUTH);
  
    final JLabel tupianLabel = new JLabel();                //图片标签
    ImageIcon loginIcon=new ImageIcon("res/gerenxinxixiugai.jpg");
    tupianLabel.setIcon(loginIcon);
    tupianLabel.setOpaque(true);
    tupianLabel.setBackground(Color.GREEN);
    tupianLabel.setPreferredSize(new Dimension(260, 50));
    panel.add(tupianLabel, BorderLayout.NORTH);
    //panel.add(tupianLabel, BorderLayout.NORTH);
    
       
   }

}
