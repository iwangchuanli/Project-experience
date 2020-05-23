package SY_6;

class Shape
{
void   showArea()
{
  System.out.println("根据形状求面积");
}
}


class Trangle  extends Shape//三角形
{  
   double sideA,sideB,sideC,area,length;
   boolean boo;
   public  Trangle(double a,double b,double c) 
   { 
    a=sideA;b=sideB;c=sideC;//参数a,b,c分别赋值给sideA,sideB,sideC。
     if(a+b>c&&a+c>b&&b+c>a) //a,b,c构成三角形的条件表达式。
     { 
       boo=true; //给boo赋值。
     }    
    else
     { 
        boo=false; //给boo赋值。
     }
   }
   double getLength() 
   {   
       return sideA+sideB+sideC; //方法体，要求计算出length的值并返回。 
  }
   public void   showArea()
 
   {  
      if(boo)
        { 
          double p=(sideA+sideB+sideC)/2.0;
          area=Math.sqrt(p*(p-sideA)*(p-sideB)*(p-sideC)) ;
             System.out.println("三角形的面积是："+area);

        }
      else
        { 
          System.out.println("不是一个三角形,不能计算面积");
          }
   } 
   public  void  setABC(double a,double b,double c)
   { 
	   a=sideA;b=sideB;c=sideC; //参数a,b,c分别赋值给sideA,sideB,sideC。
     if(a+b>c&&a+c>b&&b+c>a) //a,b,c构成三角形的条件表达式。
     { 
       boo=true; //给boo赋值。
     }    
    else
     { 
        boo=false; //给boo赋值。
     }
   }
}


class Lader extends Shape//梯形
{   
    double above,bottom,height,area; 
    Lader(double a,double b,double h)
    {
      above=a;bottom=b;height=h; //方法体。
    }
       public void   showArea()
    {
      area=(above+bottom)*height/2; 
      System.out.println("梯形的面积："+area);//方法体,要求输出area
    }
}

class Circle extends Shape//圆形
{  
    double radius,area;
    Circle(double r)
    { 
        this.radius=r; //方法体。
    }
      public void   showArea()
    {  
       area=(3.14d)*radius*radius;
       System.out.println("圆形的面积："+area);//方法体,要求输出area
    }
    double getLength() 
    {  
      return (3.14d)*radius*2; //方法体,要求计算出length返回。
    }
    void setRadius(double newRadius)
    {  
       radius=newRadius;
    }
    double getRadius() 
    { 
        return radius;
    }
}
