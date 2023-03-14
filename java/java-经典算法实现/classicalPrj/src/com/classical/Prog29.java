package com.classical;

import java.util.Scanner;

/**
 * 题目：求一个3*3矩阵对角线元素之和 程序分析：利用双重for循环控制输入二维数组，再将a[i][i]累加后输出。
 * 
 * @author Administrator
 *
 */
public class Prog29 {

	public static void main(String[] args) {
		int a[][] = new int[3][3];
		input(a);
		cal(a);
	}

	public static void input(int a[][]) {
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				System.out.print("第" + (i + 1) + "行第" + (j + 1) + "列的元素为：");
				a[i][j] = input.nextInt();
			}
		}
	}

	public static void cal(int a[][]) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i][i];
		}
		System.out.println("sum=" + sum);
	}
}
