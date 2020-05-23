abstract class A {
   abstract int add(int x,int y);
   int sub(int x,int y) { 
      return x-y;
   }
}
class B extends A {
   int add(int x,int y) {   //子类必须重写父类的add方法
      return x+y;
   }
}
public class Example5_12 {
   public static void main(String args[]) {
      B b=new B();
      int sum=b.add(30,20);            //调用子类重写的add方法
      int sub=b.sub(30,20);            //调用子类继承的sub方法
      System.out.println("sum="+sum);  //输出结果为sum=50
      System.out.println("sum="+sub);  //输出结果为sum=10
      A a;                             //抽象类声明对象
      a = b;                           //a是b的上转型对象
      sum=a.add(30,20);                //调用子类重写的方法
      sub=a.sub(30,20);                //调用子类继承的方法
      System.out.println("sum="+sum);  //输出结果为sum=50
      System.out.println("sum="+sub);  //输出结果为sum=10
   }
}


