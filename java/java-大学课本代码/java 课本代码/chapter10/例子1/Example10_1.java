import java.io.*;
public class Example10_1 {
   public static void main(String args[]) {
      File f = new File("C:\\ch10","Example10_1.java");
      System.out.println(f.getName()+"�ǿɶ�����:"+f.canRead());
      System.out.println(f.getName()+"�ĳ���:"+f.length());
      System.out.println(f.getName()+"�ľ���·��:"+f.getAbsolutePath());
      File file = new File("new.txt");
      System.out.println("�ڵ�ǰĿ¼�´������ļ�"+file.getName());
      if(!file.exists()) {
         try {
              file.createNewFile();
              System.out.println("�����ɹ�");
         }
         catch(IOException exp){}
      }
   }
}
