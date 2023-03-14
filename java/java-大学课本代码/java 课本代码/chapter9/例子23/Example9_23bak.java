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
   JButton printTextFied=new JButton("打印文本框"),
          printFrame=new JButton("打印窗口"),
          printButton=new JButton("打印按钮");
   MyFrame() {
      super("在应用程序中打印"); 
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
          g=p.getGraphics();    //p获取一个用于打印的 Graphics对象
          g.translate(120,200); 
          text.printAll(g);     //打印当前文本区及其内容
          g.dispose();          //释放对象 g 
          p.end();
     } 
     else if(e.getSource()==printFrame) {
          p=getToolkit().getPrintJob(this,"ok",null);
          g=p.getGraphics();    
          g.translate(120,200);
          this.printAll(g);      //打印当前窗口及其子组件
          g.dispose();           
          p.end();
     } 
     else if(e.getSource()==printButton) {
          p=getToolkit().getPrintJob(this,"ok",null);
          g=p.getGraphics();
          g.translate(120,200); //在打印页的坐标(120,200)处打印printTextFied按扭
          printTextFied.printAll(g);
          g.translate(78,0);   //在打印页的坐标(198,200)处打印printFrame按扭
          printFrame.printAll(g);
          g.translate(66,0);   //在打印页的坐标(264,200)处打印printButton”
          printButton.printAll(g);   
          g.dispose();          
          p.end();
     }
   }
}
