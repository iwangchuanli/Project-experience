import java.awt.*; 
import java.net.*;
import java.awt.event.*;
import java.io.*;
import java.applet.*;
import javax.swing.*;
public class AudioClipDialog extends JDialog implements Runnable,ItemListener,ActionListener {
   Thread thread;
   JComboBox choiceMusic;
   AudioClip clip;
   JButton buttonPlay,
           buttonLoop,
           buttonStop;
   String str;
   AudioClipDialog() {
     thread=new Thread(this);
     choiceMusic=new JComboBox();
     choiceMusic.addItem("ѡ����Ƶ�ļ�");
     choiceMusic.addItem("ding.wav");
     choiceMusic.addItem("notify.wav");
     choiceMusic.addItem("online.wav");
     choiceMusic.addItemListener(this);
     buttonPlay=new JButton("����");
     buttonLoop=new JButton("ѭ��");
     buttonStop=new JButton("ֹͣ");
     buttonPlay.addActionListener(this);
     buttonStop.addActionListener(this);
     buttonLoop.addActionListener(this); 
     setLayout(new FlowLayout());
     add(choiceMusic);
     add(buttonPlay);
     add(buttonLoop);
     add(buttonStop);
     setSize(350,120);
     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   } 
   public void itemStateChanged(ItemEvent e) {
      str=choiceMusic.getSelectedItem().toString();
      if(!(thread.isAlive())) {
         thread=new Thread(this);
      }
      try{  thread.start();
      }
      catch(Exception ee){}
   }
   public void run() {
      try{  File file=new File(str);
            URI uri=file.toURI();
            URL url=uri.toURL();
            clip=Applet.newAudioClip(url);
      }
      catch(Exception e){}
   }
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==buttonPlay)
          clip.play();
      else if(e.getSource()==buttonLoop)
          clip.loop();
      else if(e.getSource()==buttonStop)
          clip.stop();
  }
}

