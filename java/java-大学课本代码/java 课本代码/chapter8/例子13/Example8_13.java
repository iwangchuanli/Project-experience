import java.util.*;
public class Example8_13 {
   public static void main(String args[]) {
      String  cost= "TV cost 876 dollar.Computer cost 2398 dollar.telephone cost 1278 dollar.";
      Scanner scanner = new Scanner(cost);
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
      System.out.println("总消费:"+sum+"元");
   }
}
