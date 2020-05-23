package com.day03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 一、根据需求完成代码
	1.定义一个Person类
		成员变量：name   age
		构造方法、get和set、toSring
	2.创建三个Person对象
		Person("张三",23);
		Person("李四",24);
		Person("张三",23);
	3.将这三个对象存储到Set集合中。(同姓名同年龄的为重复值、不存储)
	4.使用迭代器遍历集合
 * @author Administrator
 *
 */
public class Test01 {

	public static void main(String[] args) {
		HashSet<Person> per = new HashSet<>();
		Person per1 = new Person("张三",23);
		Person per2 = new Person("李四",24);
		Person per3 = new Person("张三",23);
		per.add(per1);per.add(per2);per.add(per3);
		
		Iterator<Person> it = per.iterator();
		while (it.hasNext()) {
			String s = it.next().toString();
			System.out.println(s);
		}
	}
}
