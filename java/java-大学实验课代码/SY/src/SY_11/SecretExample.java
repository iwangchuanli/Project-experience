package SY_11;

import java.io.*;
public class SecretExample
{  
   public static void main(String args[ ])
   { 
         File fileOne=new File("hello.txt"),
         fileTwo=new File("hello.secret");
      char b[]=new char[100];
      try{
      FileReader in=new FileReader(fileOne) ;//创建指向fileOne的字符输入流
      FileWriter  out=new FileWriter(fileTwo);//创建指向fileTwo字符输出流。
           int n=-1;
           while((n=in.read(b))!=-1)
            {
                for(int i=0;i<n;i++)
                 {
                    b[i]=(char)(b[i]^"a");
                 }
      out.flush();;// out将数组b的前n单元写到文件。
            }
      out.close(); //out关闭。
           in=new FileReader(fileTwo) ;//创建指向fileTwo的字符输入流。
           System.out.println("加密后的文件内容:");
           while((n=in.read(b))!=-1)
           {
               String str=new String(b,0,n);
               System.out.println(str);
           }
           in=new FileReader(fileTwo);//创建指向fileTwo的字符输入流。 
           System.out.println("解密后的文件内容:");
           while((n=in.read(b))!=-1)
            {
                for(int i=0;i<n;i++)
                 {
                    b[i]=(char)(b[i]^"a");
                 }
                System.out.printf(new String(b,0,n));
            }  
           in.close();//in关闭。         
         }
      catch(IOException e)
         {
           System.out.println(e);
         }  
   }
}
 
