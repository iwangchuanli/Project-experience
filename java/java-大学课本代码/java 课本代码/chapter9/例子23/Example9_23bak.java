import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Example9_23 {
   public static void main(String args[]) {
      MyFrame f=new MyFrame();
      f.setBounds(70,70,570,289);
      f.setVisible(true);
      f.validate();
   }
}
class MyFrame extends JFrame implements ActionListener {
   PrintJob p=null;             
   Graphics g=null;
   JTextArea text=new JTextArea(10,10);
   JButton printTextFied=new JButton("��ӡ�ı���"),
          printFrame=new JButton("��ӡ����"),
          printButton=new JButton("��ӡ��ť");
   MyFrame() {
      super("��Ӧ�ó����д�ӡ"); 
      printTextFied.addActionListener(this);
      printFrame.addActionListener(this);
      printButton.addActionListener(this);
      add(text,"Center");
      JPanel panel=new JPanel();
      panel.add(printTextFied); 
      panel.add(printFrame);
      panel.add(printButton);
      add(panel,"South");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
   }
   public void actionPerformed(ActionEvent e) {
     if(e.getSource()==printTextFied) {
          p=getToolkit().getPrintJob(this,"ok",null);
          g=p.getGraphics();    //p��ȡһ�����ڴ�ӡ�� Graphics����
          g.translate(120,200); 
          text.printAll(g);     //��ӡ��ǰ�ı�����������
          g.dispose();          //�ͷŶ��� g 
          p.end();
     } 
     else if(e.getSource()==printFrame) {
          p=getToolkit().getPrintJob(this,"ok",null);
          g=p.getGraphics();    
          g.translate(120,200);
          this.printAll(g);      //��ӡ��ǰ���ڼ��������
          g.dispose();           
          p.end();
     } 
     else if(e.getSource()==printButton) {
          p=getToolkit().getPrintJob(this,"ok",null);
          g=p.getGraphics();
          g.translate(120,200); //�ڴ�ӡҳ������(120,200)����ӡprintTextFied��Ť
          printTextFied.printAll(g);
          g.translate(78,0);   //�ڴ�ӡҳ������(198,200)����ӡprintFrame��Ť
          printFrame.printAll(g);
          g.translate(66,0);   //�ڴ�ӡҳ������(264,200)����ӡprintButton��
          printButton.printAll(g);   
          g.dispose();          
          p.end();
     }
   }
}
