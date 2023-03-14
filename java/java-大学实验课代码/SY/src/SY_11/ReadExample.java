package SY_11;

import java.io.*;
public class ReadExample
{  
   public static void main(String args[ ])
   { 
      File file=new File("e:/java","hello.txt");
      File tempFile=new File("e:/java/temp.text");
      try{
    
    FileReader  inOne=new FileReader(file);// 创建指向文件file的输入流。
    BufferedReader inTwo=new  BufferedReader(inOne);// 创建指向inOne file的输入流。
    FileWriter  tofile=new FileWriter(tempFile);// 创建指向文件tempFile的输出流。
    BufferedWriter out=new BufferedWriter(tofile);// 创建指向tofile的输出流。
           String s=null;
           int i=0;
           s=inTwo.readLine();//inTwo读取一行。
           while(s!=null)
           {
               i++;
               out.write(i+" "+s);
               out.newLine();
               s=inTwo.readLine();//inTwo读取一行。
           }
           inOne.close();
           inTwo.close();
           out.flush();
           out.close();
           tofile.close();
           inOne=new FileReader(tempFile);// 创建指向文件tempFile的输入流
           inTwo= new BufferedReader(inOne);// 创建指向inOne file的输入流。
           tofile=new FileWriter(file);// 创建指向文件file的输出流。
           out=new BufferedWriter(tofile);// 创建指向tofile的输出流。
           while((s=inTwo.readLine())!=null)  ////inTwo读取一行。
           {
              out.write(s);
              out.newLine();
           } 
           inOne.close();
           inTwo.close();
           out.flush();
           out.close();
           tofile.close();
           inOne=new FileReader(file);// 创建指向文件file的输入流。
           inTwo=new BufferedReader(inOne);// 创建指向inOne file的输入流。
          while((s=inTwo.readLine())!=null)  ////inTwo读取一行。
           {
             System.out.println(s);
           } 
           inOne.close();
           inTwo.close(); 
           //tempFile.delete(); 
      }
      catch(IOException e)
      {
           System.out.println(e);
      }  
   }




}
