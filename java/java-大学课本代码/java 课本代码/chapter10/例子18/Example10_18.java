import javax.swing.*; 
import java.io.*;
public class Example10_18 {
   public static void main(String args[]) {
      byte b[]=new byte[30];
      try{  FileInputStream input=new FileInputStream("Example10_18.java");
            ProgressMonitorInputStream in=
            new ProgressMonitorInputStream(null,"��ȡjava�ļ�",input);
            ProgressMonitor p=in.getProgressMonitor();  //��ý�����
            while(in.read(b)!=-1) {
               String s=new String(b);
               System.out.print(s);
               Thread.sleep(1000);//�����ļ���С��Ϊ�˿�����������������ӻ�1��
            }
       }
       catch(Exception e){}
   }
}
