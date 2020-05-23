import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class WindowEnter extends JFrame implements ActionListener {
   JTextField inputName; 
   JTextArea  save;
   WindowEnter() {
      inputName=new JTextField(22);
      inputName.addActionListener(this);
      save=new JTextArea();
      add(inputName,BorderLayout.NORTH); 
      add(new JScrollPane(save),BorderLayout.CENTER); 
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   public void actionPerformed(ActionEvent e) {
      String s=inputName.getText();
      int n=JOptionPane.showConfirmDialog(this,"确认是否正确","确认对话框",
                                      JOptionPane.YES_NO_OPTION );
      if(n==JOptionPane.YES_OPTION) {  
         save.append("\n"+s);
      }  
      else if(n==JOptionPane.NO_OPTION) { 
         inputName.setText(null);
      }
   }
}
