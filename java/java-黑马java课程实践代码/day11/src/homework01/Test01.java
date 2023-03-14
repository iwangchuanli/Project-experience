package homework01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Test01 {

	public static void main(String[] args) {
		//创建集合对象
		Collection<Student> collStu = new ArrayList<Student>();
		
		Student stu1 = new Student("java", 1);
		Student stu2 = new Student("c#", 2);
		Student stu3 = new Student("phython", 3);
		Student stu4 = new Student("android", 4);
		Student stu5 = new Student("ios", 5);
		collStu.add(stu1);
		collStu.add(stu2);
		collStu.add(stu3);
		collStu.add(stu4);
		collStu.add(stu5);
		
		Iterator<Student> itStu = collStu.iterator();
		//返回的是迭代器接口的实现类的对象
		while (itStu.hasNext()) {
			itStu.next().show();
			//System.out.println();
		}
	}
}
