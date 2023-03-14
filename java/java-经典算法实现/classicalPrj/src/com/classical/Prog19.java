package com.classical;
/**
 * 
 * @author Administrator
 *题目：打印出如下图案（菱形）
    *
   *** 
 ****** 
******** 
 ****** 
  *** 
   * 
程序分析：先把图形分成两部分来看待，前四行一个规律，后三行一个规律，利用双重 for循环，第一层控制行，第二层控制列。

 */
public class Prog19{
	public static void main(String[] args){
		int n = 5;
		printStar(n);
	}
	//打印星星
	private static void printStar(int n){
		//打印上半部分
		for(int i=0;i<n;i++){
			for(int j=0;j<2*n;j++){
		  	if(j<n-i)
		  	  System.out.print(" ");
		  	if(j>=n-i && j<=n+i)
		  	  System.out.print("*");
		  }
		  System.out.println();
		}
		//打印下半部分
		for(int i=1;i<n;i++){
			System.out.print(" ");
			for(int j=0;j<2*n-i;j++){
				if(j<i)
		  	  System.out.print(" ");
		  	if(j>=i && j<2*n-i-1)
		  	  System.out.print("*");
			}
			System.out.println();
		}
	}
}

