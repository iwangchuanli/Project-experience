import java.util.*;
public class Example8_2 {
    public static void main(String args[]) {
       String [] a={"melon","apple","pear","banana"};
       String [] b={"����","ƻ��","��","�㽶"};
       System.out.println("ʹ��SortString��ķ������ֵ�����������a:");
       SortString.sort(a);
       for(int i=0;i<a.length;i++) {
          System.out.print("  "+a[i]);
       }
       System.out.println("");
       System.out.println("ʹ������е�Arrays�࣬���ֵ�����������b:"); 
       Arrays.sort(b);
       for(int i=0;i<b.length;i++) {
          System.out.print("  "+b[i]);
       }
    }
}
