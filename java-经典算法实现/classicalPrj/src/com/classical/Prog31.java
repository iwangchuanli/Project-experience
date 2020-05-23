package com.classical;
/**
 * 题目：将一个数组逆序输出。
程序分析：用第一个与最后一个交换。

 * @author Administrator
 *
 */
//public class Prog31{
//	public static void main(String[] args){
//		int[] A = new int[]{1,2,3,4,5,6,7,8,9,};
//		print(A);
//		System.out.println();
//		int[] B = reverse(A);
//		print(B);
//	}
//	private static int[] reverse(int[] A){
//		for(int i=0;i<A.length/2;i++){
//			int temp = A[A.length-i-1];
//			A[A.length-i-1] = A[i];
//			A[i] = temp;
//		}
//		return A;
//	}
//	private static void print(int[] A){
//		for(int i=0;i<A.length;i++)
//		  System.out.print(A[i]+" ");
//	}
//}
//

public class Prog31 {

	public static void main(String[] args) {
		int [] num = {1,2,3,4,5,6,7,8,9};
		printf(num);
		printf(change(num));
	}
	private static int[] change(int[] arr){
		for(int i=0;i<arr.length/2;i++){
			int temp = arr[arr.length-i-1];
			arr[arr.length-i-1] = arr[i];
			arr[i] = temp;
		}
		return arr;
	}
	public static void printf(int [] arr){
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
	}
}
