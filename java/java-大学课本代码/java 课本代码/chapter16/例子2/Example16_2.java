import java.awt.*;
import java.applet.*;
public class Example16_2 extends Applet {
   int x,y,sum;
   public void init() {
      String s1=getParameter("girl"); //从html得到"girl"的值。
      String s2=getParameter("boy");  //从html得到"boy"的值。
      x=Integer.parseInt(s1);
      y=Integer.parseInt(s2); 
      sum=x+y;
   } 
   public void paint(Graphics g) {
      g.drawString("sum="+sum,90,120);
   }
}
