package com.classical;
/***
 * 
 * @author Administrator
 *��Ŀ��һ��������������100����һ����ȫƽ�������ټ���168����һ����ȫƽ���������ʸ����Ƕ��٣�
�����������10�������жϣ��Ƚ���������100���ٿ������ٽ���������268���ٿ��������������Ľ�������������������ǽ����
 */
public class Prog13{
	public static void main(String[] args){
		int n=0;
		for(int i=0;i<100001;i++){
			if(isCompSqrt(i+100) && isCompSqrt(i+268)){
				n = i;
				System.out.println("��������ǣ�"+n);
				System.out.println("�ֱ𿪷���:"+Math.sqrt(n+100)+"   "+Math.sqrt(n+268));
				//break;
			}
		}
		//System.out.println("��������ǣ�"+n);
	}
	//�ж���ȫƽ����
	private static boolean isCompSqrt(int n){
		boolean isComp = false;
		for(int i=1;i<Math.sqrt(n)+1;i++){
			if(n==Math.pow(i,2)){//Math.pow(double a, double b)���ص�һ�������ĵڶ����������ݵ�ֵ��
				isComp = true;
				break;
			}
		}
		return isComp;
	}
}
