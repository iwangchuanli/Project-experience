package code.myIf.src.com.itheima;

import java.util.Scanner;

/*
 * ���󣺼���¼���������ݣ���ȡ���������ݵĽϴ�ֵ
 * 
 * ������
 * 		A:�����˼���¼�룬���Ǿ�Ӧ���뵽����¼�����������
 * 			��������������¼����󣬻�ȡ����
 * 		B:��ȡ�������ݵĽϴ�ֵ����if���ĸ�ʽ2�Ϳ���ʵ���ж�
 * 		C:�ѽϴ�Ľ���������
 * 
 * ������
 * 		A:�ֶ�����
 * 		B:�������Զ�����
 * 		C:��ݼ�
 * 			ctrl+shift+o
 */
public class IfTest {
	public static void main(String[] args) {
		//��������¼�����
		Scanner sc = new Scanner(System.in);
		
		//������ʾ
		System.out.println("�������һ��������");
		int a = sc.nextInt();
		
		System.out.println("������ڶ���������");
		int b = sc.nextInt();
		
		//if��ʽ2ʵ���ж�
		/*
		if(a > b) {
			System.out.println("�ϴ��ֵ�ǣ�"+a);
		}else {
			System.out.println("�ϴ��ֵ�ǣ�"+b);
		}
		*/
		
		//���ǻ�ȡ���ϴ��ֵδ������������ܻ�����������Ĳ�������������Ҫ�Ľ�����
		//����һ���������ڱ���ϴ��ֵ
		int max;
		
		if(a > b) {
			max = a;
		}else {
			max = b;
		}
		
		//�ҿ�����
		//max += 10;
		
		System.out.println("�ϴ��ֵ�ǣ�"+max);
	}
}
