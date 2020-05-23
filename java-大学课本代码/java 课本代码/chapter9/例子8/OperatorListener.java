import java.awt.event.*;
import javax.swing.*;
public class OperatorListener implements ItemListener {
   JComboBox choice;
   ComputerListener workTogether;
   public void setJComboBox(JComboBox box) {
      choice = box;
   }
   public void setWorkTogether(ComputerListener computer) {
      workTogether = computer;
   }
   public void itemStateChanged(ItemEvent e)  {
      String fuhao = choice.getSelectedItem().toString();
      workTogether.setFuhao(fuhao);
   }
}
