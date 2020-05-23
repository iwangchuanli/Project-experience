package com.classical;

import java.util.Scanner;

/**
 * 题目：给一个不多于5位的正整数，要求：一、求它是几位数，二、逆序打印出各位数字。
 * @author Administrator
 *
 */
public class Prog24{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		String s = input.nextLine();
		input.close();
		int n = Integer.parseInt(s); 
		int i = 0;
		int[] a = new int[5];
		do{
			a[i] = n%10;
		  n /= 10;
		  ++i;
		}while(n!=0);
		
		System.out.print("这是一个"+i+"位数，从个位起，各位数字依次为：");
		for(int j=0;j<i;j++)
		  System.out.print(a[j]+" ");
	}
}

