package homework;

import java.util.Scanner;

/**
 * 6.�����Ҫ�ж�һ���ַ����Ƿ����ĳ���ַ���Ӧ����ʲô����  
 *  ���� �ж�"abcqwe" �Ƿ����  "cq" 
 * */

public class Test06 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("������һ���ַ�����");
		String str = input.next();
		
		System.out.println("�������ж��ַ�����");
		CharSequence regex = input.next();
		/*boolean 
		 * contains(CharSequence s) 
          	���ҽ������ַ�������ָ���� char ֵ����ʱ������ true��
           */
		boolean flag = str.contains(regex);
		
		if (flag == true) {
			System.out.println(str+"����"+regex);
		}else
			System.out.println(str+"������"+regex);
	}
}
