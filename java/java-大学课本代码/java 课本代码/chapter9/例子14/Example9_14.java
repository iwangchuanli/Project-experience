import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Example9_14 {
   public static void main(String args[]) {
      WindowButton win=new WindowButton("������");
   }
}
class WindowButton extends JFrame implements ActionListener {
   int number;
   JLabel hintLabel;
   JTextField inputGuess;
   JButton buttonGetNumber,buttonEnter;   
   WindowButton(String s) {
      super(s);
      addWindowListener( new WindowAdapter(){ //�������ʵ�����Ӵ����¼�
                            public void windowClosing(WindowEvent e) {
                                dispose();
                            }
                         } 
                       );  
      setLayout(new FlowLayout());
      buttonGetNumber=new JButton("�õ�һ�������");
      add(buttonGetNumber);
      hintLabel=new JLabel("������Ĳ²⣺",JLabel.CENTER);
      hintLabel.setBackground(Color.cyan);
      inputGuess=new JTextField("0",10); 
      add(hintLabel);
      add(inputGuess);
      buttonEnter=new JButton("ȷ��"); 
      add(buttonEnter); 
      buttonEnter.addActionListener(this);
      buttonGetNumber.addActionListener(this);
      setBounds(100,100,150,150);
      setVisible(true);
      validate();
    }
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()==buttonGetNumber) {
            number=(int)(Math.random()*100)+1;
            hintLabel.setText("������Ĳ²⣺");
         }
         else if(e.getSource()==buttonEnter) {
            int guess=0;
            try {  guess=Integer.parseInt(inputGuess.getText());
                   if(guess==number) {
                      hintLabel.setText("�¶��ˣ�");
                   }
                   else if(guess>number) {
                      hintLabel.setText("�´��ˣ�");
                      inputGuess.setText(null); 
                   }
                   else if(guess<number) {
                       hintLabel.setText("��С�ˣ�");
                       inputGuess.setText(null); 
                   }
               }
             catch(NumberFormatException event) {
                   hintLabel.setText("�����������ַ�");
             } 
         }
    } 
}
