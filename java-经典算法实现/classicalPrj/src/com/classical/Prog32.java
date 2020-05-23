package com.classical;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 题目：取一个整数a从右端开始的4～7位。 程序分析：可以这样考虑： (1)先使a右移4位。
 * (2)设置一个低4位全为1,其余全为0的数。可用~(~0<<4) (3)将上面二者进行&运算。
 * 
 * @author Administrator
 *
 */

//import java.util.Scanner;
//public class Prog32{
//	public static void main(String[] msg){
//		//输入一个长整数
//		Scanner scan = new Scanner(System.in);
//		long l = scan.nextLong();
//		scan.close();
//		//以下截取字符
//		String str = Long.toString(l);
//		char[] ch = str.toCharArray();
//		int n = ch.length;
//		if(n<7)
//		  System.out.println("输入的数小于7位！");
//		else
//		  System.out.println("截取的4~7位数字："+ch[n-7]+ch[n-6]+ch[n-5]+ch[n-4]);
//		}	  
//}

public class Prog32 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		//Integer num = new Integer(str);
		int count = 0;
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i <= str.length(); i++) {
			list.add(str.split("")[i]);
			System.out.print(list.get(i)+" ");
		}
		System.out.print('\n'+"取右端开始的4～7位：");
		for (int i = 0; i < list.size(); i++) {
			if (i > list.size()-8 && i < list.size()-3) {
				System.out.print(list.get(i)+" ");
			}
		}
	}
}
