/*
学生综合成绩查询子界面
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
    private JScrollPane scrollPane;   //成绩显示区域

    public QueryScoreByAllIFrame() { //构造器
     super();
     setMaximizable(true);        //使可最大化
     setIconifiable(true);
     setClosable(true);
     setTitle("综合成绩查询");
     setBounds(50, 50, 500, 400);   //设置子窗口的边界
        setVisible(true);             //显示子窗口

     final JPanel panel = new JPanel(); //总的显示panel
     panel.setLayout(new BorderLayout());
     getContentPane().add(panel);
     final JPanel panelOfQueryInput = new JPanel();   //查询条件输入区域panelOfQueryInput
     panelOfQueryInput.setBorder(new TitledBorder(null, "温馨提醒", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
     panelOfQueryInput.setPreferredSize(new Dimension(10, 50));
     panel.add(panelOfQueryInput, BorderLayout.NORTH);
     panelOfQueryInput.add(new JLabel("点击查询,可查到你所有课程的成绩记录.")); //加入输入提示信息
   
        final JPanel panelOfResult = new JPanel();      ////结果显示区域panelOfResult
        panelOfResult.setBorder(new TitledBorder(null, "查询结果显示", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
     panel.add(panelOfResult);
     scrollPane = new JScrollPane();             
     scrollPane.setPreferredSize(new Dimension(450, 250));
    
     panelOfResult.add(new JLabel("姓名:"));    //加入显示个人信息的标签
     panelOfResult.add(new JLabel(Student.name));
     panelOfResult.add(new JLabel(" 学号:"));
     panelOfResult.add(new JLabel(Student.id));
     panelOfResult.add( new JLabel(" 院系:"));
     panelOfResult.add(new JLabel(Student.department));
     panelOfResult.add(new JLabel (" 班级:"));
     panelOfResult.add(new JLabel(Student.sclass+"班"));
     panelOfResult.add(scrollPane);
     panel.add(panelOfResult);
   
     final JPanel panelOfButton = new JPanel();      //按钮显示区panelOfButton
     panelOfButton.setBorder(new TitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
     panelOfButton.setPreferredSize(new Dimension(200, 100));
    
     final JButton button = new JButton();
     button.setText("查询");
     panelOfButton.add(button);
        button.addActionListener(new QueryButtonListener());

     final JButton exitButton = new JButton();
     exitButton.setText("退出");
     panelOfButton.add(exitButton);
     exitButton.addActionListener(new ActionListener(){      //为退出按钮添加监听器      
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
    class QueryButtonListener implements ActionListener {    //查询按钮的监听器
        public void actionPerformed(ActionEvent arg0) {
     
    JTable table = null;                      //申明表格
         Object[][] tableBody = null;              //申明表的内容
         String [] tableTitle = { "课程代码", "课程名", "老师", "学期", "学年","学分", "学时", "分数" }; //申明表头
  
    tableBody=getObject(Student.getScoreList()); 
    table = new JTable(tableBody,tableTitle);
    table.setBackground(Color.cyan);      //设置表格的背景色
      table.setEnabled(false);
    scrollPane.setViewportView(table);
     }
        }/*class QueryButtonListener */
}
