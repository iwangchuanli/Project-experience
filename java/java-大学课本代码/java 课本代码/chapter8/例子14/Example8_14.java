import java.util.*;
public class Example8_14 {
   public static void main(String args[]) {
      String  cost= "�����嵥���л���76.89Ԫ����;����167.38Ԫ�����ŷ�12.68Ԫ";
      Scanner scanner = new Scanner(cost);
      scanner.useDelimiter("[^0123456789.]+"); 
      double sum=0;
      while(scanner.hasNext()){
         try{
              double price=scanner.nextDouble();
              sum=sum+price;
              System.out.println(price);
         } 
         catch(InputMismatchException exp){
              String t=scanner.next();
         }   
      }
      System.out.println("��ͨ�ŷ���:"+sum+"Ԫ");
   }
}
