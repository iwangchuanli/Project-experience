import javax.swing.*;
import java.awt.*;
public class Example9_1 {
    public static void main(String args[]) {
       JFrame window1=new JFrame("��һ������");
       JFrame window2=new JFrame("�ڶ�������");
       Container con=window1.getContentPane();
       con.setBackground(Color.yellow) ;       //���ô��ڵı���ɫ
       window1.setBounds(60,100,188,108);
       window2.setBounds(260,100,188,108);
       window1.setVisible(true);
       window1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       window2.setVisible(true);
       window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}
