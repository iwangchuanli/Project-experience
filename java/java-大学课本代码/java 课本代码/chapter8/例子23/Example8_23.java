import java.lang.reflect.*;
import java.util.*;
public class Example8_23 {
   public static void main(String args[]) {
      Date date=new Date();
      Class cs=date.getClass();
      String className=cs.getName();
      Constructor[] con=cs.getDeclaredConstructors();
      Field[] field=cs.getDeclaredFields() ;
      Method[] method=cs.getDeclaredMethods();
      System.out.println("类的名字:"+className);
      System.out.println("类中有如下的成员变量:");
      for(int i=0;i<field.length;i++) {
            System.out.println(field[i].toString());
      }
      System.out.println("类中有如下的方法:");
      for(int i=0;i<method.length;i++) {
         System.out.println(method[i].toString());
      }
      System.out.println("类中有如下的构造方法:");
      for(int i=0;i<con.length;i++) {
        System.out.println(con[i].toString());
      }
  }  
}
