/*
ѧ���ۺϳɼ���ѯ�ӽ���
*/
package com.system.jiemian;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import com.system.model.Student;
import com.system.model.StudentCourse;

public class QueryScoreByAllIFrame extends JInternalFrame {
private static final long serialVersionUID = -2378866877251982599L;
    private JScrollPane scrollPane;   //�ɼ���ʾ����

    public QueryScoreByAllIFrame() { //������
     super();
     setMaximizable(true);        //ʹ�����
     setIconifiable(true);
     setClosable(true);
     setTitle("�ۺϳɼ���ѯ");
     setBounds(50, 50, 500, 400);   //�����Ӵ��ڵı߽�
        setVisible(true);             //��ʾ�Ӵ���

     final JPanel panel = new JPanel(); //�ܵ���ʾpanel
     panel.setLayout(new BorderLayout());
     getContentPane().add(panel);
     final JPanel panelOfQueryInput = new JPanel();   //��ѯ������������panelOfQueryInput
     panelOfQueryInput.setBorder(new TitledBorder(null, "��ܰ����", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
     panelOfQueryInput.setPreferredSize(new Dimension(10, 50));
     panel.add(panelOfQueryInput, BorderLayout.NORTH);
     panelOfQueryInput.add(new JLabel("�����ѯ,�ɲ鵽�����пγ̵ĳɼ���¼.")); //����������ʾ��Ϣ
   
        final JPanel panelOfResult = new JPanel();      ////�����ʾ����panelOfResult
        panelOfResult.setBorder(new TitledBorder(null, "��ѯ�����ʾ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
     panel.add(panelOfResult);
     scrollPane = new JScrollPane();             
     scrollPane.setPreferredSize(new Dimension(450, 250));
    
     panelOfResult.add(new JLabel("����:"));    //������ʾ������Ϣ�ı�ǩ
     panelOfResult.add(new JLabel(Student.name));
     panelOfResult.add(new JLabel(" ѧ��:"));
     panelOfResult.add(new JLabel(Student.id));
     panelOfResult.add( new JLabel(" Ժϵ:"));
     panelOfResult.add(new JLabel(Student.department));
     panelOfResult.add(new JLabel (" �༶:"));
     panelOfResult.add(new JLabel(Student.sclass+"��"));
     panelOfResult.add(scrollPane);
     panel.add(panelOfResult);
   
     final JPanel panelOfButton = new JPanel();      //��ť��ʾ��panelOfButton
     panelOfButton.setBorder(new TitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
     panelOfButton.setPreferredSize(new Dimension(200, 100));
    
     final JButton button = new JButton();
     button.setText("��ѯ");
     panelOfButton.add(button);
        button.addActionListener(new QueryButtonListener());

     final JButton exitButton = new JButton();
     exitButton.setText("�˳�");
     panelOfButton.add(exitButton);
     exitButton.addActionListener(new ActionListener(){      //Ϊ�˳���ť��Ӽ�����      
      public void actionPerformed(ActionEvent arg0) {
       QueryScoreByAllIFrame.this.setVisible(false);
        }});
     panel.add(panelOfButton, BorderLayout.SOUTH);
     setVisible(true);

   }
    public Object[][] getObject(List list) { 
     Object[][] s = new Object[list.size()][8];
     for (int i = 0; i < list.size(); i++) {
   StudentCourse book = (StudentCourse) list.get(i);
   s[i][0] = book.id;
   s[i][1] = book.name;
   s[i][2] = book.teacher;
   s[i][3] = book.term;
   s[i][4] = book.year;
   s[i][5] = new Integer(book.xuefen);
   s[i][6] = new Integer( book.xueshi);
   s[i][7] = new Double(book.score);
     }
     return s;
    }
    class QueryButtonListener implements ActionListener {    //��ѯ��ť�ļ�����
        public void actionPerformed(ActionEvent arg0) {
     
    JTable table = null;                      //�������
         Object[][] tableBody = null;              //�����������
         String [] tableTitle = { "�γ̴���", "�γ���", "��ʦ", "ѧ��", "ѧ��","ѧ��", "ѧʱ", "����" }; //������ͷ
  
    tableBody=getObject(Student.getScoreList()); 
    table = new JTable(tableBody,tableTitle);
    table.setBackground(Color.cyan);      //���ñ��ı���ɫ
      table.setEnabled(false);
    scrollPane.setViewportView(table);
     }
        }/*class QueryButtonListener */
}
