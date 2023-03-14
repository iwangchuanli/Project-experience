import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
public class Example10_14 { 
   public static void main(String args[]) { 
      MyWin win=new MyWin();
   }
}
class MyWin extends JFrame implements ActionListener {
   JLabel label=null; 
   JButton 读入=null,写出=null;
   ByteArrayOutputStream out = null;
   MyWin() {
      setLayout(new FlowLayout()); 
      label=new JLabel("How are you");
      读入=new JButton("读入对象"); 
      写出=new JButton("写出对象");
      读入.addActionListener(this);
      写出.addActionListener(this);
      setVisible(true); 
      add(label);
      add(写出);
      add(读入);
      setSize(500,400);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      validate();
   }
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==写出) {
          try{  out = new ByteArrayOutputStream();
                ObjectOutputStream objectOut = new ObjectOutputStream(out);
                objectOut.writeObject(label);               
                objectOut.close();
          }
          catch(IOException event){}
      }
      else if(e.getSource()==读入) {
          try{  ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
                ObjectInputStream objectIn = new ObjectInputStream(in);
                JLabel temp=(JLabel)objectIn.readObject();
                temp.setText("你好"); 
                this.add(temp);
                this.validate();
                objectIn.close();
          }
          catch(Exception event){}
      }
   }
}



