package com.classical;
/***
 * 
 * @author Administrator
 *��Ŀ����������������m��n���������Լ������С��������
�������������շ������
 */
public class Prog6{
	public static void main(String[] args){
		int m,n;
		try{
			m = Integer.parseInt(args[0]);//m,n������
			n = Integer.parseInt(args[1]);
		}catch(ArrayIndexOutOfBoundsException e){//�����쳣����
			System.out.println("��������");
			return;
		}
		max_min(m,n);//���÷��������Լ������С������
	}
	
	//�����Լ������С������
	private static void max_min(int m, int n){
		int temp = 1;
		int yshu = 1;//���Լ��
		int bshu = m*n;//��С������
		if(n<m){
			temp = n;
			n = m;
			m = temp;
		}
		while(m!=0){
			temp = n%m;
			n = m;
			m = temp;
		}
		yshu = n;
		bshu /= n;
		System.out.println(m+"��"+n+"�����Լ��Ϊ"+yshu);
		System.out.println(m+"��"+n+"����С������Ϊ"+bshu);
	}
}

