class Point {
    int x,y;
    Point(int a,int b) {
    x=a;
       y=b;
    }
}
public class Example4_2 {
   public static void main(String args[]) {
      Point p1,p2;                 //声明对象p1和p2
      p1=new Point(10,10);         //为对象分配变量(使用new和类中的构造方法)
      p2=new Point(23,35);        //为对象分配变量(使用new和类中的构造方法)
   }
}

