package code.mySwitch.src.com.itheima;

import java.util.Scanner;

/*
 * switch����ʽ��
 * 		switch(���ʽ) {
 * 			case ֵ1:
 * 				�����1;
 * 				break;
 * 			case ֵ2:
 * 				�����2;
 * 				break;
 * 			case ֵ3:
 * 				�����3;
 * 				break;
 * 			...
 * 			default:
 * 				�����n+1;
 * 				break;
 * 		}
 * 
 * ��ʽ���ͣ�
 * 		���ʽ��byte,short,int,char
 * 			JDK5�Ժ������ö��,JDK7�Ժ�������ַ���
 * 		case�����ֵ���������ͱ��ʽ��ֵ����ƥ���
 * 		break����ʾ�жϵ���˼
 * 		default�����е�ֵ���ͱ��ʽ��ƥ�䣬��ִ��default��Ӧ������
 * 
 * ִ�����̣�
 * 		A:������ʽ��ֵ
 * 		B:�������ֵ���κ�case�����ֵ���бȶԣ�һ����ƥ��ģ���ִ�ж�Ӧ����䣬��ִ�еĹ����У�����break�ͽ�����
 * 		C:������е�case����ƥ�䣬��ִ�������n+1
 * 
 * ������
 * 		���ݼ���¼�������1-7�������Ӧ������һ��������
 * 
 * ��ݼ����Դ�����и�ʽ��
 * 		ctrl+shift+f
 */
public class SwitchDemo {
	public static void main(String[] args) {
		// ��������¼������
		Scanner sc = new Scanner(System.in);

		// ������ʾ
		System.out.println("������һ������(1-7)��");
		int weekDay = sc.nextInt();

		// ��switch���ʵ���ж�
		switch (weekDay) {
		case 1:
			System.out.println("����һ");
			break;
		case 2:
			System.out.println("���ڶ�");
			break;
		case 3:
			System.out.println("������");
			break;
		case 4:
			System.out.println("������");
			break;
		case 5:
			System.out.println("������");
			break;
		case 6:
			System.out.println("������");
			break;
		case 7:
			System.out.println("������");
			break;
		default:
			System.out.println("���������������");
			break;
		}
	}
}
