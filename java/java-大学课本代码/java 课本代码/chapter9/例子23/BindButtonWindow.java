import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class BindButtonWindow extends JFrame {
    JButton button; 
    Police listener;
    BindButtonWindow(){
       setLayout(new FlowLayout());
       listener=new Police();
       int M = JComponent.WHEN_IN_FOCUSED_WINDOW;
       button = new JButton("单击我或按'A'键可以移动我");
       add(button);
       button.addActionListener(listener);
       InputMap inputmap = button.getInputMap(M);
       inputmap.put(KeyStroke.getKeyStroke("A"),"dog");
       ActionMap actionmap=button.getActionMap();
       actionmap.put("dog",listener);
       setVisible(true);
       setBounds(100,100,200,200);
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
    }
    class Police extends AbstractAction { //Police是内部类
       public void actionPerformed(ActionEvent e) {
          JButton b=(JButton)e.getSource();
          int x=b.getBounds().x; //获取按钮的位置 
          int y=b.getBounds().y;
          b.setLocation(x+10,y+10); //移动按钮 
       }
    }
}
