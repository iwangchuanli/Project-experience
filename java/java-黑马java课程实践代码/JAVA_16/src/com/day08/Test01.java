package com.day08;
/**
 * 使用递归计算1-5的阶乘
 * @author Administrator
 *
 */
public class Test01 {

	public static void main(String[] args) {
		System.out.println(" 1!="+mul(1)+" 2!="+mul(2)+" 3!="+mul(3)+" 4!="+mul(4)+" 5!="+mul(5));
	}
	public static int mul(int n){
		if (n == 1) {
			return 1;
		}else {
			return n*mul(n-1);
		}
		
	}
}
