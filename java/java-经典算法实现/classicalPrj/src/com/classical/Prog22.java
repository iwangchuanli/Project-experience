package com.classical;
/**
 * 
 * @author Administrator
 *��Ŀ�����õݹ鷽����5!��
����������ݹ鹫ʽ��fn=fn_1*4!

 */
/**
 * public class Prog22{
	public static void main(String[] args){
		System.out.println(fact(10));
	}
	//�ݹ���׳�
	private static long fact(int n){
		if(n==1)
		  return 1;
		else
		  return fact(n-1)*n;
	}
}

 * @author Administrator
 *
 */
public class Prog22 {

	public static void main(String[] args) {
		System.out.println(fn(5));
	}
	public static int fn(int n){
		if (n == 1) {
			return 1;
		}else
			return n*fn(n-1);
	}
}
