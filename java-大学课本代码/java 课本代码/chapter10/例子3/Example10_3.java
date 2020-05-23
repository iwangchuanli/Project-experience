import java.io.*;
public class Example10_3 {
   public static void main(String args[]) {
      try{ 
           Runtime ce=Runtime.getRuntime();
           File file=new File("c:/windows","Notepad.exe");
           ce.exec(file.getAbsolutePath());
           file=new File("C:\\Program Files\\Internet Explorer","IEXPLORE www.sina.com ");
           ce.exec(file.getAbsolutePath());
      }
      catch(Exception e) {
         System.out.println(e);
      } 
   } 
}
