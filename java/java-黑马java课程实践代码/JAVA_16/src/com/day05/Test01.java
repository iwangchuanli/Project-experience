package com.day05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * һ��������������
	1.������
	public static void main(String[]args){
		 String str=null;
		 System.out.println(str.length());
	}

	�� java.lang.NullPointerException	����ʱ����
		str.length() Ϊ�գ�ȡ����ֵ��


	2.������
	public static void main(String[]args){
		int arr[]={1,2};
		System.out.println(arr[2]);
	}

	�� java.lang.ArrayIndexOutOfBoundsException ����ʱ����
		arr������Ϊ0,1,	arr[2]����Χ��


	3.������
	public static void main(String[]args){
		System.out.println(1/0);
	}

	�� java.lang.ArithmeticException: / by zero
		��������Ϊ0


	4.������
	public static void main(String[]args){
		System.out.println(Integer.parseInt("itcast"));
	}

	�� java.lang.NumberFormatException: For input string: "itcast"
		����ַ����������ɽ���������ʱ�׳��˴���

	5.������
	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		
		try {
			Date date = format.parse("2018-04-03");
			System.out.println("��������");
			
		} catch (ParseException e) {
			System.out.println("�����쳣");
		}
	}

	��ParseException  �����ַ����ܲ����Ϲ��򣬻��߲�����ʱ����parseת��ʱ����
	
 * @author Administrator
 *
 */
public class Test01 {
	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		
		try {
			Date date = format.parse("2018-04-03");
			System.out.println("��������");
			
		} catch (ParseException e) {
			System.out.println("�����쳣");
		}
	}

}
