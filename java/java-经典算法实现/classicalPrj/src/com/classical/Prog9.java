package com.classical;
/***
 * @author Administrator
 *��Ŀ��һ�������ǡ�õ�����������֮�ͣ�������ͳ�Ϊ"����"��
 *����6=1��2��3.����ҳ�1000���ڵ�����������
 */
public class Prog9{
	public static void main(String[] args){
		int n = 10000;
		compNumber(n);//���÷���
	}
	//������
	private static void compNumber(int n){
		int count = 0;//����
		System.out.println(n+"���ڵ�������");
		
		for(int i=1;i<n+1;i++){//i������
			int sum = 0;
			for(int j=1;j<i/2+1;j++){//j������i������
				if((i%j)==0){//��������jΪ����һ������
					sum += j;//������֮��
					if(sum==i){//������ʱ������ô��
						System.out.print(i+" ");
						if((count++)%5==0)//���������ʽ
							System.out.println();
					}
				}
			}
		}
	}
}
