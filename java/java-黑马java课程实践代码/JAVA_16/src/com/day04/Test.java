package com.day04;

import java.util.Scanner;

/**
 * 
 * @author Administrator
 *��һ�⣺��ϰ���յĴ���

�ڶ��⣺������������ʵ��
	1.���巽����������������ĺ�
	2.���÷�����������

�����⣺������������ʵ��
	1.���巽�����������С���ĺ�
	2.���÷�����������

�����⣺������������ʵ��
	1.���巽������������ε��ܳ�
	2.���÷�����������

�����⣺������������ʵ��
	1.���巽������������ε����
	2.���÷�����������

 */
public class Test {

	//������������ĺ�
	public static int sum(int num1,int num2){
		int sum = num1 + num2;
		return sum;
	}
	//�������С���ĺ�
	public static int lower(int num1,int num2){
		if (num1 < num2) {
			return num1;
		}else {
			return num2;
		}
	}
	//��������ε��ܳ�
	public static double zc(double length,double height){
		return (length+height)*2;
	}
	
	//��������ε����
	public static double area(double length,double height){
		return length*height;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("����������������");
		int n1 = input.nextInt();
		int n2 = input.nextInt();
		System.out.println("�������ĺ�Ϊ��"+sum(n1, n2)+"�����������С���ǣ�"+lower(n1, n2));
		System.out.println("�����볤���εĳ��͸ߣ�");
		double length = input.nextDouble();
		double height = input.nextDouble();
		System.out.println("�����ε����Ϊ��"+area(length, height)+"�����ε��ܳ�Ϊ��"+zc(length, height));
	}
}
