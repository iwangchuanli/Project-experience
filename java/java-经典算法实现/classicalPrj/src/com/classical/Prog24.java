package com.classical;

import java.util.Scanner;

/**
 * ��Ŀ����һ��������5λ����������Ҫ��һ�������Ǽ�λ�������������ӡ����λ���֡�
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
		
		System.out.print("����һ��"+i+"λ�����Ӹ�λ�𣬸�λ��������Ϊ��");
		for(int j=0;j<i;j++)
		  System.out.print(a[j]+" ");
	}
}

