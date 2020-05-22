package cn.liulanqi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.event.*;
@SuppressWarnings("serial")
class Win3 extends JFrame implements ActionListener,Runnable
{  
   JButton button;
   URL url;
   JTextField text;
   JEditorPane editPane; 
   byte b[]=new byte[118];
   Thread thread;
   public Win3()
   {  
      text=new JTextField(20);
      editPane=new JEditorPane();
      editPane.setEditable(false);
      button=new JButton("确定");
      button.addActionListener(this);
      thread=new Thread(this);
      JPanel p=new JPanel();
      p.add(new JLabel("输入网址:"));
      p.add(text); 
      p.add(button);
      Container con=getContentPane();
      con.add(new JScrollPane(editPane),BorderLayout.CENTER);
      con.add(p,BorderLayout.NORTH);
      setBounds(60,60,400,300);
      setVisible(true);
      validate();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      editPane.addHyperlinkListener(new HyperlinkListener()
                                         {
                                           public void hyperlinkUpdate(HyperlinkEvent e) 
                                            { 
                                              if(e.getEventType()==
                                                HyperlinkEvent.EventType.ACTIVATED)
                                                { 
                                                  try{
                                                       editPane.setPage(e.getURL());
                                                     }     
                                                  catch(IOException e1)
                                                     {
                                                        editPane.setText(""+e1);
                                                     }
                                                 }
                                            } 
                                         }
                                     );
   }
   public void actionPerformed(ActionEvent e)
   { 
      if(!(thread.isAlive())) 
         thread=new Thread(this);
      try{
           thread.start();
         }
      catch(Exception ee)
         {
            text.setText("我正在读取"+url);
         }
   }
  public void run()
   {
       try {    
                 int n=-1;
                 editPane.setText(null);
                 url=new URL(text.getText().trim());
                 editPane.setPage(url);
            }
          catch(MalformedURLException e1)
           {  
                 text.setText(""+e1);
                 return;
           }
          catch(IOException e1)
           {  
                 text.setText(""+e1);
                 return;
           }  
   }
}
public class Example3
{ 
    public static void main(String args[])
    { 
       new Win3();
    }
}