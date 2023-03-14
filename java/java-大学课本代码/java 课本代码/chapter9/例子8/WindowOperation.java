import java.awt.*;
import javax.swing.*;
import java.io.*;
public class WindowOperation extends JFrame {
   JTextField inputNumberOne,inputNumberTwo; 
   JComboBox choiceFuhao;
   JTextArea textShow;
   JButton button;
   OperatorListener operator;  //监视ItemEvent事件的监视器
   ComputerListener computer;  //监视ActionEvent事件的监视器
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
      button = new JButton("计算"); 
      choiceFuhao.addItem("选择运算符号:");
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
      choiceFuhao.addItemListener(operator);   //choiceFuhao是事件源,operator是监视器
      button.addActionListener(computer);     //button是事件源,computer是监视器
      add(inputNumberOne);
      add(choiceFuhao);
      add(inputNumberTwo);
      add(button);
      add(new JScrollPane(textShow));
   }
}
