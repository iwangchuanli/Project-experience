package homework;

import java.util.Scanner;

/**8.������һ���ַ����е�������ĸ�����СдӦ����ʲô����*/
public class Test08 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("������һ���ַ�����");
		String str = input.next();
		/*String 
		 * toLowerCase() 
          	ʹ��Ĭ�����Ի����Ĺ��򽫴� String �е������ַ���ת��ΪСд��  */
		str = str.toLowerCase();
		System.out.println("ת����"+str);
	}
}
