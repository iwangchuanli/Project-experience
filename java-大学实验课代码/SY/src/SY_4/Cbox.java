package SY_4;

import java.util.Scanner;

public class Cbox {
	double length;
	double width;
	double height;
	void volume(){
		System.out.println(length*width*height);
	}
	void surfaceArea(){
		System.out.println((length*width+length*height+width*height)*2);
	}
	public static void main(String[] args) {
		Cbox box1=new Cbox();
		Scanner input=new Scanner(System.in);
		System.out.println("长方形的长，宽，高：");
		box1.length=input.nextDouble();
		box1.width=input.nextDouble();
		box1.height=input.nextDouble();
		System.out.println("长方形的体积：");
		box1.volume();
		System.out.println("长方形的面积：");
		box1.surfaceArea();
		input.close();
	}

}
