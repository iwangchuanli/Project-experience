 /*ѧ�������޸��ӽ���*/
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
    setMaximizable(true);        //ʹ�����
    setIconifiable(true);
    setClosable(true);
    setTitle("�����޸�");
    setBounds(50, 50, 500, 400); //�����Ӵ��ڵı߽�
    setVisible(true); 
   
    final JPanel panel = new JPanel();                           //���
    panel.setLayout(new BorderLayout());
    panel.setBorder(new EmptyBorder(0, 0, 0, 0));
    getContentPane().add(panel);
    final JPanel panel1 = new JPanel();   //������Ϣ��ʾ����
    panel1.setBorder(new LineBorder(Color.cyan,80,false));   //���� panel1�߽��ߵ���ɫ��С,��״
    panel1.setBackground(Color.cyan);
    final GridLayout gridLayout = new GridLayout(0, 2,5,10);
    panel1.setLayout(gridLayout);
   
         oldLabel = new JLabel();
         oldLabel.setText("���������ľ�����:");
         newLabel = new JLabel();
         newLabel.setText("����������������:");
         newAgainLabel = new JLabel();
         newAgainLabel.setText("��ȷ������������:");
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
       
       final JPanel panel2 = new JPanel();                         //ȷ�Ϻ���ذ�ť��ʾ����
    final GridLayout gridLayout2 = new GridLayout(0, 2);
    gridLayout2.setHgap(5);
    gridLayout2.setVgap(20);
    panel2.setLayout(gridLayout2);
   
    queding = new JButton("ȷ��");                                //ȷ����ť���������
    queding.addActionListener(new ActionListener(){         
     public void actionPerformed(ActionEvent arg0) {
      if(! newField.getText().equals("")){
         if(newField.getText().trim().equals(newAgainField.getText().trim() )){
         if(oldField.getText().equals(Student.password)){
            if(Student.changePassword(newField.getText().trim())){
               JOptionPane.showMessageDialog(null, "�����޸ĳɹ�!���μ�����������:"+newField.getText().trim());
               oldField.setText("");
             newField.setText("");
             newAgainField.setText("");
            }else{
             JOptionPane.showMessageDialog(null, "�����޸�ʧ��!");
             oldField.setText("");
             newField.setText("");
             newAgainField.setText("");
            }
         }else{
            JOptionPane.showMessageDialog(null, "���ľ������������,���޸�!");
            oldField.setText("");
         }
         
          
         }else{
          JOptionPane.showMessageDialog(null, "�Բ���,��������������벻һ��,���޸�!");
          newField.setText("");
          newAgainField.setText("");
      }
      }else{
       JOptionPane.showMessageDialog(null, "���벻��Ϊ��!����������!");
       newField.setText("");
         newAgainField.setText("");
      }
         }});
   
    tuichu = new JButton("�˳�");
    tuichu.addActionListener(new ActionListener(){         
     public void actionPerformed(ActionEvent arg0) {
      StudentModifyPasswordIFrame.this.setVisible(false);
         }});
    panel2.add(queding);
    panel2.add(tuichu);
    panel.add(panel2, BorderLayout.SOUTH);
  
    final JLabel tupianLabel = new JLabel();                //ͼƬ��ǩ
    ImageIcon loginIcon=new ImageIcon("res/xueshengmimaxiugai.jpg");
    tupianLabel.setIcon(loginIcon);
    tupianLabel.setOpaque(true);
    tupianLabel.setBackground(Color.GREEN);
    tupianLabel.setPreferredSize(new Dimension(260, 50));
    panel.add(tupianLabel, BorderLayout.NORTH);
       
   }/*Construct over*/
}
