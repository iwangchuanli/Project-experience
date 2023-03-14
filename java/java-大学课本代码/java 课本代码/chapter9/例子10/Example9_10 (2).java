import java.awt.*;
import javax.swing.*;
public class WindowMouse extends JFrame {
   JTextField text; 
   JButton button;
   JTextArea textArea;
   MousePolice police; 
   WindowMouse() {
      init();
      setBounds(100,100,420,220);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
   }
   void init() {
      setLayout(new FlowLayout());
      text=new JTextField(8);
      textArea=new JTextArea(5,28);
      police=new MousePolice();
      police.setJTextArea(textArea); 
      text.addMouseListener(police);
      button=new JButton("°´Å¥"); 
      button.addMouseListener(police);
      addMouseListener(police);
      add(button);
      add(text);
      add(new JScrollPane(textArea));
   }
}
