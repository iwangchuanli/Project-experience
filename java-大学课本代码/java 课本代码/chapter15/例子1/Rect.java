public class Rect {
   double sideA,sideB,area; 
   Rect(double a,double b) {
     sideA=a;
     sideB=b;
   } 
   public String toString() {
      area=sideA*sideB;
      return ""+area;
   }
}
