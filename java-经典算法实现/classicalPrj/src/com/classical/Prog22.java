package com.classical;
/**
 * 
 * @author Administrator
 *题目：利用递归方法求5!。
程序分析：递归公式：fn=fn_1*4!

 */
/**
 * public class Prog22{
	public static void main(String[] args){
		System.out.println(fact(10));
	}
	//递归求阶乘
	private static long fact(int n){
		if(n==1)
		  return 1;
		else
		  return fact(n-1)*n;
	}
}

 * @author Administrator
 *
 */
public class Prog22 {

	public static void main(String[] args) {
		System.out.println(fn(5));
	}
	public static int fn(int n){
		if (n == 1) {
			return 1;
		}else
			return n*fn(n-1);
	}
}
