/*学生个人信息查询子界面*/
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
import com.system.model.Student;
public class StudentQueryInfoIFrame extends JInternalFrame{
    /**
* 
*/
private static final long serialVersionUID = -8052038577115090682L;
private JLabel nameLabel,idLabel,sexLabel,classLabel,departmentLabel;
    private JTextField nameField,idField,sexField,classField,departmentField;
    private JButton queding,xiugai;


public StudentQueryInfoIFrame(){
   super(); 
   setMaximizable(true);        //使可最大化
   setIconifiable(true);        //使可最小化
   setClosable(true);           //使可关闭
   setTitle("个人信息查询");
   setBounds(50, 50, 500, 400); //设置子窗口的边界
   setVisible(true); 
  
   final JPanel panel = new JPanel();                           //子窗口的总面板
   panel.setLayout(new BorderLayout());
   panel.setBorder(new EmptyBorder(0, 0, 0, 0));
   getContentPane().add(panel);
  
   final JLabel tupianLabel = new JLabel();                //图片标签
   ImageIcon loginIcon=new ImageIcon("res/gerenxinxixianshi.jpg");
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
   departmentLabel = new JLabel("院 系:");
   classLabel = new JLabel("班 级:");
        nameLabel = new JLabel("姓 名:");
        idLabel = new JLabel("学 号:");
        sexLabel = new JLabel("性 别:");
        
        departmentField = new JTextField ();
        departmentField.setEditable(false);
        classField= new JTextField();
        classField.setEditable(false);
        nameField=new JTextField();
        nameField.setEditable(false);
        idField= new JTextField();
        idField.setEditable(false);
        sexField= new JTextField();
        sexField.setEditable(false);
        
        departmentField.setText(Student.department);    //设置显示的个人信息
    classField.setText(Student.sclass);
    nameField.setText(Student.name);
    idField.setText(Student.id);
    sexField.setText(Student.sex);    
    
        
    panelOfInfo.add(departmentLabel);             //将待显示的个人信息控件加入panelOfInfo
    panelOfInfo.add(departmentField);
    panelOfInfo.add(classLabel);
    panelOfInfo.add(classField);
    panelOfInfo.add(nameLabel);
    panelOfInfo.add(nameField);
    panelOfInfo.add(idLabel);
    panelOfInfo.add(idField);
    panelOfInfo.add(sexLabel);
    panelOfInfo.add(sexField);
        panel.add(panelOfInfo, BorderLayout.CENTER);
        
        final JPanel panelOfButton = new JPanel();                         //确认和相关按钮显示区域
   final GridLayout gridLayout2 = new GridLayout(0, 2,0,0);
   panelOfButton.setLayout(gridLayout2);
  
   queding = new JButton("确定");                                //确定按钮的相关设置
   queding.addActionListener(new ActionListener(){         
    public void actionPerformed(ActionEvent arg0) {
     StudentQueryInfoIFrame.this.setVisible(false);
        }});
  
   xiugai = new JButton("修改");
   xiugai.addActionListener(new ActionListener(){         
    public void actionPerformed(ActionEvent arg0) {
     StudentMainFrame.addIFame(new StudentModifyInfoIFrame());
     StudentQueryInfoIFrame.this.setVisible(false);
        }});
   panelOfButton.add(queding);
   panelOfButton.add(xiugai);
   panel.add(panelOfButton, BorderLayout.SOUTH);
  
}
}
