package com.classical;

import java.util.Scanner;


/**
 * ��Ŀ������3����a,b,c������С˳�������
�������������ָ�뷽����

 * @author Administrator
 *
 */
public class Prog34 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int a = input.nextInt();
		int b = input.nextInt();
		int c = input.nextInt();
		int temp;
		if (a > b) {
			temp = a;
			a = b;
			b = temp;
		}
		if (a > c) {
			temp = a;
			a = c;
			c = temp;
		}
		if (b > c) {
			temp = b;
			b = c;
			c = temp;
		}
		System.out.println(a+" "+b+" "+c);
		
	}
}
