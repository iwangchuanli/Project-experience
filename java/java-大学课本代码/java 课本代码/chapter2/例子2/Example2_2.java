public class Example2_2 { 
   public static void main (String args[]) {
      byte b = 22; 
      int  n = 129;  
      float f =123456.6789f ;
      double d=123456789.123456789;
      System.out.println("b=  "+b);   
      System.out.println("n=  "+n);
      System.out.println("f=  "+f);   
      System.out.println("d=  "+d); 
      b=(byte)n;    //���¾��ȵ���ʧ.
      f=(float)d;   //���¾��ȵ���ʧ
      System.out.println("b=  "+b);   
      System.out.println("f=  "+f); 
   }
}


