/*��ʦ�γ���Ϣ��ѯ�ӽ���*/
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
   setMaximizable(true);        //ʹ�����
   setIconifiable(true);        //ʹ����С��
   setClosable(true);           //ʹ�ɹر�
   setTitle("�γ���Ϣ��ѯ");
   setBounds(50, 50, 500, 400); //�����Ӵ��ڵı߽�
   setVisible(true); 
  
   final JPanel panel = new JPanel();                           //�Ӵ��ڵ������
   panel.setLayout(new BorderLayout());
   panel.setBorder(new EmptyBorder(0, 0, 0, 0));
   getContentPane().add(panel);
  
   final JLabel tupianLabel = new JLabel();                //ͼƬ��ǩ
   ImageIcon loginIcon=new ImageIcon("res/kechengxinxi.jpg");
   tupianLabel.setIcon(loginIcon);
   tupianLabel.setOpaque(true);
   tupianLabel.setBackground(Color.GREEN);
   tupianLabel.setPreferredSize(new Dimension(260, 50));
   panel.add(tupianLabel, BorderLayout.NORTH); 
        
   final JPanel panelOfInfo = new JPanel();                         //������Ϣ��ʾ����
   panelOfInfo.setBorder(new LineBorder(Color.cyan,60,false));   //����panelOfInfo�ı߽��ߵ���ɫ��С,��״
   panelOfInfo.setBackground(Color.cyan);
   GridLayout layout = new GridLayout(0,2,5,10);
   panelOfInfo.setLayout(layout);
   idLabel = new JLabel("�γ̴���:");
        nameLabel = new JLabel("�γ�����:");
        yearLabel = new JLabel("�ڿ�ѧ��:");
        termLabel = new JLabel("�ڿ�ѧ��:");
        xueshiLabel = new JLabel("ѧ ʱ:");
        xuefenLabel = new JLabel("ѧ ��:");
       
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
        panelOfInfo.add(yearLabel);//������ʾ�ĸ�����Ϣ�ؼ�����panelOfInfo
    panelOfInfo.add(yearField);
    panelOfInfo.add(termLabel);
    panelOfInfo.add(termField);
    panelOfInfo.add(xueshiLabel);
    panelOfInfo.add(xueshiField);
    panelOfInfo.add(xuefenLabel); 
    panelOfInfo.add(xuefenField);
        panel.add(panelOfInfo, BorderLayout.CENTER);
        
        final JPanel panelOfButton = new JPanel();                         //ȷ�Ϻ���ذ�ť��ʾ����
   final GridLayout gridLayout2 = new GridLayout(0, 2,0,0);
   panelOfButton.setLayout(gridLayout2);
  
   queding = new JButton("ȷ��");                                //ȷ����ť���������
   queding.addActionListener(new ActionListener(){         
    public void actionPerformed(ActionEvent arg0) {
     TeacherCourseInfoIFrame.this.setVisible(false);
        }});
  
   xiugai = new JButton("�޸�");
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
