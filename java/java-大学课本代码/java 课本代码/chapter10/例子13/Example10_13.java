import java.io.*;
public class Example10_13 {
   public static void main(String args[]) {
      TV changhong = new TV();
      changhong.setName("�������");
      changhong.setPrice(5678); 
      File file=new File("television.txt");
      try{ 
          FileOutputStream fileOut=new FileOutputStream(file);
          ObjectOutputStream objectOut=new ObjectOutputStream(fileOut);
          objectOut.writeObject(changhong); 
          objectOut.close(); 
          FileInputStream fileIn=new FileInputStream(file);
          ObjectInputStream objectIn=new ObjectInputStream(fileIn);
          TV xinfei=(TV)objectIn.readObject(); 
          objectIn.close();
          xinfei.setName("�·ɵ���");
          xinfei.setPrice(6666); 
          System.out.println("changhong������:"+changhong.getName());
          System.out.println("changhong�ļ۸�:"+changhong.getPrice());
          System.out.println("xinfei������:"+xinfei.getName());
          System.out.println("xinfei�ļ۸�:"+xinfei.getPrice());
       }
       catch(ClassNotFoundException event) {
          System.out.println("���ܶ�������");
       }
       catch(IOException event) {
          System.out.println(event);
       }
   }
}
