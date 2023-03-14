package homework02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Test02 {

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
		
		Iterator<Student> itStu = listStu.iterator();
		while (itStu.hasNext()) {
			itStu.next().show();
		}
		System.out.println("--------------");
		for (int i = 0; i < listStu.size(); i++) {
			listStu.get(i).show();
		}
		
	}
}
