package com.classical;
/***
 * @author Administrator
 *题目：一个数如果恰好等于它的因子之和，这个数就称为"完数"。
 *例如6=1＋2＋3.编程找出1000以内的所有完数。
 */
public class Prog9{
	public static void main(String[] args){
		int n = 10000;
		compNumber(n);//调用方法
	}
	//求完数
	private static void compNumber(int n){
		int count = 0;//计数
		System.out.println(n+"以内的完数：");
		
		for(int i=1;i<n+1;i++){//i遍历数
			int sum = 0;
			for(int j=1;j<i/2+1;j++){//j遍历求i的因子
				if((i%j)==0){//能整除，j为其中一个因子
					sum += j;//求因子之和
					if(sum==i){//如果相等时完数那么：
						System.out.print(i+" ");
						if((count++)%5==0)//控制输出格式
							System.out.println();
					}
				}
			}
		}
	}
}
