package com.classical;
/***
 * 
 * @author Administrator
 *��Ŀ�����������������Ƕ������ɴ��⣺
 *ѧϰ�ɼ�>=90�ֵ�ͬѧ��A��ʾ��60-89��֮�����B��ʾ��60�����µ���C��ʾ��
���������(a>b)?a:b��������������Ļ������ӡ� 

 */
public class Prog5{
	public static void main(String[] args){
		int n = -1;
		try{
			System.out.println("������ɼ�");
			n = Integer.parseInt(args[0]);
		}catch(ArrayIndexOutOfBoundsException e){//��������쳣
			return;
		}
		grade(n);
	}
	
	//�ɼ��ȼ�����
	private static void grade(int n){
		if(n>100 || n<0)
		  System.out.println("������Ч");//�����쳣����
		else{
		  String str = (n>=90)?"�֣�����A��":((n>60)?"�֣�����B��":"�֣�����C��");//��Ԫ�ж�ʽ
		  System.out.println(n+str);
		}
	}
}

