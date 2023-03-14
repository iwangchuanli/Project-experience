package homework04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import homework02.Student;

public class Test04 {

	public static void main(String[] args) {
		List<Student> listStu = new ArrayList<>();
		
		Student stu1 = new Student("java", 1);
		Student stu2 = new Student("c#", 2);
		Student stu3 = new Student("phython", 3);
		Student stu4 = new Student("android", 4);
		Student stu5 = new Student("ios", 5);
		Student stu6 = new Student("java", 1);
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
		System.out.println("-------------");
		for (int i = 0; i < listStu.size(); i++) {
			listStu.get(i).show();
		}
		
		//增强for遍历
		System.out.println("-------------");
		for (Student stu : listStu) {
			stu.show();
		}
	}
}
