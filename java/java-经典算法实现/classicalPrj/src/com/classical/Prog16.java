package com.classical;
/***
 * 
 * @author Administrator
 *题目：输出9*9口诀。
程序分析：分行与列考虑，共9行9列，i控制行，j控制列。
 */

public class Prog16{
	public static void main(String[] args){
		for(int i=1;i<10;i++){//有多少行，共9行
			for(int j=1;j<i+1;j++)//每行有多少个数相乘
				System.out.print(j+"*"+i+"="+(j*i)+" ");
			System.out.println();
		}
	}
}

