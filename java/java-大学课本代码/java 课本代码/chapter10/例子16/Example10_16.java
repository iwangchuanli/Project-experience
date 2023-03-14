import java.io.*;
import java.util.*;
public class Example10_16 {
   public static void main(String args[]) {
      File file = new File("student.txt");
      Scanner sc=null;
      int count=0;
      double sum=0;
      try { double score=0;
            sc = new Scanner(file);
            sc.useDelimiter("[^0123456789.]+"); 
            while(sc.hasNextDouble()){
                score=sc.nextDouble();
                count++;
                sum=sum+score;
                System.out.println(score); 
            }
            double aver=sum/count;
            System.out.println("Æ½¾ù³É¼¨:"+aver);
      }
      catch(Exception exp){
         System.out.println(exp); 
      }
   }
}
