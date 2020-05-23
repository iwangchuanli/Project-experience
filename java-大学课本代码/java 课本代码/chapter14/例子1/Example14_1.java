import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
class MyCanvas extends JPanel { 
   public void paint(Graphics g) {
      Graphics2D g_2d=(Graphics2D)g;
      Arc2D arc=new Arc2D.Double(0,0,100,100,-90,-180,Arc2D.PIE);
      g_2d.setColor(Color.black);
      g_2d.fill(arc);
      g_2d.setColor(Color.white);
      arc.setArc(0,0,100,100,-90,180,Arc2D.PIE);
      g_2d.fill(arc);
      arc.setArc(25,0,50,50,-90,-180,Arc2D.PIE);
      g_2d.fill(arc);
      g_2d.setColor(Color.black);
      Ellipse2D ellipse=new Ellipse2D.Double(40,15,20,20); 
      g_2d.fill(ellipse);
      arc.setArc(25,50,50,50,90,-180,Arc2D.PIE);
      g_2d.fill(arc);
      g_2d.setColor(Color.white);
      ellipse.setFrame(40,65,20,20);
      g_2d.fill(ellipse);
      g.setColor(Color.black);
      Polygon polygon=new Polygon();
      polygon.addPoint(150,10);
      polygon.addPoint(100,90); 
      polygon.addPoint(210,90);
      polygon.addPoint(260,10);
      g_2d.draw(polygon);
   } 
}
public class Example14_1{
   public static void main(String args[]) {
      JFrame win = new JFrame();
      win.setSize(400,400);
      win.add(new MyCanvas());
      win.setVisible(true);    
   }     
}
