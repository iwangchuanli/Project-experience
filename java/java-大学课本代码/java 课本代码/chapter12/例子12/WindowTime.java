import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.Date;
import java.text.SimpleDateFormat;
public class WindowTime extends JFrame implements ActionListener {
   JTextField text;
   JButton bStart,bStop,bContinue;
   Timer time;
   SimpleDateFormat m;
   int n=0,start=1;
   WindowTime() {
      time=new Timer(1000,this);//WindowTime��������ʱ���ļ�����
      m=new SimpleDateFormat("hh:mm:ss");
      text=new JTextField(10); 
      bStart=new JButton("��ʼ");
      bStop=new JButton("��ͣ");
      bContinue=new JButton("����");
      bStart.addActionListener(this);
      bStop.addActionListener(this);
      bContinue.addActionListener(this);
      setLayout(new FlowLayout());
      add(bStart);
      add(bStop);
      add(bContinue);
      add(text);
      setSize(500,500);
      validate();
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   public void actionPerformed(ActionEvent e) { 
      if(e.getSource()==time) {
        Date date=new Date();
        text.setText("ʱ�䣺"+m.format(date));
        int x=text.getBounds().x;
        int y=text.getBounds().y;  
        y=y+2;
        x=x-2;
        text.setLocation(x,y);
      } 
      else if(e.getSource()==bStart)
        time.start();    
      else if(e.getSource()==bStop)
        time.stop();    
      else if(e.getSource()==bContinue)
        time.restart();    
   }
} 
