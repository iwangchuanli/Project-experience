package com.day02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 * 三、定义一个集合，存储一些元素。并使用增强for循环遍历输出
 * @author Administrator
 *
 */
public class Test03 {

	public static void main(String[] args) {
		List<Student> listStu = new ArrayList<>();
		
		Student stu1 = new Student("java", 1,"男");
		Student stu2 = new Student("c#", 2,"女");
		Student stu3 = new Student("phython", 3,"男");
		Student stu4 = new Student("android", 4,"女");
		Student stu5 = new Student("ios", 5,"男");
		Student stu6 = new Student("java", 1,"女");
		listStu.add(stu1);
		listStu.add(stu2);
		listStu.add(stu3);
		listStu.add(stu4);
		listStu.add(stu5);
		listStu.add(stu6);
		
		//迭代遍历
		Iterator<Student> it = listStu.iterator();
		while (it.hasNext()) {
			it.next().show();
		}
		
		//普通遍历
		System.out.println("------------------------------------");
		for (int i = 0; i < listStu.size(); i++) {
			listStu.get(i).show();
		}
		
		//增强for遍历
		System.out.println("---------------------------------------");
		for (Student stu : listStu) {
			stu.show();
		}
	}
}
