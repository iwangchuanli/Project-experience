package com.classical;
/***
 * 
 * @author Administrator
 *��Ŀ����ӡ�����е�"ˮ�ɻ���"��
 *��ν"ˮ�ɻ���"��ָһ����λ�������λ���������͵��ڸ�������
 *���磺153��һ��"ˮ�ɻ���"����Ϊ153=1�����η���5�����η���3�����η��� 
�������������forѭ������100-999������ÿ�����ֽ����λ��ʮλ����λ��
 */
public class Prog3{
	public static void main(String[] args){
		for(int i=100;i<1000;i++){
			if(isLotus(i))
			   System.out.print(i+" ");
		}
		System.out.println();
	}
	
	//�ж�ˮ�ɻ���
	private static boolean isLotus(int lotus){
		int m = 0;
		int n = lotus;//���մ����Ĳ���
		int sum = 0;
		m = n/100;//��λ��
		n  -= m*100;//ȥ����λ����
		sum = m*m*m;//���λ����
		m = n/10;//ʮλ��
		n -= m*10;//��λ��
		sum += m*m*m + n*n*n;//����λ�õ�������
		if(sum==lotus)
			return true;// 1
		else
			return false;
		}
}

