package homework;

import java.util.Scanner;

/***
 * 5.�����Ҫ�ж�һ���ַ����Ƿ�������ĳ���ַ�����βӦ����ʲô����  
 *  ���� �ж�"abcqwe" �Ƿ��� "abc" ��β
 * @author Administrator
 *
 */
public class Test05 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("������һ���ַ�����");
		String str = input.next();
		
		System.out.println("�������жϽ�β�ַ�����");
		String suffix = input.next();
		
		boolean flag = str.endsWith(suffix);
		
		if (flag == true) {
			System.out.println("����ַ�������"+suffix+"��β�ġ�");
		}else
			System.out.println("����ַ���������"+suffix+"��β�ġ�");
	}
}
