public class Example6_1 {
   public static void main(String args[]) {
      China zhang; 
      Japan henlu;
      zhang=new China();   
      henlu=new Japan();  
      zhang.number=32+Computable.MAX; 
      henlu.number=14+Computable.MAX;
      System.out.println("zhang的学号"+zhang.number+",zhang求和结果"+zhang.f(100));
      System.out.println("henlu的学号"+henlu.number+",henlu求和结果"+henlu.f(100));
   }
}
