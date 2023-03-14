import java.io.*; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class WindowGoods extends JFrame implements ActionListener {
   File file=null;
   JMenuBar bar;
   JMenu fileMenu;
   JMenuItem 录入,显示;
   JTextArea show;
   InputArea inputMessage;
   JPanel pCenter;
   JTable table;
   Object 表格单元[][],列名[]={"名称","库存","单价"};
   CardLayout card;
   WindowGoods() {
     file=new File("库存.txt"); //存放链表的文件
     录入=new JMenuItem("录入");
     显示=new JMenuItem("显示");
     bar=new JMenuBar();
     fileMenu=new JMenu("菜单选项");
     fileMenu.add(录入);
     fileMenu.add(显示);
     bar.add(fileMenu);
     setJMenuBar(bar);
     录入.addActionListener(this);
     显示.addActionListener(this);
     inputMessage=new InputArea(file); //创建录入截面
     card=new CardLayout();
     pCenter=new JPanel();
     pCenter.setLayout(card);
     pCenter.add("录入",inputMessage);
     add(pCenter,BorderLayout.CENTER);
     setVisible(true);
     setBounds(100,50,420,380);
     validate();
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==录入) {
        card.show(pCenter,"录入");
      }
      else if(e.getSource()==显示) {
        try{ 
           FileInputStream fi=new FileInputStream(file);
           ObjectInputStream oi=new  ObjectInputStream(fi);
           LinkedList<Goods> goodsList=(LinkedList<Goods>)oi.readObject();
           fi.close();
           oi.close();  
           int length=goodsList.size();
           表格单元=new Object[length][3];
           table=new JTable(表格单元,列名); 
           pCenter.removeAll(); 
           pCenter.add("录入",inputMessage);
           pCenter.add("显示",new JScrollPane(table)); 
           pCenter.validate();      
           Iterator<Goods> iter=goodsList.iterator();
           int i=0;
           while(iter.hasNext()) {
              Goods  商品 =iter.next();
              表格单元[i][0]= 商品.getName();
              表格单元[i][1]=商品.getMount();
              表格单元[i][2]=商品.getPrice();
              i++;
           }
           table.repaint();
        }
        catch(Exception ee){}
        card.show(pCenter,"显示");
      }
   }
}
