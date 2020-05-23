import java.util.*;
public class Example8_2 {
    public static void main(String args[]) {
       String [] a={"melon","apple","pear","banana"};
       String [] b={"西瓜","苹果","梨","香蕉"};
       System.out.println("使用SortString类的方法按字典序排列数组a:");
       SortString.sort(a);
       for(int i=0;i<a.length;i++) {
          System.out.print("  "+a[i]);
       }
       System.out.println("");
       System.out.println("使用类库中的Arrays类，按字典序排列数组b:"); 
       Arrays.sort(b);
       for(int i=0;i<b.length;i++) {
          System.out.print("  "+b[i]);
       }
    }
}
