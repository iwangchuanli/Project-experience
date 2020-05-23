import java.io.*;
public class Example10_9 {
   public static void main(String args[]) {
      RandomAccessFile in=null;
      try{ in=new RandomAccessFile("Example10_9.java","rw");
           long length=in.length();  //获取文件的长度
           long position=0;
           in.seek(position);       //将读取位置定位到文件的起始 
           while(position<length) {
              String str=in.readLine();
              byte b[]=str.getBytes("iso-8859-1");
              str=new String(b);
              position=in.getFilePointer();
              System.out.println(str);
           } 
      }
      catch(IOException e){} 
   }
}

