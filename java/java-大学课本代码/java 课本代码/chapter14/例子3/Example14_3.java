import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
class MyCanvs extends JPanel { 
   public void paint(Graphics g) {
      Graphics2D g_2d=(Graphics2D)g;
       Ellipse2D ellipse= new Ellipse2D.Double(0,2,80,80);
       Rectangle2D rect=  new Rectangle2D.Double(40,2,80,80);
       Area a1=new Area(ellipse);  
       Area a2=new Area(rect);
       a1.intersect(a2);       //"”Î"
       g_2d.fill(a1);
       ellipse.setFrame(130,2,80,80); 
       rect.setFrame(170,2,80,80);
       a1=new Area(ellipse); 
       a2=new Area(rect);
       a1.add(a2);             //"ªÚ"
       g_2d.draw(a1);
       ellipse.setFrame(0,90,80,80);
       rect.setFrame(40,90,80,80);
       a1=new Area(ellipse);  
       a2=new Area(rect);
       a1.subtract(a2);        //"≤Ó"
       g_2d.draw(a1);
       ellipse.setFrame(130,90,80,80);
       rect.setFrame(170,90,80,80);
       a1=new Area(ellipse);  
       a2=new Area(rect);
       a1.exclusiveOr(a2);     //"“ÏªÚ"
       g_2d.fill(a1);
   }
}
public class Example14_3{
   public static void main(String args[]) {
      JFrame win = new JFrame();
      win.setSize(400,400);
      win.add(new MyCanvs());
      win.setVisible(true);    
   }     
}
