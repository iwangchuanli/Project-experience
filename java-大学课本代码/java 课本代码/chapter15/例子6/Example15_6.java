import java.util.*;
public class Example15_6 {
   public static void main(String args[]) {
      Stack<Integer> stack=new Stack<Integer>();
      stack.push(new Integer(1)); 
      stack.push(new Integer(1));
      int k=1;
      while(k<=10) {
        for(int i=1;i<=2;i++) {
          Integer F1=stack.pop();
          int f1=F1.intValue();
          Integer F2=stack.pop();
          int f2=F2.intValue();
          Integer temp=new Integer(f1+f2);
          System.out.println(""+temp.toString()); 
          stack.push(temp);
          stack.push(F2);
          k++;
        }
      } 
   }
}


