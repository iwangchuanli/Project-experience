import java.awt.event.*;
public class ReaderListen implements ActionListener { 
   public void actionPerformed(ActionEvent e) {
      String str=e.getActionCommand();   //��ȡ��װ���¼��еġ�����ַ���
      System.out.println(str+":"+str.length());
   }
}
