public class Example3_1 {
   public static void main(String args[]) {
      char a1='十',a2='点',a3='进',a4='攻';
      char secret='A';
      a1=(char)(a1^secret);   
      a2=(char)(a2^secret);
      a3=(char)(a3^secret);   
      a4=(char)(a4^secret);
      System.out.println("密文:"+a1+a2+a3+a4);
      a1=(char)(a1^secret);   
      a2=(char)(a2^secret);
      a3=(char)(a3^secret);  
      a4=(char)(a4^secret);
      System.out.println("原文:"+a1+a2+a3+a4);
   }
}


