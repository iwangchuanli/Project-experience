package com.day02;

import java.util.ArrayList;
import java.util.Collection;

/**
 * һ���������´��룬�붨�巽��public static int listTest(Collection<String> list,String s)
 * ͳ�Ƽ�����ָ��Ԫ�س��ֵĴ�������"a":2,"b": 2,"c" :1, "xxx":0��
		Collection<String> list = new ArrayList<>();
			list.add("a");
			list.add("a");
			list.add("b");
			list.add("b");
			list.add("c");
			System.out.println("a:"+listTest(list, "a"));	
			System.out.println("b:"+listTest(list, "b"));	
			System.out.println("c:"+listTest(list, "c"));
			System.out.println("xxx:"+listTest(list, "xxx"));
 * @author Administrator
 *
 */
public class Test01 {

	public static void main(String[] args) {
		Collection<String> list = new ArrayList<>();
		list.add("a");
		list.add("a");
		list.add("b");
		list.add("b");
		list.add("c");
		System.out.println("a:"+listTest(list, "a"));	
		System.out.println("b:"+listTest(list, "b"));	
		System.out.println("c:"+listTest(list, "c"));
		System.out.println("xxx:"+listTest(list, "xxx"));
	}
	public static int listTest(Collection<String> list,String s){
		int count = 0;
		for (String str : list) {
			if (str.equals(s)) {
				count ++;
				
			}
		}
		return count;	
	}
}
