package com.classical;
/**
 * ��Ŀ����5��������һ��
 * �ʵ�����˶����ꣿ��˵�ȵ�4���˴�2�ꡣ
 * �ʵ�4������������˵�ȵ�3���˴�2�ꡣ
 * �ʵ������ˣ���˵�ȵ�2�˴����ꡣ
 * �ʵ�2���ˣ�˵�ȵ�һ���˴����ꡣ
 * ����ʵ�һ���ˣ���˵��10�ꡣ���ʵ�����˶�� 
������������õݹ�ķ������ݹ��Ϊ���ƺ͵��������׶Ρ�
Ҫ��֪�����������������֪�������˵�������
�������ƣ��Ƶ���һ�ˣ�10�꣩���������ơ�

 * @author Administrator
 *
 */
//public class Prog23{
//	public static void main(String[] args){
//		System.out.println(getAge(5,2));
//	}
//	//���mλͬ־������
//	private static int getAge(int m,int n){
//		if(m==1)
//		  return 10;
//		else
//		  return getAge(m-1,n)+n;		
//	}
//}

 
public class Prog23 {

	public static void main(String[] args) {
		System.out.println(dg(5));
	}
	public static int dg(int n){
		if (n == 1) {
			return 10;
		}else {
			int age = 2+dg(n-1);
			return age;
		}
	}
}
