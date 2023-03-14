import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class StandardExamInTime extends JFrame implements ActionListener,ItemListener{
   File testFile;
   int MAX = 8; 
   int maxTime = MAX,score=0;
   javax.swing.Timer time;  //��ʱ��
   JTextArea  showQuesion; //��ʾ����
   JCheckBox choiceA,choiceB,choiceC,choiceD;
   JLabel showScore,showTime;
   String correctAnswer;   //��ȷ��
   JButton reStart;
   FileReader inOne;
   BufferedReader inTwo;
   StandardExamInTime(){
      time = new javax.swing.Timer(1000,this);
      showQuesion = new JTextArea(2,16);
      setLayout(new FlowLayout());
      showScore=new JLabel("����"+score);
      showTime=new JLabel(" ");
      add(showTime);
      add(new JLabel("����:")) ;
      add(showQuesion);
      choiceA =new JCheckBox("A");
      choiceB =new JCheckBox("B");
      choiceC =new JCheckBox("C"); 
      choiceD =new JCheckBox("D");
      choiceA.addItemListener(this);
      choiceB.addItemListener(this);
      choiceC.addItemListener(this);
      choiceD.addItemListener(this); 
      add(choiceA);
      add(choiceB);
      add(choiceC);
      add(choiceD);
      add(showScore); 
      reStart=new JButton("����һ��");
      reStart.setEnabled(false);
      add(reStart);
      reStart.addActionListener(this);
      setBounds(100,100,200,200);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   } 
   public void setMAX(int n){
      MAX = n;
   }
   public void setTestFile(File f) {
      testFile = f;
      score=0;
      try{
         inOne = new FileReader(testFile);
         inTwo = new BufferedReader(inOne);
         readOneQuesion();
         reStart.setEnabled(false); 
      }
      catch(IOException exp){
         showQuesion.setText("û��ѡ��");
      }
   }
   public void readOneQuesion() {
      showQuesion.setText(null);
      try {
           String s = null;
           while((s = inTwo.readLine())!=null) {
              if(!s.startsWith("-"))
                  showQuesion.append("\n"+s);
              else {
                  s = s.replaceAll("-",""); 
                  correctAnswer = s;
                  break;
              }
           }
           time.start();   //������ʱ
           if(s==null) {
              inTwo.close();
              reStart.setEnabled(true);
              showQuesion.setText("��Ŀ���");
              time.stop();
           }
      } 
      catch(IOException exp){}
   }
   public void itemStateChanged(ItemEvent e) {
      JCheckBox box=(JCheckBox)e.getSource();
      String str=box.getText();
      boolean booOne=box.isSelected(); 
      boolean booTwo=str.compareToIgnoreCase(correctAnswer)==0;
      if(booOne&&booTwo){
           score++;
           showScore.setText("����:"+score);
           time.stop();       //ֹͣ��ʱ
           maxTime = MAX;
           readOneQuesion(); //������һ����Ŀ  
      }
      box.setSelected(false);
   }
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==time){
            showTime.setText("ʣ:"+maxTime+"��");
            maxTime--;
            if(maxTime <= 0){
                maxTime = MAX;
                readOneQuesion(); //������һ����Ŀ
            }
      }
      else if(e.getSource()==reStart) {
         setTestFile(testFile);
      }
   }
}
