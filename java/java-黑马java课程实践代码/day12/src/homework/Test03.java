package homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test03 {
	/**
	 * 
	 * HashSet
	 * */
	public static void main(String[] args) {
		HashMap<Student, String> hmStuVK = new HashMap<>();
		
		Student stu1 = new Student("java", 1);
		Student stu2 = new Student("C#", 2);
		Student stu3 = new Student("Phython", 3);
		Student stu4 = new Student("Android", 4);
		Student stu5 = new Student("Ios", 5);
		Student stu6 = new Student("java", 1);
		
		hmStuVK.put(stu1, "��");hmStuVK.put(stu2, "��");
		hmStuVK.put(stu3, "��");hmStuVK.put(stu4, "��");
		hmStuVK.put(stu5, "��");hmStuVK.put(stu6, "��");
		
		Set<Student> set1 = hmStuVK.keySet();
		for (Student stu : set1) {
			String value = hmStuVK.get(stu);
			System.out.println("������"+stu.getName()+"�����䣺"+stu.getAge()+",��ַ��"+value);
		}
		
		System.out.println("-------------------------------");
		
		Set<Map.Entry<Student, String>> set2 = hmStuVK.entrySet();
		for (Map.Entry<Student, String> entry : set2) {
			Student stu = entry.getKey();
			String value = entry.getValue();
			System.out.println("������"+stu.getName()+"�����䣺"+stu.getAge()+",��ַ��"+value);
		}
	}
}
