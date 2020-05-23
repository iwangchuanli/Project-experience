import java.util.*;
public class Example8_12 {
   public static void main(String args[]) {
      String s="you are welcome(thank you),nice to meet you";
      StringTokenizer fenxi=new StringTokenizer(s,"() ,"); 
      int number=fenxi.countTokens();
      while(fenxi.hasMoreTokens()) {
          String str=fenxi.nextToken();
          System.out.print(str+" ");
      }
      System.out.println("共有单词："+number+"个");
    } 
}



