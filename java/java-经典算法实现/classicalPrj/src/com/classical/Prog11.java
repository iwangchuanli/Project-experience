package com.classical;
/***
 * 
 * @author Administrator
 *��Ŀ����1��2��3��4�����֣�����ɶ��ٸ�������ͬ�����ظ����ֵ���λ�������Ƕ��٣�
��������������ڰ�λ��ʮλ����λ�����ֶ���1��2��3��4��������е����к���ȥ �����������������С�
 */
public class Prog11{
	public static void main(String[] args){
		int count = 0;
		int n = 0;
		for(int i=1;i<5;i++){//��λ��
			for(int j=1;j<5;j++){//ʮλ��
				if(j==i)//��֤��λ��ʮλ������ͬ
				  continue;
				for(int k=1;k<5;k++){//��λ��
					if(k!=i && k!=j){//������ͬ
						n = i*100+j*10+k;//�õ�������ͬ�����ظ����ֵ���λ��n
					  System.out.print(n+" ");
					  if((++count)%5==0)//���������ʽ
					  System.out.println();
					}
				}
			}
		}
		System.out.println();
		System.out.println("����������������"+count+"��");
	}
}

