package homework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test02 {
	/**
	 * 
	 * HashMap
	 * */
	public static void main(String[] args) {
		HashMap<String, Student> hmStu = new HashMap<>();
		
		Student stu1 = new Student("java", 1);
		Student stu2 = new Student("C#", 2);
		Student stu3 = new Student("Phython", 3);
		Student stu4 = new Student("Android", 4);
		Student stu5 = new Student("Ios", 5);
		Student stu6 = new Student("java", 1);
		hmStu.put("x001", stu1);hmStu.put("x002", stu2);
		hmStu.put("x003", stu3);hmStu.put("x004", stu4);
		hmStu.put("x005", stu5);hmStu.put("x006", stu6);
		
		Set<String> set = hmStu.keySet();//获取所有键的集合
		for (String key : set) {//遍历键的集合，获取到每一个键
			Student stu = hmStu.get(key);
			System.out.println("学号："+key+"，姓名："+stu.getName()+",年龄："+stu.getAge());
		}
		
		System.out.println("----------------------------------");
		
		//获取  键值对  对象的集合
		Set<Map.Entry<String, Student>> set2 = hmStu.entrySet();
		for (Map.Entry<String, Student> entry : set2) {
			//遍历键值对对象的集合，得到每一个 键值对  对象
			String key = entry.getKey();
			Student stu = entry.getValue();
			System.out.println("学号："+key+"，姓名："+stu.getName()+",年龄："+stu.getAge());
		}
		
	}
}
