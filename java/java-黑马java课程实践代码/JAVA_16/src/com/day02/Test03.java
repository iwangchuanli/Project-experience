package com.day02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 * ��������һ�����ϣ��洢һЩԪ�ء���ʹ����ǿforѭ���������
 * @author Administrator
 *
 */
public class Test03 {

	public static void main(String[] args) {
		List<Student> listStu = new ArrayList<>();
		
		Student stu1 = new Student("java", 1,"��");
		Student stu2 = new Student("c#", 2,"Ů");
		Student stu3 = new Student("phython", 3,"��");
		Student stu4 = new Student("android", 4,"Ů");
		Student stu5 = new Student("ios", 5,"��");
		Student stu6 = new Student("java", 1,"Ů");
		listStu.add(stu1);
		listStu.add(stu2);
		listStu.add(stu3);
		listStu.add(stu4);
		listStu.add(stu5);
		listStu.add(stu6);
		
		//��������
		Iterator<Student> it = listStu.iterator();
		while (it.hasNext()) {
			it.next().show();
		}
		
		//��ͨ����
		System.out.println("------------------------------------");
		for (int i = 0; i < listStu.size(); i++) {
			listStu.get(i).show();
		}
		
		//��ǿfor����
		System.out.println("---------------------------------------");
		for (Student stu : listStu) {
			stu.show();
		}
	}
}
