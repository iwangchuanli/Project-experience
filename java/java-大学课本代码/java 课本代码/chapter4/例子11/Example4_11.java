import java.util.*;
public class Example4_11 {
   public static void main(String args[]) {
      Scanner scanner = new Scanner(System.in);
      int [] a={12,34,9,23,45,6,45,90,123,19,34};
      Arrays.sort(a);
      System.out.println(Arrays.toString(a));
      System.out.println("���������������жϸ������Ƿ���������:");
      int number = scanner.nextInt();
      int index=Arrays.binarySearch(a,number);    
      if(index>=0)
         System.out.println(number+"������������Ϊ"+index+"��Ԫ��ֵ��ͬ");   
      else
         System.out.println(number+"�����������κ�Ԫ��ֵ��ͬ"); 
    }
}
