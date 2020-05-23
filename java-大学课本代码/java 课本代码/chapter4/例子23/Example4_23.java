public class Example4_23 {
   public static void main(String args[]) {
      Rational r1=new Rational();
      r1.setNumerator(1);
      r1.setDenominator(5);
      Rational r2=new Rational();
      r2.setNumerator(3);
      r2.setDenominator(2);
      Rational result=r1.add(r2); 
      int a=result.getNumerator();
      int b=result.getDenominator();  
      System.out.println("1/5+3/2 = "+a+"/"+b);
      result=r1.sub(r2);
      a=result.getNumerator();
      b=result.getDenominator();    
      System.out.println("1/5-3/2 = "+a+"/"+b);
      result=r1.muti(r2); 
      a=result.getNumerator();
      b=result.getDenominator();  
      System.out.println("1/5×3/2 = "+a+"/"+b);
      result=r1.div(r2);
      a=result.getNumerator();
      b=result.getDenominator();   
      System.out.println("1/5÷3/2 = "+a+"/"+b);
      int n=10,k=1;
      System.out.println("计算2/1+3/2+5/3+8/5+13/8…的前"+n+"项和.");
      Rational sum=new Rational();
      sum.setNumerator(0);
      Rational item=new Rational();
      item.setNumerator(2);
      item.setDenominator(1);
      while(k<=n) {
         sum=sum.add(item);
         k++;
         int fenzi=item.getNumerator();
         int fenmu=item.getDenominator();
         item.setNumerator(fenzi+fenmu);
         item.setDenominator(fenzi);
      } 
      a=sum.getNumerator();
      b=sum.getDenominator();
      System.out.println("用分数表示:");
      System.out.println(a+"/"+b);
      double doubleResult=(a*1.0)/b;
      System.out.println("用小数表示:");
      System.out.println(doubleResult); 
   }
}





