public class Example6_1 {
   public static void main(String args[]) {
      China zhang; 
      Japan henlu;
      zhang=new China();   
      henlu=new Japan();  
      zhang.number=32+Computable.MAX; 
      henlu.number=14+Computable.MAX;
      System.out.println("zhang��ѧ��"+zhang.number+",zhang��ͽ��"+zhang.f(100));
      System.out.println("henlu��ѧ��"+henlu.number+",henlu��ͽ��"+henlu.f(100));
   }
}
