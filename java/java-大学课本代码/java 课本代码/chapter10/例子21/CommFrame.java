import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class CommFrame extends JFrame implements ActionListener {
   File file=null;
   JMenuBar bar;
   JMenu fileMenu;
   JMenuItem inputMenuItem,showMenuItem;
   JTextArea show;         //������ʾ��Ϣ
   InputArea inputMessage; //����¼����Ϣ(InputArea���Լ�д���࣬�������е�InputArea.java)
   CardLayout card=null;   //��Ƭʽ����.
   JPanel pCenter;
   CommFrame() {
       file=new File("ͨѶ¼.txt");
       inputMenuItem=new JMenuItem("¼��");
       showMenuItem=new JMenuItem("��ʾ");
       bar=new JMenuBar();
       fileMenu=new JMenu("�˵�ѡ��");
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
                    show.append("\t "+in.readUTF());  //��ȡemail
                    show.append("\t"+in.readUTF());  //��ȡphone
                    show.append("\n------------------------- ");
                    number++;
               }
               in.close();
          }
          catch(Exception ee){}
       }
   }
}
