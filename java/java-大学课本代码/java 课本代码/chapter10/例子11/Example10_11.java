import java.io.*;
public class Example10_11 {
   public static void main(String args[]) {
      File file=new File("apple.txt");
      try{
          FileOutputStream fos=new FileOutputStream(file);
          DataOutputStream outData=new DataOutputStream(fos);
          outData.writeInt(100);
          outData.writeLong(123456);  
          outData.writeFloat(3.1415926f);
          outData.writeDouble(987654321.1234);
          outData.writeBoolean(true);
          outData.writeChars("How are you doing ");
       } 
       catch(IOException e){}
       try{
          FileInputStream fis=new FileInputStream(file);
          DataInputStream inData=new DataInputStream(fis);
          System.out.println(inData.readInt());    //读取int数据
          System.out.println(inData.readLong());   //读取long数据 
          System.out.println(+inData.readFloat()); //读取float数据
          System.out.println(inData.readDouble()); //读取double数据
          System.out.println(inData.readBoolean());//读取boolean数据
          char c = '\0';
          while((c=inData.readChar())!='\0') {       //'\0'表示空字符。
              System.out.print(c);
          } 
        }
        catch(IOException e){}
   }
}
