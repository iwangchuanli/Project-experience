import java.text.*;
public class Example8_19 {
   public static void main(String args[]){
      int n= 12356789;
      System.out.println("����"+n+"��ǧ����(������):");
      String s=String.format("%,+d",n);
      System.out.println(s);
      double number = 98765.6789;
      System.out.println(number+"��ʽ��Ϊ����7λ��С��3λ:");
      s=String.format("%011.3f",number);
      System.out.println(s);
   }
}
