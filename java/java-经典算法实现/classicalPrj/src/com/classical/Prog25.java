package com.classical;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ��Ŀ��һ��5λ�����ж����ǲ��ǻ���������12321�ǻ���������λ����λ��ͬ��ʮλ��ǧλ��ͬ��
 * @author Administrator
 *
 */
import java.io.*;
public class Prog25{
	public static void main(String[] args){
		int n = 0;
		System.out.print("������һ��nλ��������");
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
//			System.out.println("����Ĳ���5λ����");
//			return;
//		}else{
//		  for(int i=0;i<5;i++){
//			  a[i] = n%10;
//			  n /= 10;
//		  }
//		  if(a[0]==a[4] && a[1]==a[3])
//		    System.out.println(m+"��һ��������");
//		  else
//		    System.out.println(m+"���ǻ�����");
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
				System.out.print(m+"����һ��������");
				System.exit(0);
			}
			//System.out.print(list.get(i).intValue()+" ");
		}
		System.out.println(m+"��һ��������");
	}

}
