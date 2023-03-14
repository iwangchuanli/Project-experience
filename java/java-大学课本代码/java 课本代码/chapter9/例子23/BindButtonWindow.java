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
       button = new JButton("�����һ�'A'�������ƶ���");
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
    class Police extends AbstractAction { //Police���ڲ���
       public void actionPerformed(ActionEvent e) {
          JButton b=(JButton)e.getSource();
          int x=b.getBounds().x; //��ȡ��ť��λ�� 
          int y=b.getBounds().y;
          b.setLocation(x+10,y+10); //�ƶ���ť 
       }
    }
}
