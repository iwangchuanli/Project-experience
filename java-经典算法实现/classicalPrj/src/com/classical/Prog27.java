package com.classical;
/**
 * 题目：求100之内的素数
 * @author Administrator
 *
 */

//public class Prog27{
//	public static void main(String[] args){
//		int n = 100;
//		System.out.print(n+"以内的素数：");
//		for(int i=2;i<n+1;i++){
//			if(isPrime(i))
//			  System.out.print(i+" ");
//		}
//	}
//	//求素数
//	private static boolean isPrime(int n){
//		boolean flag = true;
//		for(int i=2;i<Math.sqrt(n)+1;i++)
//			if(n%i==0){
//			  flag = false;
//			  break;
//			}
//		return flag;
//	}
//}

public class Prog27 {

	public static void main(String[] args) {
		int n = 100;
		for (int i = 2; i < n; i++) {
			if (isSs(i)) {
				System.out.print(i+" ");
			}
		}
	}
	
	public static boolean isSs(int n){
		for (int i = 1; i < n; i++) {
			if (n % i == 0 && i != 1 ) {
				return false;
			}
		}
		return true;
	}
	
}
