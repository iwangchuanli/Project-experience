package com.day02;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 二、定义一个学生类Student，包含三个属性姓名、年龄、性别，创建三个学生对象存入ArrayList集合中。
	A：使用迭代器遍历集合。
	B：求出年龄最大的学生，然后将该对象的姓名变为：小猪佩奇。
 * @author Administrator
 *
 */
public class Test02 {

	public static void main(String[] args) {
		ArrayList<Student> stu = new ArrayList<>();
		Student stu1 = new Student("android", 1, "男");
		Student stu2 = new Student("ios", 2, "女");
		Student stu3 = new Student("java", 3, "男");
		stu.add(stu1);stu.add(stu2);stu.add(stu3);
		int age = 0;int i = 0;int index = 0;
		Iterator<Student> it = stu.iterator();
		while (it.hasNext()) {
			Student stuTemp = it.next();
			if (age < stuTemp.getAge()) {
				age = stuTemp.getAge();
				index = i;
			}
			i ++;
			System.out.println("姓名："+stuTemp.getName()+"，年龄"+stuTemp.getAge()+"，性别："+stuTemp.getSex());
		}
		System.out.println("-----------------------------");
		stu.get(index).setName("小猪佩奇");
		for (Student st : stu) {
			System.out.println("姓名："+st.getName()+"，年龄"+st.getAge()+"，性别："+st.getSex());
		}
		
	}
}
