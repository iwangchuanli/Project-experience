package com.classical;
/***
 * ��Ŀ��һ���100�׸߶��������£�ÿ����غ�����ԭ�߶ȵ�һ�룻�����£�
 * ������ ��10�����ʱ�������������ף���10�η�����ߣ�
 */
import java.util.Scanner;
public class Prog10{
	public static void main(String[] args){
		System.out.print("������С�����ʱ�ĸ߶Ⱥ����Ĵ�����");
		Scanner scan = new Scanner(System.in).useDelimiter("\\s");//�ո�ָ����뽵��߶Ⱥʹ���
		int h = scan.nextInt();
		int n = scan.nextInt();
		scan.close();
		distance(h,n);//���÷���
	}
	//С���h�߶����£���n�η����󾭹��ľ���ͷ����ĸ߶�
	private static void distance(int h,int n){
		double length = 0;
		for(int i=0;i<n;i++){
			length += h;//�ۼ����ܹ�������·��
			h /=2.0 ;//ÿ����غ�����ԭ�߶ȵ�һ��
		}
		System.out.println("������"+n+"�η�����С�򹲾���"+length+"�ף�"+"��"+n+"�η����߶�Ϊ"+h+"��");
	}
}

