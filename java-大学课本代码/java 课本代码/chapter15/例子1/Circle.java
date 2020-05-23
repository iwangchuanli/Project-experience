public class Circle {
   double area,radius; 
   Circle(double r) {
      radius=r;
   } 
   public String toString() { //重写Object类的toString()方法
      area=radius*radius*Math.PI;
      return ""+area;
   }
}
