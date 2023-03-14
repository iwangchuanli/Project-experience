import java.util.Date;
class Rect {
   double width,height,area;
   public double getArea() {  
      area=height*width;
      return area;
  }
}
public class Example8_22 {
   public static void main(String args[]) {
      try{  
            Class cs=Class.forName("Rect");
            Rect rect=(Rect)cs.newInstance();
            rect.width=100;
            rect.height=200;
            System.out.println("rectµÄÃæ»ý"+rect.getArea());
            cs=Class.forName("java.util.Date");
            Date date = (Date)cs.newInstance();
            System.out.println(date.toString());
       }
       catch(Exception e){
             System.out.println(e.toString());
       } 
   }  
}
