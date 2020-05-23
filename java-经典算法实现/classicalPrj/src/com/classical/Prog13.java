package com.classical;
/***
 * 
 * @author Administrator
 *题目：一个整数，它加上100后是一个完全平方数，再加上168又是一个完全平方数，请问该数是多少？
程序分析：在10万以内判断，先将该数加上100后再开方，再将该数加上268后再开方，如果开方后的结果满足如下条件，即是结果。
 */
public class Prog13{
	public static void main(String[] args){
		int n=0;
		for(int i=0;i<100001;i++){
			if(isCompSqrt(i+100) && isCompSqrt(i+268)){
				n = i;
				System.out.println("所求的数是："+n);
				System.out.println("分别开方是:"+Math.sqrt(n+100)+"   "+Math.sqrt(n+268));
				//break;
			}
		}
		//System.out.println("所求的数是："+n);
	}
	//判断完全平方数
	private static boolean isCompSqrt(int n){
		boolean isComp = false;
		for(int i=1;i<Math.sqrt(n)+1;i++){
			if(n==Math.pow(i,2)){//Math.pow(double a, double b)返回第一个参数的第二个参数次幂的值。
				isComp = true;
				break;
			}
		}
		return isComp;
	}
}
