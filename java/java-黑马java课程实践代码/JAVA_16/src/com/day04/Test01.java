package com.day04;

import java.util.HashMap;
import java.util.Set;

/**
 * 一、现在有一个map集合如下：
	HashMap<Integer,String> map = new HashMap<Integer, String>();
			map.put(1, "张三丰");
			map.put(2, "周芷若");
			map.put(3, "汪峰");
			map.put(4, "灭绝师太");
	要求：
	1.遍历集合，并将序号与对应人名打印。
	2.向该map集合中插入一个编码为5姓名为李晓红的信息
		3.移除该map中的编号为1的信息 
		4.将map集合中编号为2的姓名信息修改为"周林"
 * @author Administrator
 *
 */
public class Test01 {

	public static void main(String[] args) {
		HashMap<Integer,String> map = new HashMap<Integer, String>();
		map.put(1, "张三丰");
		map.put(2, "周芷若");
		map.put(3, "汪峰");
		map.put(4, "灭绝师太");
		
		Set<Integer> set = map.keySet();
		for (Integer i : set) {
			String s = map.get(i);
			System.out.println("编号："+i+"，姓名："+s);
		}
		System.out.println("-----------------------");
		map.put(5, "李晓红");
		map.remove(1);
		map.put(2,"周林");
		for (Integer i1 : set) {
			String s = map.get(i1);
			System.out.println("编号："+i1+"，姓名："+s);
		}
	}
}
