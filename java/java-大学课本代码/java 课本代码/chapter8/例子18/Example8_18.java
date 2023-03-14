import java.math.*;
public class Example8_18 {
   public static void main(String args[]) {
      double a=5.0;
      double st=Math.sqrt(a);
      System.out.println(a+"的平方跟:"+st);
      BigInteger result=new BigInteger("0"),
                 one=new BigInteger("123456789"),
                 two=new BigInteger("987654321");
       result=one.add(two);
       System.out.println("和:"+result);
       result=one.multiply(two);
       System.out.println("积:"+result);
   }
}
