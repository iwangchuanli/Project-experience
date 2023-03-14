import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class WindowFileLock extends JFrame implements ActionListener {
   JTextArea text;
   JButton button;
   File file;
   RandomAccessFile input;
   FileChannel channel;
   FileLock lock;
   WindowFileLock(File f) {
      file=f;
      try {
         input=new RandomAccessFile(file,"rw"); 
         channel=input.getChannel();
         lock=channel.tryLock();
      }
      catch(Exception exp){}
      text=new JTextArea();
      button=new JButton("∂¡»°“ª––");
      button.addActionListener(this);
      add(new JScrollPane(text),BorderLayout.CENTER);
      add(button,BorderLayout.SOUTH);
      setSize(300,400);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   public void actionPerformed(ActionEvent e) {
      try{  
           lock.release();
           String lineString=input.readLine();
           text.append("\n"+lineString);
           lock=channel.tryLock();
           if(lineString==null)
             input.close();
      }
      catch(Exception ee){}
  }
}
