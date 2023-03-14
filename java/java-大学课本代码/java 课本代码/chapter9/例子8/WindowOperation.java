import java.awt.*;
import javax.swing.*;
import java.io.*;
public class WindowOperation extends JFrame {
   JTextField inputNumberOne,inputNumberTwo; 
   JComboBox choiceFuhao;
   JTextArea textShow;
   JButton button;
   OperatorListener operator;  //����ItemEvent�¼��ļ�����
   ComputerListener computer;  //����ActionEvent�¼��ļ�����
   public WindowOperation() {
      init();
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   void init() {
      setLayout(new FlowLayout());
      inputNumberOne = new JTextField(5);
      inputNumberTwo = new JTextField(5);
      choiceFuhao = new JComboBox();
      button = new JButton("����"); 
      choiceFuhao.addItem("ѡ���������:");
      String [] a = {"+","-","*","/"};
      for(int i=0;i<a.length;i++) {
          choiceFuhao.addItem(a[i]);
      }
      textShow = new JTextArea(9,30);
      operator = new OperatorListener();
      computer = new ComputerListener();
      operator.setJComboBox(choiceFuhao);
      operator.setWorkTogether(computer);
      computer.setJTextFieldOne(inputNumberOne);
      computer.setJTextFieldTwo(inputNumberTwo);
      computer.setJTextArea(textShow);
      choiceFuhao.addItemListener(operator);   //choiceFuhao���¼�Դ,operator�Ǽ�����
      button.addActionListener(computer);     //button���¼�Դ,computer�Ǽ�����
      add(inputNumberOne);
      add(choiceFuhao);
      add(inputNumberTwo);
      add(button);
      add(new JScrollPane(textShow));
   }
}
