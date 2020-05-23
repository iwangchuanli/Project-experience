 /*学生密码修改子界面*/
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
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import com.system.model.Student;

public class StudentModifyPasswordIFrame extends JInternalFrame{
     /**
* 
*/
private static final long serialVersionUID = -8711355002967516507L;
   private JLabel oldLabel,newLabel,newAgainLabel;
     private JPasswordField oldField,newField,newAgainField;
     private JButton queding,tuichu;
  
  
   public StudentModifyPasswordIFrame(){
    super();
    setMaximizable(true);        //使可最大化
    setIconifiable(true);
    setClosable(true);
    setTitle("密码修改");
    setBounds(50, 50, 500, 400); //设置子窗口的边界
    setVisible(true); 
   
    final JPanel panel = new JPanel();                           //面板
    panel.setLayout(new BorderLayout());
    panel.setBorder(new EmptyBorder(0, 0, 0, 0));
    getContentPane().add(panel);
    final JPanel panel1 = new JPanel();   //个人信息显示区域
    panel1.setBorder(new LineBorder(Color.cyan,80,false));   //设置 panel1边界线的颜色大小,形状
    panel1.setBackground(Color.cyan);
    final GridLayout gridLayout = new GridLayout(0, 2,5,10);
    panel1.setLayout(gridLayout);
   
         oldLabel = new JLabel();
         oldLabel.setText("请输入您的旧密码:");
         newLabel = new JLabel();
         newLabel.setText("请输入您的新密码:");
         newAgainLabel = new JLabel();
         newAgainLabel.setText("请确认您的新密码:");
         oldField=new JPasswordField();
         oldField.setPreferredSize(new Dimension(0, 0));
         //nameField.
         newField= new JPasswordField();
         newAgainField= new JPasswordField();
    
       panel1.add(oldLabel);
       panel1.add(oldField);
       panel1.add(newLabel);
       panel1.add(newField);
       panel1.add(newAgainLabel);
       panel1.add(newAgainField);
       panel.add(panel1, BorderLayout.CENTER);
       
       final JPanel panel2 = new JPanel();                         //确认和相关按钮显示区域
    final GridLayout gridLayout2 = new GridLayout(0, 2);
    gridLayout2.setHgap(5);
    gridLayout2.setVgap(20);
    panel2.setLayout(gridLayout2);
   
    queding = new JButton("确定");                                //确定按钮的相关设置
    queding.addActionListener(new ActionListener(){         
     public void actionPerformed(ActionEvent arg0) {
      if(! newField.getText().equals("")){
         if(newField.getText().trim().equals(newAgainField.getText().trim() )){
         if(oldField.getText().equals(Student.password)){
            if(Student.changePassword(newField.getText().trim())){
               JOptionPane.showMessageDialog(null, "密码修改成功!请牢记您的新密码:"+newField.getText().trim());
               oldField.setText("");
             newField.setText("");
             newAgainField.setText("");
            }else{
             JOptionPane.showMessageDialog(null, "密码修改失败!");
             oldField.setText("");
             newField.setText("");
             newAgainField.setText("");
            }
         }else{
            JOptionPane.showMessageDialog(null, "您的旧密码输入错误,请修改!");
            oldField.setText("");
         }
         
          
         }else{
          JOptionPane.showMessageDialog(null, "对不起,您两次输入的密码不一致,请修改!");
          newField.setText("");
          newAgainField.setText("");
      }
      }else{
       JOptionPane.showMessageDialog(null, "密码不能为空!请重新输入!");
       newField.setText("");
         newAgainField.setText("");
      }
         }});
   
    tuichu = new JButton("退出");
    tuichu.addActionListener(new ActionListener(){         
     public void actionPerformed(ActionEvent arg0) {
      StudentModifyPasswordIFrame.this.setVisible(false);
         }});
    panel2.add(queding);
    panel2.add(tuichu);
    panel.add(panel2, BorderLayout.SOUTH);
  
    final JLabel tupianLabel = new JLabel();                //图片标签
    ImageIcon loginIcon=new ImageIcon("res/xueshengmimaxiugai.jpg");
    tupianLabel.setIcon(loginIcon);
    tupianLabel.setOpaque(true);
    tupianLabel.setBackground(Color.GREEN);
    tupianLabel.setPreferredSize(new Dimension(260, 50));
    panel.add(tupianLabel, BorderLayout.NORTH);
       
   }/*Construct over*/
}
