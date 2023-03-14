import java.applet.*;
import java.awt.*;
public class Example16_1 extends Applet {
   Button button1,button2;
   int sum;
   public void init() {
      button1=new Button("yes");
      button2=new Button("No");  
      add(button1);
      add(button2);     
   }
   public void start() {
      sum=0;
      for(int i=1;i<=100;i++)
         sum=sum+i;
   }
    public void stop() { }
    public void destroy(){ }
    public void paint(Graphics g) {
       g.setColor(Color.blue); 
       g.drawString("程序设计方法",20,60);
       g.setColor(Color.red);
       g.drawString("sum="+sum,20,100);
    }
}
