import java.awt.event.*; 
import java.awt.*;
import javax.swing.*;
public class WindowMess extends JFrame implements ActionListener {
   JTextField inputEnglish;
   JTextArea show;
   String regex = "[a-zZ-Z]+"; 
   WindowMess() {
       inputEnglish=new JTextField(22);
       inputEnglish.addActionListener(this);
       show=new JTextArea(); 
       add(inputEnglish,BorderLayout.NORTH);
       add(show,BorderLayout.CENTER); 
       setVisible(true);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   public void actionPerformed(ActionEvent e) { 
       if(e.getSource()==inputEnglish) {
           String str=inputEnglish.getText();
           if(str.matches(regex)) {
              show.append(str+",");
           }
           else { //弹出“警告”消息对话框。
               JOptionPane.showMessageDialog(this,"您输入了非法字符","消息对话框",
                                             JOptionPane.WARNING_MESSAGE);
               inputEnglish.setText(null); 
           }  
      }
   }
}
