/*
学生学期成绩查询子界面
*/
package com.system.jiemian;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import com.system.model.Student;
import com.system.model.StudentCourse;

public class QueryScoreByTermIFrame extends JInternalFrame {
    /**
* 
*/
private static final long serialVersionUID = -8299226900760947417L;
private JComboBox yearChoice; //年份选择框
private JComboBox termChoice;//学期选择框
    private JScrollPane scrollPane; //成绩显示区域
    public QueryScoreByTermIFrame () {   //构造器
     super();
     setMaximizable(true);        //使可最大化
     setIconifiable(true);
     setClosable(true);
     setTitle("学期成绩查询");
     setBounds(50, 50, 500, 400); //设置子窗口的边界
        setVisible(true);      //显示子窗口

     final JPanel panel = new JPanel();   //总的显示panel
     panel.setLayout(new BorderLayout());
     getContentPane().add(panel);
     final JPanel panelOfQueryInput = new JPanel();   //查询条件输入区域panelOfQueryInput
     panelOfQueryInput.setBorder(new TitledBorder(null, "请选择查询条件", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
     panelOfQueryInput.setPreferredSize(new Dimension(10, 50));
     panel.add(panelOfQueryInput, BorderLayout.NORTH);
        yearChoice = new JComboBox();                      //年份查询条件选择框
     String[] array1={"2015","2016","2017","2018"};   //设置年份选择框的内容
     for(int i=0;i<array1.length;i++){
   yearChoice.addItem(array1[i]);
   }
     termChoice = new JComboBox();      //学期查询条件选择框
     String[] array2={"1","2"};           //设置选择框的内容
     for(int i=0;i<array2.length;i++){
     termChoice.addItem(array2[i]);
   }
     
     panelOfQueryInput.add(new JLabel("请选择学年:")); //加入年份输入提示信息和输入选择框
     panelOfQueryInput.add(yearChoice);
     panelOfQueryInput.add(new JLabel("请选择学期:")); //加入学期输入提示信息和输入选择框
     panelOfQueryInput.add(termChoice);
     
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
      QueryScoreByTermIFrame.this.setVisible(false);
        }});
     panel.add(panelOfButton, BorderLayout.SOUTH);
     setVisible(true);

   }
    public Object[][] getObject(List list) {   //获得Jtable显示内容的对象
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
    class QueryButtonListener implements ActionListener { //查询按钮的监听器
        public void actionPerformed(ActionEvent arg0) {
         String year=(String)yearChoice.getSelectedItem();
         String term=(String)termChoice.getSelectedItem();
         JTable table = null;                      //申明表格
         Object[][] tableBody = null;              //申明表的内容
         String [] tableTitle = { "课程代码", "课程名", "老师", "学期", "学年","学分", "学时", "分数" }; //申明表头

      tableBody=getObject(Student.getTermScoreLiat(year, term)); 
      table = new JTable(tableBody,tableTitle);
      table.setBackground(Color.cyan);      //设置表格的背景色
      table.setEnabled(false);
      scrollPane.setViewportView(table);
     }
        }/*class QueryButtonListener */
}
