package com.classical;
/***
 * ��Ŀ������ĳ��ĳ��ĳ�գ��ж���һ������һ��ĵڼ��죿
�����������3��5��Ϊ����Ӧ���Ȱ�ǰ�����µļ�������Ȼ���ټ���5�켴����ĵڼ��죬��������������������·ݴ���3ʱ�迼�Ƕ��һ�졣
 */
import java.util.Scanner;
public class Prog14{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in).useDelimiter("\\D");//ƥ�������
		System.out.print("�����뵱ǰ���ڣ���-��-�գ�:");
		int year = scan.nextInt();
		int month = scan.nextInt();
		int date = scan.nextInt();
		scan.close();
		analysis(year,month,date);
		//System.out.println("������"+year+"��ĵ�"++"��");
	}
	//�ж�����
	private static void analysis(int year, int month, int date){
		int n = 0;
		int[] month_date = new int[] {0,31,28,31,30,31,30,31,31,30,31,30,31};
		if((year%400)==0 || ((year%4)==0)&&((year%100)!=0))//�ж����껹��ƽ��
		  month_date[2] = 29;//���·����ڲ�ͬ
		for(int i=0;i <= month-1;i++)
		  n += month_date[i];//���µ��������
		if (date <= month_date[month]) {
			n = n+date;//�����ϸ��µ�����
			System.out.println("������"+year+"��ĵ�"+n+"��");
		}
		else
			System.out.println("���������쳣");
	}
}

