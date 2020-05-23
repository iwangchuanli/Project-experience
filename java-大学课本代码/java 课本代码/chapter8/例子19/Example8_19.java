import java.text.*;
public class Example8_19 {
   public static void main(String args[]){
      int n= 12356789;
      System.out.println("整数"+n+"按千分组(带正号):");
      String s=String.format("%,+d",n);
      System.out.println(s);
      double number = 98765.6789;
      System.out.println(number+"格式化为整数7位，小数3位:");
      s=String.format("%011.3f",number);
      System.out.println(s);
   }
}
