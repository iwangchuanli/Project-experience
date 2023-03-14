package com.classical;
/**
 * 
 * @author Administrator
 *	题目：求1+2!+3!+...+20!的和 
程序分析：此程序只是把累加变成了累乘。
 */

//public class Prog21{
//	public static void main(String[] args){
//		long sum = 0;
//		for(int i=0;i<20;i++)
//		  sum += factorial(i+1);
//		System.out.println(sum);
//	}
//	//阶乘
//	private static long factorial(int n){
//		int mult = 1;
//		for(int i=1;i<n+1;i++)
//		  mult *= i;
//		return mult;
//	}
//}


public class Prog21 {

	public static void main(String[] args) {
		int n = 20;
		long sum = 0;
		int temp = 1;
		for (int i = 0; i <= n; i++) {
			
			for (int j = 1; j < i+1; j++) {
				temp = temp * j;
			}
			sum += temp;
			temp = 1;
		}
		System.out.println(sum);
	}
}
