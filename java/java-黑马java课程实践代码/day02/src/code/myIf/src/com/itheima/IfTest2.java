package code.myIf.src.com.itheima;

import java.util.Scanner;

/*
 * ����¼��ѧ�����Գɼ�������ݳɼ��жϸ�ѧ�������ĸ�����
 * 90-100	����
 * 80-90	��
 * 70-80	��
 * 60-70	����
 * 60����	������
 * 
 * ������
 * A:����¼��ѧ�����Գɼ�,�뵽����¼�����ݵĲ���
 * B:ͨ���򵥵ķ���������֪���˸�ʹ��if���ĸ�ʽ3�����ж�
 * 		�����ж�ֱ�������Ӧ�ļ���
 * 
 * д�����ʱ�������ݲ��ԣ�Ӧ�ò��������ļ��������
 * 		��ȷ����
 * 		�߽�����
 * 		��������
 */
public class IfTest2 {
	public static void main(String[] args) {
		//��������¼�����
		Scanner sc = new Scanner(System.in);
		
		//������ʾ
		System.out.println("������ѧ���Ŀ��Գɼ���");
		int score = sc.nextInt();
		
		//if����ʽ3ʵ��
		/*
		if(score>=90 && score<=100) {
			System.out.println("����");
		}else if(score>=80 && score<90) {
			System.out.println("��");
		}else if(score>=70 && score<80) {
			System.out.println("��");
		}else if(score>=60 && score<70) {
			System.out.println("����");
		}else {
			System.out.println("������");
		}
		*/
		
		//ͨ�����ݵĲ��ԣ����Ƿ��ֳ��򲻹��Ͻ���δ����Ƿ����ݵ��ж�
		if(score>100 || score<0) {
			System.out.println("������ĳɼ�����");
		}else if(score>=90 && score<=100) {
			System.out.println("����");
		}else if(score>=80 && score<90) {
			System.out.println("��");
		}else if(score>=70 && score<80) {
			System.out.println("��");
		}else if(score>=60 && score<70) {
			System.out.println("����");
		}else {
			System.out.println("������");
		}
	}
}
