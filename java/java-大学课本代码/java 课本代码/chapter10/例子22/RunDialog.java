import java.io.*;
import javax.swing.*;
import java.awt.*;
public class RunDialog  extends JDialog {
    JTextArea showOut;
    RunDialog() {
       setTitle("���жԻ���");
       showOut = new JTextArea();
       Font f =new Font("����",Font.BOLD,15);
       showOut.setFont(f);
       add(new JScrollPane(showOut),BorderLayout.CENTER);
       setBounds(210,10,500,300);
    } 
    public void run(String name) {
       try{  Runtime ce=Runtime.getRuntime();
             Process proccess = ce.exec("java "+name);
             InputStream in=proccess.getInputStream();
             BufferedInputStream bin=new BufferedInputStream(in);
             int n;
             boolean bn=true;
             byte mess[]=new byte[100];
             while((n=bin.read(mess,0,100))!=-1) {
                   String s=null;
                   s=new String(mess,0,n);
                   showOut.append(s);
                   if(s!=null) bn = false;
                   if(bn)  showOut.setText("Java������ûʹ��out�������Ϣ"); 
             } 
        }
        catch(IOException e1){} 
    }
}