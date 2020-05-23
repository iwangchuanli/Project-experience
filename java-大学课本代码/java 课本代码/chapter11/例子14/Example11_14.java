import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Example11_14 {
   public static void main(String args[]) {
      JTable table;
      Query  query;   //负责查询数据库
      query = new Query();
      query.setDatasourceName("myData");
      query.setTableName("goods");
      Object a[][]=query.getRecord(); 
      int m=a[0].length;
      String b[]=new String[m];  
      for(int i=0;i<m;i++)
           b[i]="字段"+(i+1);
      table=new JTable(a,b);
      JFrame frame=new JFrame();
      frame.add(new JScrollPane(table));
      frame.setVisible(true);
      frame.setBounds(20,20,300,400);  
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
   }
}
