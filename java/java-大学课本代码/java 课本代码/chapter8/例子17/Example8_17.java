import java.util.*;
public class Example8_17 {
   public static void main(String args[]) {
      Date nowTime = new Date();
      System.out.println(nowTime);
      String pattern = "%tY-%<tm-%<td(%<tA) %<tT";
      String s=String.format(pattern,nowTime);
      System.out.println(s);
      Calendar calendar=Calendar.getInstance();
      calendar.set(2022,11,31,05,15,59);
      s=String.format("%tY��%<tb%<td��(%<tT)������ʱ��%<tZ��GMT���:%<tzСʱ",calendar);
      System.out.println(s);
    }
}


