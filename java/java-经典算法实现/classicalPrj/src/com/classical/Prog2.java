package com.classical;
/***
 * 
 * @author Administrator
 *题目：判断101-200之间有多少个素数，并输出所有素数。       只有1和他本身能整除
程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除，则表明此数不是素数，反之是素数。
 */
public class Prog2{
	public static void main(String[] args){
		int m = 1;
		int n = 1000;
		int count = 0;
		//统计素数个数
		for(int i=m;i<n;i++){//1~n之间一个一个遍历
			if(isPrime(i)){//是素数
				count++;//计数+1并输出
				System.out.print(i+" ");
				if(count%10==0){//十个素数输出换行
					System.out.println();
				}
			}
		}
		
		System.out.println();
		System.out.println("在"+m+"和"+n+"之间共有"+count+"个素数");
	}
	//判断素数
	private static boolean isPrime(int n){//boolean型返回值
		boolean flag = true;
		if(n==1)
		  flag = false;//1不是素数，但它符合条件
		else{
			for(int i=2;i<=Math.sqrt(n);i++){
			if((n%i)==0 || n==1){
				flag = false;
				break;
			}
			 else
			   flag = true;
		  }
		}
		return flag;
	}
}
