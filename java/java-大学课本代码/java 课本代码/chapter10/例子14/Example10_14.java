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
   JButton ����=null,д��=null;
   ByteArrayOutputStream out = null;
   MyWin() {
      setLayout(new FlowLayout()); 
      label=new JLabel("How are you");
      ����=new JButton("�������"); 
      д��=new JButton("д������");
      ����.addActionListener(this);
      д��.addActionListener(this);
      setVisible(true); 
      add(label);
      add(д��);
      add(����);
      setSize(500,400);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      validate();
   }
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==д��) {
          try{  out = new ByteArrayOutputStream();
                ObjectOutputStream objectOut = new ObjectOutputStream(out);
                objectOut.writeObject(label);               
                objectOut.close();
          }
          catch(IOException event){}
      }
      else if(e.getSource()==����) {
          try{  ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
                ObjectInputStream objectIn = new ObjectInputStream(in);
                JLabel temp=(JLabel)objectIn.readObject();
                temp.setText("���"); 
                this.add(temp);
                this.validate();
                objectIn.close();
          }
          catch(Exception event){}
      }
   }
}



