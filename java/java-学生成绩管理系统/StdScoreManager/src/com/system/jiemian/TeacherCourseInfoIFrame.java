/*教师课程信息查询子界面*/
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import com.system.model.Course;
import com.system.model.Teacher;
public class TeacherCourseInfoIFrame extends JInternalFrame {

/**
* 
*/
private static final long serialVersionUID = -7790479816091975191L;
private JLabel nameLabel,idLabel,yearLabel,termLabel,xueshiLabel,xuefenLabel;
    private JTextField nameField,idField,yearField,termField,xueshiField,xuefenField;
    private JButton queding,xiugai;


public TeacherCourseInfoIFrame (){
   super(); 
   setMaximizable(true);        //使可最大化
   setIconifiable(true);        //使可最小化
   setClosable(true);           //使可关闭
   setTitle("课程信息查询");
   setBounds(50, 50, 500, 400); //设置子窗口的边界
   setVisible(true); 
  
   final JPanel panel = new JPanel();                           //子窗口的总面板
   panel.setLayout(new BorderLayout());
   panel.setBorder(new EmptyBorder(0, 0, 0, 0));
   getContentPane().add(panel);
  
   final JLabel tupianLabel = new JLabel();                //图片标签
   ImageIcon loginIcon=new ImageIcon("res/kechengxinxi.jpg");
   tupianLabel.setIcon(loginIcon);
   tupianLabel.setOpaque(true);
   tupianLabel.setBackground(Color.GREEN);
   tupianLabel.setPreferredSize(new Dimension(260, 50));
   panel.add(tupianLabel, BorderLayout.NORTH); 
        
   final JPanel panelOfInfo = new JPanel();                         //个人信息显示区域
   panelOfInfo.setBorder(new LineBorder(Color.cyan,60,false));   //设置panelOfInfo的边界线的颜色大小,形状
   panelOfInfo.setBackground(Color.cyan);
   GridLayout layout = new GridLayout(0,2,5,10);
   panelOfInfo.setLayout(layout);
   idLabel = new JLabel("课程代码:");
        nameLabel = new JLabel("课程名称:");
        yearLabel = new JLabel("授课学年:");
        termLabel = new JLabel("授课学期:");
        xueshiLabel = new JLabel("学 时:");
        xuefenLabel = new JLabel("学 分:");
       
        Course course = new Course(Teacher.courseid);
        idField = new JTextField(course.id); //nameField,idField,yearField,termField,xueshiField,xuefenField;
        idField.setEditable(false);
        nameField = new JTextField(course.name);
        nameField.setEditable(false);
        yearField = new JTextField(course.year);
        yearField.setEditable(false);
        termField = new JTextField(course.term);
        termField.setEditable(false);
        xueshiField = new JTextField(String.valueOf(course.xueshi));
        xueshiField.setEditable(false);
        xuefenField = new JTextField(String.valueOf(course.xuefen));
        xuefenField.setEditable(false);
         
    
        panelOfInfo.add(idLabel);
        panelOfInfo.add(idField);
        panelOfInfo.add(nameLabel);
        panelOfInfo.add(nameField);
        panelOfInfo.add(yearLabel);//将待显示的个人信息控件加入panelOfInfo
    panelOfInfo.add(yearField);
    panelOfInfo.add(termLabel);
    panelOfInfo.add(termField);
    panelOfInfo.add(xueshiLabel);
    panelOfInfo.add(xueshiField);
    panelOfInfo.add(xuefenLabel); 
    panelOfInfo.add(xuefenField);
        panel.add(panelOfInfo, BorderLayout.CENTER);
        
        final JPanel panelOfButton = new JPanel();                         //确认和相关按钮显示区域
   final GridLayout gridLayout2 = new GridLayout(0, 2,0,0);
   panelOfButton.setLayout(gridLayout2);
  
   queding = new JButton("确定");                                //确定按钮的相关设置
   queding.addActionListener(new ActionListener(){         
    public void actionPerformed(ActionEvent arg0) {
     TeacherCourseInfoIFrame.this.setVisible(false);
        }});
  
   xiugai = new JButton("修改");
   xiugai.addActionListener(new ActionListener(){         
    public void actionPerformed(ActionEvent arg0) {
     //TeacherMainFrame.addIFame(new TeacherModifyInfoIFrame());
     TeacherCourseInfoIFrame.this.setVisible(false);
        }});
   panelOfButton.add(queding);
   panelOfButton.add(xiugai);
   panel.add(panelOfButton, BorderLayout.SOUTH);
  
}

}
