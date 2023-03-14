package com.day03;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 三、根据需求完成代码
	1.键盘录入一个字符串，去掉其中重复字符
    2.打印出不同的那些字符，必须保证顺序。例如输入：aaaabbbcccddd，打印结果为：abcd。
 * @author Administrator
 *
 */
public class Test03 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("input:");
		String str = input.nextLine();
		
		ArrayList<String> ss = new ArrayList<>();
		for (int i = 0; i < str.length(); i++) {
			String temp = str.split("")[i];
			//System.out.print(temp+" ");
			if (ss.contains(temp)) {
				continue;
			}else {
				ss.add(temp);
			}
		}
		
		System.out.println(ss);
	}
}
