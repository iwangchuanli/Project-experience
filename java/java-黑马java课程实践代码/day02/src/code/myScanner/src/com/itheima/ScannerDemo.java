package code.myScanner.src.com.itheima;
import java.util.Scanner;
/*
 * Ϊ����߳��������ԣ����ǰ����ݸĽ�Ϊ����¼�롣
 * ���ʵ�ּ���¼��������?Ŀǰʹ��JDK�ṩ����Scanner��
 * ���ʹ��Scanner����ȡ������?Ŀǰ��Ҽ�סʹ�ò��輴�ɡ�
 * 
 * ʹ�ò��裺
 * 		A:����
 * 			import java.util.Scanner;
 * 			ע�⣺��һ�����У���������˳���ϵ
 * 				package > import > class
 * 		B:��������¼�����
 * 			Scanner sc = new Scanner(System.in);
 * 		C:��ȡ����
 * 			int i = sc.nextInt();
 */
public class ScannerDemo {
	public static void main(String[] args) {
		//��������¼�����
		Scanner sc = new Scanner(System.in);
		
		//������ʾ
		System.out.println("������һ��������");
		//��ȡ����
		int i = sc.nextInt();
		
		//�ѻ�ȡ���������
		System.out.println("i:"+i);
	}
}
