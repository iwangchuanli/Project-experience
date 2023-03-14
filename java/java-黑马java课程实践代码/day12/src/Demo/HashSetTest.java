package Demo;

import java.util.HashSet;
import java.util.Iterator;

/*
 * HashSet集合存储自定义对象并遍历
 * 提示：自定义一个学生类，给出成员变量name和age。遍历集合的时候，在控制台输出学生对象的成员变量值。
 * 两种方式遍历
 * 		迭代器
 * 		增强for
 */
public class HashSetTest {
	public static void main(String[] args) {
		//创建集合对象
		HashSet<Student> hs = new HashSet<Student>();
		
		//创建元素对象
		Student s1 = new Student("林青霞",30);
		Student s2 = new Student("张曼玉",35);
		Student s3 = new Student("王祖贤",33);
		
		//把元素添加到集合
		hs.add(s1);
		hs.add(s2);
		hs.add(s3);
		
		//遍历
		//迭代器
		Iterator<Student> it = hs.iterator();
		while(it.hasNext()){
			Student s = it.next();
			System.out.println(s.getName()+"---"+s.getAge());
		}
		System.out.println("------------------");
		
		//增强for
		for(Student s : hs) {
			System.out.println(s.getName()+"---"+s.getAge());
		}
	}
}
