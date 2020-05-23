package com.classical;
/**
 * 题目：输入数组，最大的与第一个元素交换，最小的与最后一个元素交换，输出数组。
 * @author Administrator
 *
 */

import java.util.Scanner;

public class Prog35 {
	public static void main(String[] args) {
		System.out.print("请输入一组数：");
		Scanner scan = new Scanner(System.in).useDelimiter("\\s");
		int[] a = new int[50];
		int m = 0;
		while (scan.hasNextInt()) {
			a[m++] = scan.nextInt();
		}
		scan.close();
		int[] b = new int[m];
		for (int i = 0; i < m; i++)
			b[i] = a[i];
		for (int i = 0; i < b.length; i++)
			for (int j = 0; j < b.length - i - 1; j++)
				if (b[j] < b[j + 1]) {
					int temp = b[j];
					b[j] = b[j + 1];
					b[j + 1] = temp;
				}
		for (int i = 0; i < b.length; i++)
			System.out.print(b[i] + " ");

	}
}

// public class Prog35 {
//
// public static void main(String[] args) {
// int []num = {5,6,1,8,4,3,9,2,7};
// printf(num);
// change(num, getMax(num), 0);
// printf(num);
// change(num, getMin(num), num.length-1);
// System.out.println(getMin(num));
// printf(num);
// }
// public static int getMin(int [] arr){
// int min = 0;
// int index = 0;
// for (int i = 0; i < arr.length; i++) {
// if (arr[i] < min) {
// min = arr[i];
// index = i;
// }
// }
// return index;
// }
// public static int getMax(int [] arr){
// int max = 0;
// int index = 0;
// for (int i = 0; i < arr.length; i++) {
// if (arr[i] > max) {
// max = arr[i];
// index = i;
// }
// }
// return index;
// }
// public static void change(int[] arr,int ind1,int ind2){
// int temp = arr[ind1];
// arr[ind1] = arr[ind2];
// arr[ind2] = temp;
// }
// public static void printf(int [] arr){
// for (int i = 0; i < arr.length; i++) {
// System.out.print(arr[i]+" ");
// }
// System.out.println("");
// }
// }
