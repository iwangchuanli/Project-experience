package homework;

import java.util.Scanner;

/**7.������һ���ַ����е�������ĸ����ɴ�дӦ����ʲô����*/
public class Test07 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("������һ���ַ�����");
		String str = input.next();
		/*String 
		 * toUpperCase() 
          	ʹ��Ĭ�����Ի����Ĺ��򽫴� String �е������ַ���ת��Ϊ��д�� */
		str = str.toUpperCase();
		System.out.println("ת����"+str);
	}
}
