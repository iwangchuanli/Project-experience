public class Circle {
   double area,radius; 
   Circle(double r) {
      radius=r;
   } 
   public String toString() { //��дObject���toString()����
      area=radius*radius*Math.PI;
      return ""+area;
   }
}
