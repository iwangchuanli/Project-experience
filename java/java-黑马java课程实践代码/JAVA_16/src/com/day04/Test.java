package com.day04;

import java.util.Scanner;

/**
 * 
 * @author Administrator
 *第一题：练习今日的代码

第二题：分析以下需求并实现
	1.定义方法，求出两个整数的和
	2.调用方法，输出结果

第三题：分析以下需求并实现
	1.定义方法，求出两个小数的和
	2.调用方法，输出结果

第四题：分析以下需求并实现
	1.定义方法，求出长方形的周长
	2.调用方法，输出结果

第五题：分析以下需求并实现
	1.定义方法，求出长方形的面积
	2.调用方法，输出结果

 */
public class Test {

	//求出两个整数的和
	public static int sum(int num1,int num2){
		int sum = num1 + num2;
		return sum;
	}
	//求出两个小数的和
	public static int lower(int num1,int num2){
		if (num1 < num2) {
			return num1;
		}else {
			return num2;
		}
	}
	//求出长方形的周长
	public static double zc(double length,double height){
		return (length+height)*2;
	}
	
	//求出长方形的面积
	public static double area(double length,double height){
		return length*height;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入两个整数：");
		int n1 = input.nextInt();
		int n2 = input.nextInt();
		System.out.println("两个数的和为："+sum(n1, n2)+"两个数里面较小的是："+lower(n1, n2));
		System.out.println("请输入长方形的长和高：");
		double length = input.nextDouble();
		double height = input.nextDouble();
		System.out.println("长方形的面积为："+area(length, height)+"长方形的周长为："+zc(length, height));
	}
}
