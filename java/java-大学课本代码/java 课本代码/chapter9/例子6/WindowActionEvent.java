import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class WindowActionEvent extends JFrame { 
   JTextField text;
   ActionListener listener;             //listener�Ǽ�����
   public WindowActionEvent() {
      setLayout(new FlowLayout());
      text = new JTextField(10); 
      add(text);
      listener = new ReaderListen();       //����������ַ������ȵļ�����
      text.addActionListener(listener);   //text���¼�Դ,listener�Ǽ�����
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}
