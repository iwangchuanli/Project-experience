package com.classical;
/***
 * ��Ŀ����s=a+aa+aaa+aaaa+aa...a��ֵ������a��һ�����֡�
 * ����2+22+222+2222+22222(��ʱ����5�������)������������м��̿��ơ� 
����������ؼ��Ǽ����ÿһ���ֵ��
 */
import java.util.Scanner;

public class Prog8{
	public static void main(String[] args){
		System.out.print("��s=a+aa+aaa+aaaa+...��ֵ��������a��ֵ��");
		Scanner scan = new Scanner(System.in).useDelimiter("\\s*");//�Կո���Ϊ�ָ�����������a,n
		int a = scan.nextInt();
		int n = scan.nextInt();
		scan.close();//�ر�ɨ����
		System.out.println(expressed(2,5)+add(2,5));
	} 
	
	//��ͱ��ʽ
	private static String expressed(int a,int n){
		/*StringBuffer �̰߳�ȫ�Ŀɱ��ַ����С�һ�������� String ���ַ������������������޸ġ�
		 *��Ȼ������ʱ�������������ĳ���ض����ַ����У���ͨ��ĳЩ�������ÿ��Ըı�����еĳ��Ⱥ����ݡ� 
		 * */
		StringBuffer sb = new StringBuffer();//���ʽ
		StringBuffer subSB = new StringBuffer();//���ʽ��ÿһ��
		/*��Ҫ������ append �� insert ����
		 * append ����ʼ�ս���Щ�ַ���ӵ���������ĩ�ˣ��� insert ��������ָ���ĵ�����ַ���
		 * */
		for(int i=1;i<n+1;i++){
		  subSB = subSB.append(a);//���ʽ��ÿһ��
		  sb = sb.append(subSB);
		  if(i<n)//ÿһ�������
		    sb = sb.append("+");
		}
		sb.append("=");
		return sb.toString();
	}
	//���
	private static long add(int a,int n){
		long sum = 0;
		long subSUM = 0;
		for(int i=1;i<n+1;i++){
			subSUM = subSUM*10+a;//��һλ*10�����ϸ�λ
			sum = sum+subSUM;//�������
		}
		return sum;
	}
}
