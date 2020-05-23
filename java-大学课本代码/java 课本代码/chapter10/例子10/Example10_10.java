import java.io.*;
public class Example10_10 {
   public static void main(String args[]) {
      try {
         ByteArrayOutputStream outByte=new ByteArrayOutputStream();
         byte [] byteContent="mid-autumn festival".getBytes(); 
         outByte.write(byteContent); 
         ByteArrayInputStream inByte=new ByteArrayInputStream(outByte.toByteArray());
         byte backByte []=new byte[outByte.toByteArray().length];
         inByte.read(backByte);
         System.out.println(new String(backByte));
         CharArrayWriter outChar=new CharArrayWriter();
         char [] charContent="ÖÐÇï¿ìÀÖ".toCharArray(); 
         outChar.write(charContent); 
         CharArrayReader inChar=new CharArrayReader(outChar.toCharArray());
         char backChar []=new char[outChar.toCharArray().length];
         inChar.read(backChar);
         System.out.println(new String(backChar));
      }
      catch(IOException exp){}
  }
}
