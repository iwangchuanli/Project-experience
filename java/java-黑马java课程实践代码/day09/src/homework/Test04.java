package homework;

import java.util.Scanner;

/***
 * 4.�����Ҫ�ж�һ���ַ����Ƿ�������ĳ���ַ�����ͷӦ����ʲô���� 
 ���� �ж�"abcqwe" �Ƿ��� "abc" ��ͷ
 * @author Administrator
 *
 */
public class Test04 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("������һ���ַ�����");
		String str = input.next();
		
		System.out.println("�������жϿ�ͷ�ַ�����");
		String prefix = input.next();
		/* boolean 
		 * startsWith(String prefix) 
          	���Դ��ַ����Ƿ���ָ����ǰ׺��ʼ�� */
		boolean flag = str.startsWith(prefix);
		if (flag == true) {
			System.out.println("����ַ�����"+prefix+"�Կ�ͷ�ġ�");
		}else
			System.out.println("����ַ�������"+prefix+"�Կ�ͷ�ġ�");
	}
}
