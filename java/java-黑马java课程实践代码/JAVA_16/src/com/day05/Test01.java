package com.day05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 一、看代码分析结果
	1.举例：
	public static void main(String[]args){
		 String str=null;
		 System.out.println(str.length());
	}

	答： java.lang.NullPointerException	运行时错误
		str.length() 为空，取不到值，


	2.举例：
	public static void main(String[]args){
		int arr[]={1,2};
		System.out.println(arr[2]);
	}

	答： java.lang.ArrayIndexOutOfBoundsException 运行时错误
		arr的索引为0,1,	arr[2]超范围了


	3.举例：
	public static void main(String[]args){
		System.out.println(1/0);
	}

	答： java.lang.ArithmeticException: / by zero
		除数不能为0


	4.举例：
	public static void main(String[]args){
		System.out.println(Integer.parseInt("itcast"));
	}

	答： java.lang.NumberFormatException: For input string: "itcast"
		如果字符串不包含可解析的整数时抛出此错误

	5.举例：
	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		
		try {
			Date date = format.parse("2018-04-03");
			System.out.println("程序正常");
			
		} catch (ParseException e) {
			System.out.println("程序异常");
		}
	}

	答：ParseException  输入字符可能不符合规则，或者不存在时进行parse转换时出错
	
 * @author Administrator
 *
 */
public class Test01 {
	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		
		try {
			Date date = format.parse("2018-04-03");
			System.out.println("程序正常");
			
		} catch (ParseException e) {
			System.out.println("程序异常");
		}
	}

}
