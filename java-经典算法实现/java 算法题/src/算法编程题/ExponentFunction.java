package 算法编程题;

//program ExponentFunction.java 
import javax.swing.*; 
import java.awt.*; 
import java.awt.geom.*; 
class MyExponentFunction extends JFrame//继承Frame的特性 
{ 
public MyExponentFunction() 
{ 
setTitle("My Exponent Function");//设置标题 
setSize(500,780);//设置窗口大小 
ExponentFunctionPanel efp=new ExponentFunctionPanel(); 
Container pane=getContentPane(); 
pane.add(efp); 
setContentPane(pane); 
} 
} 
class ExponentFunctionPanel extends JPanel//继承JPanel的特性 
{ 
public void paintComponent(Graphics g)//调用paintComponent方法 
{ 
super.paintComponent(g);//执行构图 
Graphics2D g2=(Graphics2D)g; 

//建立坐标系，绘制直线 
Point2D p1=new Point2D.Double(0,700); 
Point2D p2=new Point2D.Double(500,700); 
Point2D p3=new Point2D.Double(250,0); 
Point2D p4=new Point2D.Double(250,780); 
Line2D l1=new Line2D.Double(p1,p2); 
g2.draw(l1); 
Line2D l2=new Line2D.Double(p3,p4); 
g2.draw(l2); 
//手绘坐标点 
g.drawString("2700",220,70); 
g.drawString("2400",220,140); 
g.drawString("2100",220,210); 
g.drawString("1800",220,280); 
g.drawString("1500",220,350); 
g.drawString("1200",220,420); 
g.drawString("900",220,490); 
g.drawString("600",220,560); 
g.drawString("300",220,630); 
g.drawString("0",240,705); 
g.drawString("1",280,720); 
g.drawString("2",310,720); 
g.drawString("3",340,720); 
g.drawString("4",370,720); 
g.drawString("5",400,720); 
g.drawString("6",430,720); 
g.drawString("7",460,720); 
g.drawString("8",490,720); 
g.drawString(">",500,700); 
g.drawString("-2",190,720); 
g.drawString("-4",130,720); 
g.drawString("-6",70,720); 
g.drawString("-8",10,720); 
//java.lang.Math.log(double) 
int n=6,m=5; 
double y,k1,k2,z1,z2,y2; 
for(y=690;y>=0;y--){//使用for循环 
z1=692-y; 
    y2=y+1; 
    z2=692-y2; 
double i1=Math.log(z1);//调用数学函数 
double i2=Math.log(z2); 
k1=i1*30+250; 
k2=i2*30+250; 
Point2D pm=new Point2D.Double(k2,y2); 
Point2D pn=new Point2D.Double(k1,y); 
Line2D ln=new Line2D.Double(pm,pn); 
g2.draw(ln); 
n=n++; 
} 
int u=1000,v=1001; 
double x,x1,x2,x3,x4,y3,y4,k3,k4,z3,z4; 
for(x=1;x<=250;x++)//使用for循环 
  { 
x1=x-1; 
x3=250-x; 
x4=250-x1; 
k1=-x/30; 
k2=-x1/30; 
double i3=Math.exp(k1);//调用数学函数 
double i4=Math.exp(k2); 
y3=690-i3; 
y4=690-i4; 
Point2D pv=new Point2D.Double(x3,y3); 
Point2D pu=new Point2D.Double(x4,y4); 
Line2D lv=new Line2D.Double(pu,pv); 
g2.draw(lv); 
u=v++; 
} 
} 
} 
public class ExponentFunction 
{ 
public static void main(String args[]) 
{ 
  MyExponentFunction mef=new MyExponentFunction(); 
  mef.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  mef.setVisible(true); 
} 
} 