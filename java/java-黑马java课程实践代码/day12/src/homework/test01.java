package homework;

import java.util.HashSet;
import java.util.Iterator;
/**
 * 
 * HashSet
 * */
public class test01 {

	public static void main(String[] args) {
		HashSet<Student> hsStu = new HashSet<>();
		
		Student stu1 = new Student("java", 1);
		Student stu2 = new Student("C#", 2);
		Student stu3 = new Student("Phython", 3);
		Student stu4 = new Student("Android", 4);
		Student stu5 = new Student("Ios", 5);
		Student stu6 = new Student("java", 1);
		hsStu.add(stu1);hsStu.add(stu2);
		hsStu.add(stu3);hsStu.add(stu4);
		hsStu.add(stu5);hsStu.add(stu6);
		/*6.	为了保证集合存储元素的唯一性，需要在学生类中重写equals()和hashCode()方法。*/
		Iterator<Student> it = hsStu.iterator();
		while (it.hasNext()) {
			Student stuTemp = it.next();
			System.out.println("姓名："+stuTemp.getName()+",年龄："+stuTemp.getAge());
		}
		System.out.println("________________________");
		for (Student stu : hsStu) {
			System.out.println("姓名："+stu.getName()+",年龄："+stu.getAge());
		}
	}
}
