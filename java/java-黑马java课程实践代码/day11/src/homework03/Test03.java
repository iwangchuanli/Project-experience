package homework03;

import java.util.ArrayList;
import java.util.List;

import homework02.Student;

public class Test03 {

	public static void main(String[] args) {
		List<Student> listStu = new ArrayList<>();
		
		Student stu1 = new Student("java", 1);
		Student stu2 = new Student("c#", 2);
		Student stu3 = new Student("phython", 3);
		Student stu4 = new Student("android", 4);
		Student stu5 = new Student("ios", 5);
		listStu.add(stu1);
		listStu.add(stu2);
		listStu.add(stu3);
		listStu.add(stu4);
		listStu.add(stu5);
		
		for (Student s:listStu) {
			s.show();
			System.out.println("ĞÕÃû:"+s.getName()+"ÄêÁä"+s.getAge());
			
		}
		
		
	}
}
