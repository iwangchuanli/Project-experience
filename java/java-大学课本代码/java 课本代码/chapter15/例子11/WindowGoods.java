import java.io.*; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class WindowGoods extends JFrame implements ActionListener {
   File file=null;
   JMenuBar bar;
   JMenu fileMenu;
   JMenuItem ¼��,��ʾ;
   JTextArea show;
   InputArea inputMessage;
   JPanel pCenter;
   JTable table;
   Object ���Ԫ[][],����[]={"����","���","����"};
   CardLayout card;
   WindowGoods() {
     file=new File("���.txt"); //���������ļ�
     ¼��=new JMenuItem("¼��");
     ��ʾ=new JMenuItem("��ʾ");
     bar=new JMenuBar();
     fileMenu=new JMenu("�˵�ѡ��");
     fileMenu.add(¼��);
     fileMenu.add(��ʾ);
     bar.add(fileMenu);
     setJMenuBar(bar);
     ¼��.addActionListener(this);
     ��ʾ.addActionListener(this);
     inputMessage=new InputArea(file); //����¼�����
     card=new CardLayout();
     pCenter=new JPanel();
     pCenter.setLayout(card);
     pCenter.add("¼��",inputMessage);
     add(pCenter,BorderLayout.CENTER);
     setVisible(true);
     setBounds(100,50,420,380);
     validate();
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==¼��) {
        card.show(pCenter,"¼��");
      }
      else if(e.getSource()==��ʾ) {
        try{ 
           FileInputStream fi=new FileInputStream(file);
           ObjectInputStream oi=new  ObjectInputStream(fi);
           LinkedList<Goods> goodsList=(LinkedList<Goods>)oi.readObject();
           fi.close();
           oi.close();  
           int length=goodsList.size();
           ���Ԫ=new Object[length][3];
           table=new JTable(���Ԫ,����); 
           pCenter.removeAll(); 
           pCenter.add("¼��",inputMessage);
           pCenter.add("��ʾ",new JScrollPane(table)); 
           pCenter.validate();      
           Iterator<Goods> iter=goodsList.iterator();
           int i=0;
           while(iter.hasNext()) {
              Goods  ��Ʒ =iter.next();
              ���Ԫ[i][0]= ��Ʒ.getName();
              ���Ԫ[i][1]=��Ʒ.getMount();
              ���Ԫ[i][2]=��Ʒ.getPrice();
              i++;
           }
           table.repaint();
        }
        catch(Exception ee){}
        card.show(pCenter,"��ʾ");
      }
   }
}
