package com.classical;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ��Ŀ����n��������ʹ��ǰ�����˳�������m��λ�ã����m���������ǰ���m����
 * @author Administrator
 *
 */
//import java.util.Scanner;
//public class Prog36{
//	public static void main(String[] args){
//		final int N = 10;
//		System.out.print("������10���������飺");
//		Scanner scan = new Scanner(System.in);
//		int[] a = new int[N];
//		for(int i=0;i<a.length;i++)
//		  a[i] = scan.nextInt();
//		System.out.print("������һ��С��10������");
//		int m = scan.nextInt();
//		scan.close();
//		int[] b = new int[m];
//		int[] c = new int[N-m];
//		for(int i=0;i<m;i++)
//		  b[i] = a[i];
//		for(int i=m,j=0;i<N;i++,j++)
//		  c[j] = a[i];
//		for(int i=0;i<N-m;i++)
//		  a[i] = c[i];
//		for(int i=N-m,j=0;i<N;i++,j++)
//		  a[i] = b[j];
//		for(int i=0;i<a.length;i++)
//		  System.out.print(a[i]+" ");
//	}
//}


public class Prog36 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int []a = {1,2,3,4,5,6,7,8,9};
		System.out.print("ǰ�����˳������ƶ��ٸ�λ�ã�");
		int m = input.nextInt();
		ArrayList<Integer> list1 = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();
		int index = a.length - m;
		for (int i = 0; i < a.length; i++) {
			if (i < index) {
				list1.add(a[i]);
			}else
				list2.add(a[i]);
		}
		System.out.println(list1+" "+list2);
		list1.addAll(0,list2);
		System.out.println(list1+" ");
		
	}
	
}
