import java.io.*;
public class Example10_12 {
   public static void main(String args[]) {
      String command = "�ɽ��ܹ�ʱ����4��22����10��";
      EncryptAndDecrypt person = new EncryptAndDecrypt();
      String password = "Tiger";
      String secret = person.encrypt(command,password); 
      File file=new File("secret.txt");
      try{
          FileOutputStream fos=new FileOutputStream(file);
          DataOutputStream outData=new DataOutputStream(fos);
          outData.writeUTF(secret);
          System.out.println("��������:"+secret);
       } 
       catch(IOException e){}
       try{
          FileInputStream fis=new FileInputStream(file);
          DataInputStream inData=new DataInputStream(fis);
          String str = inData.readUTF();
          String mingwen = person.decrypt(str,password);
          System.out.println("��������:"+mingwen);       
       }
       catch(IOException e){}
   }
}
