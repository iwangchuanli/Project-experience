package com.classical;
/**
 * 题目：对10个数进行排序
程序分析：可以利用选择法，即从后9个比较过程中，选择一个最小的与第一个元素交换，
 下次类推，即用第二个元素与后8个进行比较，并进行交换。

 * @author Administrator
 *
 */
public class Prog28{
	public static void main(String[] args){
		int[] a = new int[]{31,42,21,50,12,60,81,74,101,93};
		for(int i=0;i<10;i++)
			for(int j=0;j<a.length-i-1;j++)
				if(a[j]>a[j+1]){
					int temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
		for(int i=0;i<a.length;i++)
		  System.out.print(a[i]+" ");
	}
}
