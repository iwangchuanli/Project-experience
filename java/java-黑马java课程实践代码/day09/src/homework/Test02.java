package homework;

import java.util.Scanner;

/***
 * 
 * @author Administrator
 *�����Ҫ֪��  'a'  �� �ַ���"sbwesawer"�г��ֵ�����λ��Ӧ����ʲô����
 */
public class Test02 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("������һ���ַ�����");
		String str = input.next();
		
		System.out.println("������һ����Ҫ���ҵ��ַ���");
		String x = input.next();
		
		int a ;
		int fromIndex = 0 ;
		System.out.println("�ַ����ڵ�λ���У�");

		while (fromIndex < str.length()) {
			a = str.indexOf(x, fromIndex);
			System.out.print((a+fromIndex+1)+" ");
			fromIndex = a;
		}
		
		
	}

}
