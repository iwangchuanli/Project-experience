package com.day04;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * 三、分析以下需求，并用代码实现	
	1.利用键盘录入，输入一个字符串
	2.统计该字符串中各个字符的数量(提示:字符不用排序)
	3.如：
		用户输入字符串"If~you-want~to~change-your_fate_I_think~you~must~come-to-the-dark-horse-to-learn-java"
		程序输出结果：-(9)I(2)_(3)a(7)c(2)d(1)e(6)f(2)g(1)h(4)i(1)j(1)k(2)l(1)m(2)n(4)o(8)r(4)s(2)t(8)u(4)v(1)w(1)y(3)~(6)

 * @author Administrator
 *
 */
public class Test03 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("输入一个字符串：");
		String str = input.nextLine();
		
		HashMap<String, Integer> haMap = new HashMap<>();
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			String s = str.split("")[i];
			System.out.print(s+" ");
			if (haMap.containsKey(s)) {
				count = haMap.get(s);
				count ++;
				haMap.put(s, count);
			}else {
				haMap.put(s, 1);
			}
		}
		System.out.println("");
		Set<String> set = haMap.keySet();
		for (String ss : set) {
			int num = haMap.get(ss);
			System.out.print(ss+"("+num+")"+" ");
		}
	}
}
