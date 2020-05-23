import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class CommFrame extends JFrame implements ActionListener {
   File file=null;
   JMenuBar bar;
   JMenu fileMenu;
   JMenuItem inputMenuItem,showMenuItem;
   JTextArea show;         //负责显示信息
   InputArea inputMessage; //负责录入信息(InputArea是自己写的类，见本例中的InputArea.java)
   CardLayout card=null;   //卡片式布局.
   JPanel pCenter;
   CommFrame() {
       file=new File("通讯录.txt");
       inputMenuItem=new JMenuItem("录入");
       showMenuItem=new JMenuItem("显示");
       bar=new JMenuBar();
       fileMenu=new JMenu("菜单选项");
       fileMenu.add(inputMenuItem);
       fileMenu.add(showMenuItem);
       bar.add(fileMenu);
       setJMenuBar(bar);
       inputMenuItem.addActionListener(this);
       showMenuItem.addActionListener(this);
       inputMessage=new InputArea(file);
       show=new JTextArea(12,20); 
       card=new CardLayout();
       pCenter=new JPanel();
       pCenter.setLayout(card);
       pCenter.add("inputMenuItem",inputMessage);
       pCenter.add("showMenuItem",show);
       add(pCenter,BorderLayout.CENTER);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setVisible(true);
       setBounds(100,50,420,380);
       validate();
   }
   public void actionPerformed(ActionEvent e) {
       if(e.getSource()==inputMenuItem)
           card.show(pCenter,"inputMenuItem");
       else if(e.getSource()==showMenuItem){
          int number=1;
          show.setText(null);
          card.show(pCenter,"showMenuItem");
          try{  RandomAccessFile in=new RandomAccessFile(file,"r");
                String name=null; 
                while((name=in.readUTF())!=null) {   
                    show.append("\n"+number+" "+name);
                    show.append("\t "+in.readUTF());  //读取email
                    show.append("\t"+in.readUTF());  //读取phone
                    show.append("\n------------------------- ");
                    number++;
               }
               in.close();
          }
          catch(Exception ee){}
       }
   }
}
