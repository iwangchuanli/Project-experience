package com.day02;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * ��������һ��ѧ����Student�����������������������䡢�Ա𣬴�������ѧ���������ArrayList�����С�
	A��ʹ�õ������������ϡ�
	B�������������ѧ����Ȼ�󽫸ö����������Ϊ��С�����档
 * @author Administrator
 *
 */
public class Test02 {

	public static void main(String[] args) {
		ArrayList<Student> stu = new ArrayList<>();
		Student stu1 = new Student("android", 1, "��");
		Student stu2 = new Student("ios", 2, "Ů");
		Student stu3 = new Student("java", 3, "��");
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
			System.out.println("������"+stuTemp.getName()+"������"+stuTemp.getAge()+"���Ա�"+stuTemp.getSex());
		}
		System.out.println("-----------------------------");
		stu.get(index).setName("С������");
		for (Student st : stu) {
			System.out.println("������"+st.getName()+"������"+st.getAge()+"���Ա�"+st.getSex());
		}
		
	}
}
