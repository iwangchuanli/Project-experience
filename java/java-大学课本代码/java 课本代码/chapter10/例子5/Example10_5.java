import java.io.*;
public class Example10_5 {
   public static void main(String args[]) {
      byte [] a = "�������".getBytes();
      byte [] b = "Happy New Year".getBytes();
      File file = new File("a.txt");                         //�����Ŀ�ĵ�
      try{  
         OutputStream out=new FileOutputStream(file);      //ָ��Ŀ�ĵص������
         System.out.println(file.getName()+"�Ĵ�С:"+file.length()+"�ֽ�");//a.txt�Ĵ�С:0�ֽ�
         out.write(a);                                    //��Ŀ�ĵ�д����
         out.close();
         out=new FileOutputStream(file,true);             //׼�����ļ�β������
         System.out.println(file.getName()+"�Ĵ�С:"+file.length()+"�ֽ�");///a.txt�Ĵ�С:8�ֽ�
         out.write(b,0,b.length); 
         System.out.println(file.getName()+"�Ĵ�С:"+file.length()+"�ֽ�");///a.txt�Ĵ�С:8�ֽ�
         out.close();
      }
      catch(IOException e) {
          System.out.println("Error "+e);
      }
  }
}
