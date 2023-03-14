package SY_6;

import java.util.Scanner;

public class AreaAndLength{  
    public static void main(String args[])
    { 
      //定义上转型对象，访问输出面积方法，求三种图形的面积
    	int key;
    	System.out.println("请选择：");
		Scanner input=new Scanner(System.in);
		key=input.nextInt();
		switch (key) {
		case 1:
			Trangle tra=new Trangle(3,4,5);
			tra.showArea();
			double len=tra.getLength();
			System.out.println("三角形的周长："+len);
			break;
		case 2:
			Lader lad=new Lader(3,4,5);
			lad.showArea();
			
			break;
		case 3:
			Circle cir=new Circle(5);
			cir.showArea();
			System.out.println("圆形的周长："+cir.getLength());
			System.out.println("圆形的半径："+cir.getRadius());
			break;

		default:
			break;
		}
		input.close();
    }
}
