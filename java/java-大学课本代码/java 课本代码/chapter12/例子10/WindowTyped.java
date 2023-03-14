import java.awt.*;
import java.awt.event.*;   
import javax.swing.*;
public class WindowTyped extends JFrame implements ActionListener,Runnable {
   JTextField inputLetter;
   Thread giveLetter;       //负责给出字母
   JLabel showLetter,showScore;
   int sleepTime,score;
   Color c;
   WindowTyped() {
      setLayout(new FlowLayout());
      giveLetter=new Thread(this);
      inputLetter=new JTextField(6);
      showLetter =new JLabel(" ",JLabel.CENTER);
      showScore  = new JLabel("分数:");
      showLetter.setFont(new Font("Arial",Font.BOLD,22));
      add(new JLabel("显示字母:")); 
      add(showLetter);
      add(new JLabel("输入所显示的字母(回车)"));
      add(inputLetter); 
      add(showScore);
      inputLetter.addActionListener(this);
      setBounds(100,100,400,280);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      giveLetter.start();    //在AWT-Windows线程中启动giveLetter线程
   }
   public void run() {
      char c ='a';
      while(true) {
         showLetter.setText(""+c+" ");
         validate();
         c = (char)(c+1);
         if(c>'z') c = 'a';
         try{ Thread.sleep(sleepTime);
         } 
         catch(InterruptedException e){}
      }
   }
   public void setSleepTime(int n){
      sleepTime = n;
   }
   public void actionPerformed(ActionEvent e) {
      String s = showLetter.getText().trim();
      String letter = inputLetter.getText().trim();
      if(s.equals(letter)) {
         score++;
         showScore.setText("得分"+score);
         inputLetter.setText(null);
         validate();
         giveLetter.interrupt();  //吵醒休眠的线程，以便加快出字母的速度
      }
   }
}
