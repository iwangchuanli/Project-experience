import java.io.*;
import javax.swing.*;
import java.awt.*;
public class CompileDialog  extends JDialog {
    JTextArea showError;
    CompileDialog() {
       setTitle("编译对话框");
       showError = new JTextArea();
       Font f =new Font("宋体",Font.BOLD,15);
       showError.setFont(f);
       add(new JScrollPane(showError),BorderLayout.CENTER);
       setBounds(10,10,500,300);
    } 
    public void compile(String name) {
       try{  Runtime ce=Runtime.getRuntime();
             Process proccess = ce.exec("javac "+name);
             InputStream in=proccess.getErrorStream();
             BufferedInputStream bin=new BufferedInputStream(in);
             int n;
             boolean bn=true;
             byte error[]=new byte[100];
             while((n=bin.read(error,0,100))!=-1) {
                   String s=null;
                   s=new String(error,0,n);
                   showError.append(s);
                   if(s!=null) bn=false;
             }
             if(bn)  showError.append("编译正确"); 
        }
        catch(IOException e1){} 
    }
}