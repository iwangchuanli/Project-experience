package SY_4;

import java.util.Scanner;

public class tuxing_test {
	public static void main(String[] args) {
		int x;
		Scanner input=new Scanner(System.in);
		System.out.println("请选择图形计算（1、三角形2、梯形3、圆形）：");
		x=input.nextInt();
		switch (x) {
		case 1:
			sanjiaoxing san1=new sanjiaoxing();
			System.out.println("请依次输入三角形的三边：");
			san1.a=input.nextDouble();
			san1.b=input.nextDouble();
			san1.c=input.nextDouble();
			san1.周长();
			san1.面积();
			break;
		case 2:
			tixing ti1=new tixing();
			System.out.println("请依次输入梯形的上底、下底、高、左边长、右边长：");
			ti1.上底=input.nextDouble();
			ti1.下底=input.nextDouble();
			ti1.高=input.nextDouble();
			ti1.左边长=input.nextDouble();
			ti1.右边长=input.nextDouble();
			ti1.周长();
			ti1.面积();
			break;
		case 3:
			yuanxing yuan1=new yuanxing();
			System.out.println("请输入圆形的半径：");
			yuan1.r=input.nextDouble();
			yuan1.周长();
			yuan1.面积();
			break;
		default:
			break;
		}
		input.close();
	}

}
