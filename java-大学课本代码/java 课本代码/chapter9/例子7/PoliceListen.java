import java.awt.event.*;
import javax.swing.*;
public class PoliceListen implements MyCommandListener {
   JTextField textInput;
   JTextArea textShow;
   public void setJTextField(JTextField text) {
      textInput = text;
   }
   public void setJTextArea(JTextArea area) {
      textShow = area;
   } 
   public void actionPerformed(ActionEvent e) {
      String str=textInput.getText();
      textShow.append(str+"µÄ³¤¶È:"+str.length()+"\n");

   }
}
