import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class MyFrame extends JFrame {
       Boy police;
       MyFrame(String s) {
       super(s);
       police=new Boy();
       setBounds(100,100,200,300);
       setVisible(true);
       addWindowListener(police);   //向窗口注册监视器
       validate();
    } 
}
class Boy extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
       System.exit(0);
    }
}
public class Example9_13 {
   public static void main(String args[]) {
         new MyFrame("窗口");
   } 
}
