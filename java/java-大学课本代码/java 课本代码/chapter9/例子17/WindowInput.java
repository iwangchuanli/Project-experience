import java.awt.event.*; 
import java.awt.*;
import javax.swing.*;
import java.util.*;
public class WindowInput extends JFrame implements ActionListener {
   JTextArea showResult;
   JButton openInput;
   WindowInput() {
       openInput=new JButton("��������Ի���");
       showResult=new JTextArea();
       add(openInput,BorderLayout.NORTH);
       add(new JScrollPane(showResult),BorderLayout.CENTER);
       openInput.addActionListener(this); 
       setVisible(true);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   public void actionPerformed(ActionEvent e) { 
      String str=JOptionPane.showInputDialog(this,"��������,�ÿո�ָ�","����Ի���",
                                              JOptionPane.PLAIN_MESSAGE);
      if(str!=null) {
         Scanner scanner = new Scanner(str);
         double sum=0;
         int k=0;
         while(scanner.hasNext()){
            try{
               double number=scanner.nextDouble();
               if(k==0)
                  showResult.append(""+number);
               else
                  showResult.append("+"+number);
               sum=sum+number;
               k++;
            } 
            catch(InputMismatchException exp){
               String t=scanner.next();
            }
         }
         showResult.append("="+sum+"\n");   
      }
   }
}
