package com.classical;

/**
 * 题目：有一个已经排好序的数组。现输入一个数，要求按原来的规律将它插入数组中。
 * 程序分析：首先判断此数是否大于最后一个数，然后再考虑插入中间的数的情况，插入后此元素之后的数，依次后移一个位置。
 * 
 * @author Administrator
 *
 */

import java.util.Scanner;

public class Prog30 {
	public static void main(String[] args) {
		int[] A = new int[] { 0, 8, 7, 5, 9, 1, 2, 4, 3, 12 };
		int[] B = sort(A);
		print(B);
		System.out.println();
		System.out.print("请输入10个数的数组：");
		Scanner scan = new Scanner(System.in);
		int a = scan.nextInt();
		scan.close();
		int[] C = insert(a, B);
		print(C);
	}

	// 选择排序
	private static int[] sort(int[] A) {
		int[] B = new int[A.length];
		for (int i = 0; i < A.length - 1; i++) {
			int min = A[i];
			for (int j = i + 1; j < A.length; j++) {
				if (min > A[j]) {
					int temp = min;
					min = A[j];
					A[j] = temp;
				}
				B[i] = min;
			}
		}
		B[A.length - 1] = A[A.length - 1];
		return B;
	}

	// 打印
	private static void print(int[] A) {
		for (int i = 0; i < A.length; i++)
			System.out.print(A[i] + " ");
	}

	// 插入数字
	private static int[] insert(int a, int[] A) {
		int[] B = new int[A.length + 1];
		for (int i = A.length - 1; i > 0; i--)
			if (a > A[i]) {
				B[i + 1] = a;
				for (int j = 0; j <= i; j++)
					B[j] = A[j];
				for (int k = i + 2; k < B.length; k++)
					B[k] = A[k - 1];
				break;
			}
		return B;
	}
}

//public class Prog30 {
//
//	public static void main(String[] args) {
//		int[] num = { 1, 2, 3, 4, 6, 7, 8, 9 };
//		printf(num);
//		int[] newnum = insert(num, 5);
//		printf(newnum);
//
//	}

//	public static int[] insert(int[] arr, int x) {
//		int index = 0;
//		int len = arr.length - 1;
//		int temp;
//		int[] newarr = new int[len + 1];
//		if (x > arr[len]) {
//			arr[arr.length + 1] = x;
//		} else {
//			for (int i = arr.length - 1; i >= 0; i++) {
//				if (arr[i] < x) {
//					newarr[i] = x;
//					index = i;
//					for (int j = 0; j < newarr.length; j++) {
//						if (j < index) {
//							newarr[j] = arr[j];
//						}
//						if (j > index) {
//							newarr[j+2] = arr[j];
//						}
//					}
//				}
//			}
//		}
////		
////		for (int i = 0; i < newarr.length; i++) {
////			if (i == index) {
////				newarr[i] = x;
////				System.out.println(x + "*");
////			} else if (i > index) {
////				newarr[i] = arr[i - 1];
////			}
////			newarr[i] = arr[i];
////			System.out.println(newarr[i] + " ");
////		}
//
//		return newarr;
//	}
//
//	public static void printf(int[] arr) {
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i] + " ");
//		}
//	}
//}
