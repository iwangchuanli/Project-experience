package com.classical;

import java.util.Scanner;

/***
 * 
 * @author Administrator
 *��Ŀ����һ���������ֽ���������
 *���磺����90,��ӡ��90=2*3*3*5��
 *
�����������n���зֽ���������Ӧ���ҵ�һ����С������k��Ȼ������������ɣ�
(1)����������ǡ����n����˵���ֽ��������Ĺ����Ѿ���������ӡ�����ɡ�
(2)���n<>k����n�ܱ�k��������Ӧ��ӡ��k��ֵ������n����k����,��Ϊ�µ�������n,�ظ�ִ�е�һ����
(3)���n���ܱ�k����������k+1��Ϊk��ֵ,�ظ�ִ�е�һ����

 */
public class Prog4{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.print("������һ��������");
		int n = input.nextInt();
		decompose(n);
	}
	//�ֽ�����
	private static void decompose(int n){
		System.out.print(n+"=");
		for(int i=2;i<n+1;i++){
			while(n%i==0 && n!=i){//n�ܱ�i������������������i������һ������
				n/=i;
				System.out.print(i+"*");
			}
			if(n==i){
				System.out.println(i);
				break;
			}
		}
	}
}

