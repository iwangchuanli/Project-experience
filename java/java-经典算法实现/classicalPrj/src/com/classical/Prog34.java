package com.classical;

import java.util.Scanner;


/**
 * 题目：输入3个数a,b,c，按大小顺序输出。
程序分析：利用指针方法。

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
