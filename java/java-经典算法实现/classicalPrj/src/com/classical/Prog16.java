package com.classical;
/***
 * 
 * @author Administrator
 *��Ŀ�����9*9�ھ���
����������������п��ǣ���9��9�У�i�����У�j�����С�
 */

public class Prog16{
	public static void main(String[] args){
		for(int i=1;i<10;i++){//�ж����У���9��
			for(int j=1;j<i+1;j++)//ÿ���ж��ٸ������
				System.out.print(j+"*"+i+"="+(j*i)+" ");
			System.out.println();
		}
	}
}

