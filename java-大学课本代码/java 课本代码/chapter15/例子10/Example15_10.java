import java.util.*;
public class Example15_10 {
   public static void main(String args[]) {
      ArrayList<Integer> list=new ArrayList<Integer>();
      for(int i=0;i<10;i++) {
         list.add(i);  //自动装箱,实际添加到list中的是new Integer(i)。
      }
      for(int k=list.size()-1;k>=0;k--) {
         int m=list.get(k);  //自动拆箱,获取Integer对象中的int型数据
         System.out.printf("%3d",m);
      }
   }
}
