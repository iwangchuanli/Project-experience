import java.awt.event.*;
import javax.swing.*;
public class ComputerListener implements ActionListener {
   JTextField inputNumberOne,inputNumberTwo; 
   JTextArea textShow;
   String fuhao;
   public void setJTextFieldOne(JTextField t) {
       inputNumberOne = t;
   }
   public void setJTextFieldTwo(JTextField t) {
       inputNumberTwo = t;
   }
   public void setJTextArea(JTextArea area) {
       textShow = area;
   }
   public void setFuhao(String s) {
       fuhao = s;
   }
   public void actionPerformed(ActionEvent e) {
      try {
            double number1=Double.parseDouble(inputNumberOne.getText());
            double number2=Double.parseDouble(inputNumberTwo.getText());
            double result = 0;
            if(fuhao.equals("+")) {
                result = number1+number2;
            }
            else if(fuhao.equals("-")) {
                result = number1-number2;
            }
            else if(fuhao.equals("*")) {
                result = number1*number2;
            }
            else if(fuhao.equals("/")) {
                result = number1/number2;
            }
            textShow.append(number1+" "+fuhao+" "+number2+" = "+result+"\n");
      }
      catch(Exception exp) {
             textShow.append("\nÇëÊäÈëÊý×Ö×Ö·û\n");
      }         
   }
}
