package homework;

import java.util.Scanner;

/***
 * 
3.�����Ҫ���ַ��������е��ַ�a�滻���ַ�bӦ����ʲô����  
����: ԭ�ַ��� "absbdaa"   �滻�� "bbsbdbb"
 * @author Administrator
 *
 */
public class Test03 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("������һ���ַ�����");
		String str = input.next();
		System.out.println("��������Ҫ�滻�����ݣ�");
		CharSequence target = input.next();
		//String regex = input.next();
		System.out.println("�滻Ϊ��");
		CharSequence replaceStr = input.next();
		
		str = str.replace(target,replaceStr);
		/*String 
		 * replace(CharSequence target, CharSequence replacement) 
          	ʹ��ָ��������ֵ�滻�����滻���ַ�������ƥ������ֵĿ�����е����ַ����� */
		System.out.println("�滻����ַ���Ϊ��"+str);
	}
}
