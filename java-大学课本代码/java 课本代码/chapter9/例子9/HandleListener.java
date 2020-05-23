import java.awt.event.*;
import javax.swing.*;
public class HandleListener implements ActionListener {
   JTextArea inputText,showText;
   public void setInputText(JTextArea text) {
      inputText = text;
   }
   public void setShowText(JTextArea text) {
      showText = text;
   } 
   public void actionPerformed(ActionEvent e) {
      String str=e.getActionCommand(); 
      if(str.equals("copy"))
         showText.copy();
      else if(str.equals("cut"))
         showText.cut();
      else if(str.equals("paste"))
         inputText.paste();
   }
}
