import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Date;
public class Clock extends Canvas implements ActionListener { 
   Date date;
   javax.swing.Timer secondTime; 
   int hour,munite,second;
   Line2D secondLine,muniteLine,hourLine;
   int a,b,c;
   double pointSX[]=new double[60],//用来表示秒针端点坐标的数组
          pointSY[]=new double[60], 
          pointMX[]=new double[60], //用来表示分针端点坐标的数组
          pointMY[]=new double[60], 
          pointHX[]=new double[60], //用来表示时针端点坐标的数组
          pointHY[]=new double[60];
   Clock() {
     secondTime=new javax.swing.Timer(1000,this);
     pointSX[0]=0;                         //12点秒针位置
     pointSY[0]=-100;
     pointMX[0]=0;                         //12点分针位置
     pointMY[0]=-90;
     pointHX[0]=0;                         //12点时针位置
     pointHY[0]=-70;
     double angle=6*Math.PI/180;          //刻度为6度
     for(int i=0;i<59;i++) {              //计算出各个数组中的坐标
       pointSX[i+1]=pointSX[i]*Math.cos(angle)-Math.sin(angle)*pointSY[i];
       pointSY[i+1]=pointSY[i]*Math.cos(angle)+pointSX[i]*Math.sin(angle);
       pointMX[i+1]=pointMX[i]*Math.cos(angle)-Math.sin(angle)*pointMY[i];
       pointMY[i+1]=pointMY[i]*Math.cos(angle)+pointMX[i]*Math.sin(angle);
       pointHX[i+1]=pointHX[i]*Math.cos(angle)-Math.sin(angle)*pointHY[i];
       pointHY[i+1]=pointHY[i]*Math.cos(angle)+pointHX[i]*Math.sin(angle);
     }
     for(int i=0;i<60;i++){             
       pointSX[i]=pointSX[i]+120;            //坐标平移
       pointSY[i]=pointSY[i]+120;
       pointMX[i]=pointMX[i]+120;            //坐标平移
       pointMY[i]=pointMY[i]+120;
       pointHX[i]=pointHX[i]+120;            //坐标平移
       pointHY[i]=pointHY[i]+120;
     }
     secondLine=new Line2D.Double(0,0,0,0);
     muniteLine=new Line2D.Double(0,0,0,0);
     hourLine=new Line2D.Double(0,0,0,0);
     secondTime.start();                     //秒针开始计时
   }
   public void paint(Graphics g) {
       for(int i=0;i<60;i++) {     //绘制表盘上的小刻度和大刻度
           int m=(int)pointSX[i];
           int n=(int)pointSY[i];
           if(i%5==0) {
               g.setColor(Color.red);
               g.fillOval(m-4,n-4,8,8);
           }
           else{
               g.setColor(Color.blue);
               g.fillOval(m-2,n-2,4,4);
           }
      }
      g.fillOval(115,115,10,10);  //钟表中心的实心圆
      Graphics2D g_2d=(Graphics2D)g;
      g_2d.setColor(Color.red);
      g_2d.draw(secondLine);
      BasicStroke bs= new BasicStroke(3f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER);
      g_2d.setStroke(bs);
      g_2d.setColor(Color.blue);
      g_2d.draw(muniteLine);
      bs=new BasicStroke(6f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);
      g_2d.setStroke(bs);
      g_2d.setColor(Color.black);
      g_2d.draw(hourLine);
   }
   public void actionPerformed(ActionEvent e) {
     if(e.getSource()==secondTime) {
        date=new Date();
        String s=date.toString();
        hour=Integer.parseInt(s.substring(11,13)); 
        munite=Integer.parseInt(s.substring(14,16));
        second=Integer.parseInt(s.substring(17,19)); //获取时间中的秒
        int h=hour%12;
        a=second;                    //秒针端点的坐标
        b=munite;                    //分针端点的坐标
        c=h*5+munite/12;             //时针端点的坐标
        secondLine.setLine(120,120,(int)pointSX[a],(int)pointSY[a]);
        muniteLine.setLine(120,120,(int)pointMX[b],(int)pointMY[b]);
        hourLine.setLine(120,120,(int)pointHX[c],(int)pointHY[c]);
        repaint();
     } 
   }
}

