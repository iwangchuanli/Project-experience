import java.awt.*;
import javax.swing.*;
public class WindowMove extends JFrame { 
     LP layeredPane;
     WindowMove() {
      layeredPane = new LP();
      add(layeredPane,BorderLayout.CENTER);
      setVisible(true);
      setBounds(12,12,300,300);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}


