package com.classical;

import java.util.Scanner;

/**
 * 
 * @author Administrator
 *��Ŀ����һ�������У�2/1��3/2��5/3��8/5��13/8��21/13...���������е�ǰ20��֮�͡�
�����������ץס�������ĸ�ı仯���ɡ�

 */

public class Prog20{
	public static void main(String[] args){
		double n1 = 1;
		double n2 = 1;
		double fraction = n1/n2;
		double Sn = 0;
		for(int i=0;i<20;i++){
		  double t1 = n1;
		  double t2 = n2;
		  n1 = t1+t2;
		  n2 = t1;
		  fraction = n1/n2;
		  Sn += fraction; 
		}
		System.out.print(Sn);
	}
}
//
//public class Prog20 {
//
//	public static void main(String[] args) {
//		Scanner input = new Scanner(System.in);
//		double fz = 1;		double fm = 2;
//		double temp; 
//		double sum = fz / fm;
//		System.out.print("�����������������");
//		int n = input.nextInt();
//		//�ӵڶ��ʼ��
//		for (int i = 0; i < n; i++) {
//			temp = fm;
//			fm = fm + fz;
//			fz = temp;
//			temp = fz / fm;
//			sum = sum + temp;
//			System.out.print(fm+"/"+fz+"+");
//		}
//		System.out.println(" "+'\n'+sum);
//		
//	}
//}
