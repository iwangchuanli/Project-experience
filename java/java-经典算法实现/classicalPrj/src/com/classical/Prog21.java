package com.classical;
/**
 * 
 * @author Administrator
 *	��Ŀ����1+2!+3!+...+20!�ĺ� 
����������˳���ֻ�ǰ��ۼӱ�����۳ˡ�
 */

//public class Prog21{
//	public static void main(String[] args){
//		long sum = 0;
//		for(int i=0;i<20;i++)
//		  sum += factorial(i+1);
//		System.out.println(sum);
//	}
//	//�׳�
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
