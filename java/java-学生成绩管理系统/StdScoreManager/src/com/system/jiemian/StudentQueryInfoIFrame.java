/*ѧ��������Ϣ��ѯ�ӽ���*/
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
   setMaximizable(true);        //ʹ�����
   setIconifiable(true);        //ʹ����С��
   setClosable(true);           //ʹ�ɹر�
   setTitle("������Ϣ��ѯ");
   setBounds(50, 50, 500, 400); //�����Ӵ��ڵı߽�
   setVisible(true); 
  
   final JPanel panel = new JPanel();                           //�Ӵ��ڵ������
   panel.setLayout(new BorderLayout());
   panel.setBorder(new EmptyBorder(0, 0, 0, 0));
   getContentPane().add(panel);
  
   final JLabel tupianLabel = new JLabel();                //ͼƬ��ǩ
   ImageIcon loginIcon=new ImageIcon("res/gerenxinxixianshi.jpg");
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
   departmentLabel = new JLabel("Ժ ϵ:");
   classLabel = new JLabel("�� ��:");
        nameLabel = new JLabel("�� ��:");
        idLabel = new JLabel("ѧ ��:");
        sexLabel = new JLabel("�� ��:");
        
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
        
        departmentField.setText(Student.department);    //������ʾ�ĸ�����Ϣ
    classField.setText(Student.sclass);
    nameField.setText(Student.name);
    idField.setText(Student.id);
    sexField.setText(Student.sex);    
    
        
    panelOfInfo.add(departmentLabel);             //������ʾ�ĸ�����Ϣ�ؼ�����panelOfInfo
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
        
        final JPanel panelOfButton = new JPanel();                         //ȷ�Ϻ���ذ�ť��ʾ����
   final GridLayout gridLayout2 = new GridLayout(0, 2,0,0);
   panelOfButton.setLayout(gridLayout2);
  
   queding = new JButton("ȷ��");                                //ȷ����ť���������
   queding.addActionListener(new ActionListener(){         
    public void actionPerformed(ActionEvent arg0) {
     StudentQueryInfoIFrame.this.setVisible(false);
        }});
  
   xiugai = new JButton("�޸�");
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
