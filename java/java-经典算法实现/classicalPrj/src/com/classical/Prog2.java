package com.classical;
/***
 * 
 * @author Administrator
 *��Ŀ���ж�101-200֮���ж��ٸ����������������������       ֻ��1��������������
����������ж������ķ�������һ�����ֱ�ȥ��2��sqrt(�����)������ܱ����������������������������֮��������
 */
public class Prog2{
	public static void main(String[] args){
		int m = 1;
		int n = 1000;
		int count = 0;
		//ͳ����������
		for(int i=m;i<n;i++){//1~n֮��һ��һ������
			if(isPrime(i)){//������
				count++;//����+1�����
				System.out.print(i+" ");
				if(count%10==0){//ʮ�������������
					System.out.println();
				}
			}
		}
		
		System.out.println();
		System.out.println("��"+m+"��"+n+"֮�乲��"+count+"������");
	}
	//�ж�����
	private static boolean isPrime(int n){//boolean�ͷ���ֵ
		boolean flag = true;
		if(n==1)
		  flag = false;//1����������������������
		else{
			for(int i=2;i<=Math.sqrt(n);i++){
			if((n%i)==0 || n==1){
				flag = false;
				break;
			}
			 else
			   flag = true;
		  }
		}
		return flag;
	}
}
