public class Example4_8 {
   public static void main(String args[]) {
       Circle circle = new Circle(10);            //【代码1】
       System.out.println("main方法中circle的引用:"+circle); 
       System.out.println("main方法中circle的半径"+circle.getRadius()); 
       Circular circular = new Circular(circle,20);         //【代码2】
       System.out.println("circular圆锥的bottom的引用:"+circular.bottom); 
       System.out.println("圆锥的bottom的半径:"+circular.getBottomRadius()); 
       System.out.println("圆锥的体积:"+circular.getVolme());
       double r = 8888;
       System.out.println("圆锥更改底圆bottom的半径:"+r);
       circular.setBottomRadius(r);           //【代码3】   
       System.out.println("圆锥的bottom的半径:"+circular.getBottomRadius()); 
       System.out.println("圆锥的体积:"+circular.getVolme()); 
       System.out.println("main方法中circle的半径:"+circle.getRadius()); 
       System.out.println("main方法中circle的引用将发生变化");
       circle = new Circle(1000); //重新创建circle 【代码4】
       System.out.println("现在main方法中circle的引用:"+circle); 
       System.out.println("main方法中circle的半径:"+circle.getRadius()); 
       System.out.println("但是不影响circular圆锥的bottom的引用");
       System.out.println("circular圆锥的bottom的引用:"+circular.bottom); 
       System.out.println("圆锥的bottom的半径:"+circular.getBottomRadius()); 
    }
}
