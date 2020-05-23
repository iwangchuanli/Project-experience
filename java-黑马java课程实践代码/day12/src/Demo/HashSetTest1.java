package Demo;


import java.util.HashSet;

/*
 * HashSet集合存储自定义对象并遍历
 * 要求：如果对象的成员变量值相同，就认为是同一个元素。
 * 提示：自定义一个学生类，给出成员变量name和age。遍历集合的时候，在控制台输出学生对象的成员变量值。
 * 两种方式遍历
 * 		迭代器
 * 		增强for
 * 
 * 因为我们存储的元素所属的类没有重写hashCode()和equals()方法，所以保证不了元素的唯一性。
 * 而我想保证，怎么办呢?重写这两个方法就可以了。
 * 如何重写呢?自动生成就可以了。
 */
public class HashSetTest1 {
	public static void main(String[] args) {
		//创建集合对象
		HashSet<Student> hs = new HashSet<Student>();
		
		//创建元素对象
		Student s1 = new Student("林青霞",30);
		Student s2 = new Student("张曼玉",35);
		Student s3 = new Student("王祖贤",33);
		Student s4 = new Student("林青霞",30);
		Student s5 = new Student("张曼玉",35);
		
		//把元素添加到集合
		hs.add(s1);
		hs.add(s2);
		hs.add(s3);
		hs.add(s4);
		hs.add(s5);
		
		//遍历集合
		//增强for
		for(Student s : hs) {
			System.out.println(s.getName()+"---"+s.getAge());
		}
	}
}

