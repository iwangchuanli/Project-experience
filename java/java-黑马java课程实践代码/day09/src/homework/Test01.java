package homework;

import java.util.Scanner;

/***
 * 
 * @author Administrator
 *
1.�����Ҫ���ĳ���ַ�����һ����Ӧ����ʲô����   
 ����  �ַ���"abcde"  ��Ҫ�õ�"bc"
 */
public class Test01 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String str;
		System.out.println("������һ���ַ�����");
		str = input.next();
		
		int beginIndex,endIndex;
		System.out.println("���ַ�����ʼ��λ�ã�");
		beginIndex = input.nextInt();
		System.out.println("���ַ���������λ�ã�");
		endIndex = input.nextInt();
		
		int getStrCode = str.codePointCount(beginIndex, endIndex);
		String getNewStr = str.substring(beginIndex, endIndex);
		//substring(int beginIndex, int endIndex) 
        	//����һ�����ַ��������Ǵ��ַ�����һ�����ַ�����String ���͵�
		System.out.println(getStrCode);
		System.out.println(getNewStr);
	}
}
