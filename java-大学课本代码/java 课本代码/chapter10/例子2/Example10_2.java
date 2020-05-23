import java.io.*;
public class Example10_2 {
   public static void main(String args[]) {
      File dirFile = new File(".");
      FileAccept fileAccept = new FileAccept();
      fileAccept.setExtendName("java");
      String fileName[] = dirFile.list(fileAccept);
      for(String name:fileName) {
          System.out.println(name);
      }
   }
}
