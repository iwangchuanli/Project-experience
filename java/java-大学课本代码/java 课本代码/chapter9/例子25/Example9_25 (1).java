import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Person extends JButton implements FocusListener { 
   int number;
   Color c=new Color(255,245,170);
   Font font=new Font("ËÎÌו",Font.BOLD,12);
   Person(int number,String s) {
       super(s);
       setBackground(c);
       setFont(font);
       this.number=number;
       c=getBackground();
       addFocusListener(this);
   }
   public void focusGained(FocusEvent e) {
      setBackground(Color.red);
   }
   public void focusLost(FocusEvent e) {
      setBackground(c);
   }
}