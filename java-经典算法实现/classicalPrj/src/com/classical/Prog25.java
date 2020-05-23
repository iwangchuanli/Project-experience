package com.classical;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 题目：一个5位数，判断它是不是回文数。即12321是回文数，个位与万位相同，十位与千位相同。
 * @author Administrator
 *
 */
import java.io.*;
public class Prog25{
	public static void main(String[] args){
		int n = 0;
		System.out.print("请输入一个n位正整数：");
		BufferedReader bufin = new BufferedReader(new InputStreamReader(System.in));
		try{
		  n = Integer.parseInt(bufin.readLine());
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
			  bufin.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		palin(n);
	}
//	private static void palin(int n){
//		int m = n;
//		int[] a = new int[5];
//		if(n<10000 || n>99999){
//			System.out.println("输入的不是5位数！");
//			return;
//		}else{
//		  for(int i=0;i<5;i++){
//			  a[i] = n%10;
//			  n /= 10;
//		  }
//		  if(a[0]==a[4] && a[1]==a[3])
//		    System.out.println(m+"是一个回文数");
//		  else
//		    System.out.println(m+"不是回文数");
//	    }
//   }

	public static void palin(int n){
		int m = n;
		ArrayList<Integer> list = new ArrayList<>();
		do {
			Integer x = n % 10;
			n /= 10;
			list.add(x);
		} while (n != 0);
		int len = list.size();
		for (int i = 0; i < list.size(); i++) {
			int q = list.get(i).intValue();
			int h = list.get((len-i-1)).intValue();
			if (q == h) {
				
			}else {
				System.out.print(m+"不是一个回文数");
				System.exit(0);
			}
			//System.out.print(list.get(i).intValue()+" ");
		}
		System.out.println(m+"是一个回文数");
	}

}
