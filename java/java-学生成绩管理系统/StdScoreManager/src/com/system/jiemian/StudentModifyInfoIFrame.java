/*ѧ��������Ϣ�޸��ӽ���*/
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
    setMaximizable(true);        //ʹ�����
    setIconifiable(true);
    setClosable(true);
    setTitle("������Ϣ�޸�");
    setBounds(100, 100, 500, 400); //�����Ӵ��ڵı߽�
    setVisible(true); 
   
    final JPanel panel = new JPanel();                           //�����
    panel.setLayout(new BorderLayout());
    panel.setBorder(new EmptyBorder(0, 0, 0, 0));
    getContentPane().add(panel);
    final JPanel panel1 = new JPanel(); //������Ϣ��ʾ����
    panel1.setBorder(new LineBorder(Color.cyan,60,false));   //���� panel1�߽��ߵ���ɫ��С,��״
    panel1.setBackground(Color.cyan);
    final GridLayout gridLayout = new GridLayout(0, 2,5,10);
    panel1.setLayout(gridLayout);
   
    departmentLabel = new JLabel();
    departmentLabel.setText("Ժ ϵ:");
    classLabel = new JLabel();
         classLabel.setText("�� ��:");
         nameLabel = new JLabel();
         nameLabel.setText("�� ��:");
         idLabel = new JLabel();
         idLabel.setText("ѧ ��:");
         sexLabel = new JLabel();
         sexLabel.setText("�� ��:");
         
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
         
         departmentField.setText(Student.department);      //���ô���ʾ��ѧ��������Ϣ
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
       
         final JPanel panel2 = new JPanel();                         //ȷ�Ϻ���ذ�ť��ʾ����
    final GridLayout gridLayout2 = new GridLayout(0, 2);
    gridLayout2.setHgap(5);
    gridLayout2.setVgap(20);
    panel2.setLayout(gridLayout2);
   
    queding = new JButton("ȷ��");                                //ȷ����ť���������
    queding.addActionListener(new ActionListener(){         
     public void actionPerformed(ActionEvent arg0) {
      JOptionPane.showMessageDialog(null, "�ܱ�Ǹ,����Ȩ�޸���Ϣ!");
      departmentField.setText(Student.department);      //�����������ô���ʾ��ѧ��������Ϣ
       classField.setText(Student.sclass);
       nameField.setText(Student.name);
       idField.setText(Student.id);
       sexField.setText(Student.sex);
       
         }});
   
    tuichu = new JButton("�˳�");
    tuichu.addActionListener(new ActionListener(){         
     public void actionPerformed(ActionEvent arg0) {
      StudentModifyInfoIFrame.this.setVisible(false);
         }});
    panel2.add(queding);
    panel2.add(tuichu);
    panel.add(panel2, BorderLayout.SOUTH);
  
    final JLabel tupianLabel = new JLabel();                //ͼƬ��ǩ
    ImageIcon loginIcon=new ImageIcon("res/gerenxinxixiugai.jpg");
    tupianLabel.setIcon(loginIcon);
    tupianLabel.setOpaque(true);
    tupianLabel.setBackground(Color.GREEN);
    tupianLabel.setPreferredSize(new Dimension(260, 50));
    panel.add(tupianLabel, BorderLayout.NORTH);
    //panel.add(tupianLabel, BorderLayout.NORTH);
    
       
   }

}
