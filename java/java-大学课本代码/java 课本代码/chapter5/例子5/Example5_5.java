class A {
    float computer(float x,float y) {
       return x+y;
    }
    public int g(int x,int y) {
       return x+y;
    }
}
class B extends A {
    float computer (float x,float y,double z) {
       return x-y;
    }
}
public class Example5_5 {
    public static void main(String args[]) {
      B b=new B();
      double result=b.computer(8,9);    //b������д�ķ���
      System.out.println(result);  
      int m=b.g(12,8);                 //b���ü̳еķ���
      System.out.println(m);      
    } 
}
