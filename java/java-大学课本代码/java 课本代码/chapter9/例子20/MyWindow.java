import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MyWindow extends JFrame implements ActionListener {
   JButton button; 
   MyDialog dialog;
   MyWindow() {
      init();
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   void init() {
      button=new JButton("打开对话框"); 
      button.addActionListener(this);
      add(button,BorderLayout.NORTH); 
      dialog=new MyDialog(this,"我是对话框");  //对话框依赖于MyWindow创建的窗口
      dialog.setModal(true);   //有模式对话框  
   }
   public void actionPerformed(ActionEvent e) {
        dialog.setVisible(true); 
        String str = dialog.getTitle();
        setTitle(str);
   }
}
