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
      System.out.println("�������:"+className);
      System.out.println("���������µĳ�Ա����:");
      for(int i=0;i<field.length;i++) {
            System.out.println(field[i].toString());
      }
      System.out.println("���������µķ���:");
      for(int i=0;i<method.length;i++) {
         System.out.println(method[i].toString());
      }
      System.out.println("���������µĹ��췽��:");
      for(int i=0;i<con.length;i++) {
        System.out.println(con[i].toString());
      }
  }  
}
