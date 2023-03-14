import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class WindowTriangle extends JFrame implements ActionListener {
   Triangle triangle;              //数据对象
   JTextField textA,textB,textC;   //数据对象的视图
   JTextArea showArea;            //数据对象的视图
   JButton controlButton;         //控制器对象
   WindowTriangle() {
      init();
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   void init() {
     triangle=new Triangle();
     textA=new JTextField(5);   
     textB=new JTextField(5);
     textC=new JTextField(5);
     showArea=new JTextArea();    
     controlButton=new JButton("计算面积");
     JPanel pNorth=new JPanel();
     pNorth.add(new JLabel("边A:"));
     pNorth.add(textA);
     pNorth.add(new JLabel("边B:"));
     pNorth.add(textB);
     pNorth.add(new JLabel("边C"));
     pNorth.add(textC); 
     pNorth.add(controlButton); 
     controlButton.addActionListener(this);
     add(pNorth,BorderLayout.NORTH);
     add(new JScrollPane(showArea),BorderLayout.CENTER);
   }
   public void actionPerformed(ActionEvent e) {
     try{  
        double a=Double.parseDouble(textA.getText().trim());   
        double b=Double.parseDouble(textB.getText().trim());      
        double c=Double.parseDouble(textC.getText().trim()); 
        triangle.setA(a) ;          //更新数据
        triangle.setB(b);
        triangle.setC(c);
        String area=triangle.getArea();     
        showArea.append("三角形"+a+","+b+","+c+"的面积:"); 
        showArea.append(area+"\n");  //更新视图
     } 
     catch(Exception ex) {
        showArea.append("\n"+ex+"\n");
     }
   }
}
