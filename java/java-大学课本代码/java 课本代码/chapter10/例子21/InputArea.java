import java.io.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
public class InputArea extends JPanel implements ActionListener {
   File f=null;
   RandomAccessFile out;
   Box baseBox,boxV1,boxV2; 
   JTextField name,email,phone;
   JButton button;
   InputArea(File f) {
       setBackground(Color.cyan);
       this.f=f;
       name=new JTextField(12);
       email=new JTextField(12); 
       phone=new JTextField(12);
       button=new JButton("录入");
       button.addActionListener(this);
       boxV1=Box.createVerticalBox();
       boxV1.add(new JLabel("输入姓名"));
       boxV1.add(Box.createVerticalStrut(8));
       boxV1.add(new JLabel("输入email"));
       boxV1.add(Box.createVerticalStrut(8));
       boxV1.add(new JLabel("输入电话"));
       boxV1.add(Box.createVerticalStrut(8));
       boxV1.add(new JLabel("单击录入"));
       boxV2=Box.createVerticalBox();
       boxV2.add(name);
       boxV2.add(Box.createVerticalStrut(8));
       boxV2.add(email);
       boxV2.add(Box.createVerticalStrut(8));
       boxV2.add(phone);
       boxV2.add(Box.createVerticalStrut(8));
       boxV2.add(button);
       baseBox=Box.createHorizontalBox();
       baseBox.add(boxV1);
       baseBox.add(Box.createHorizontalStrut(10));
       baseBox.add(boxV2);
       add(baseBox); 
   }
   public void actionPerformed(ActionEvent e) {
      try{
           RandomAccessFile out=new RandomAccessFile(f,"rw");
           if(f.exists())
              {  long length=f.length();
                 out.seek(length);
              }
           out.writeUTF("姓名:"+name.getText());
           out.writeUTF("eamil:"+email.getText());
           out.writeUTF("电话:"+phone.getText());
           out.close();
      }
      catch(IOException ee){}
   }
}
