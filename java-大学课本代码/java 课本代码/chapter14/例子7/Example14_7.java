import com.sun.image.codec.jpeg.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.*;
import java.io.*;
public class Example14_7 {
  public static void main(String args[]) {
    try {  
       JPEGImageEncoder encoder=
       JPEGCodec.createJPEGEncoder(new FileOutputStream("my.jpg"));
       Paint myJPG=new Paint(); 
       encoder.encode(myJPG.getImage());
    }
    catch(Exception ee) {}
  }
}

class Paint extends Canvas {
   BufferedImage image;
   Graphics2D g_2d; 
   BasicStroke bs;
   Paint() {
      image=new BufferedImage(300,300,BufferedImage.TYPE_INT_RGB);
      g_2d=image.createGraphics();
      Rectangle2D rect = new Rectangle2D.Double(0,0,300,300);
      g_2d.setColor(Color.cyan);
      g_2d.fill(rect); 
      QuadCurve2D quadCurve=new QuadCurve2D.Double(2,10,51,70,100,10);
      bs=new BasicStroke(3f,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_ROUND);
      g_2d.setStroke(bs); 
      g_2d.setColor(Color.black);  
      g_2d.draw(quadCurve);
      Ellipse2D ellipse= new Ellipse2D.Double(2,40,100,50);
      g_2d.draw(ellipse); 
      g_2d.drawString("我绘制的图形保存的JPG图像",100,45);
   }
   public BufferedImage getImage() {
      return image;
   }
}



