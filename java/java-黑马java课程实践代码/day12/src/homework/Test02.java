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
		
		Set<String> set = hmStu.keySet();//��ȡ���м��ļ���
		for (String key : set) {//�������ļ��ϣ���ȡ��ÿһ����
			Student stu = hmStu.get(key);
			System.out.println("ѧ�ţ�"+key+"��������"+stu.getName()+",���䣺"+stu.getAge());
		}
		
		System.out.println("----------------------------------");
		
		//��ȡ  ��ֵ��  ����ļ���
		Set<Map.Entry<String, Student>> set2 = hmStu.entrySet();
		for (Map.Entry<String, Student> entry : set2) {
			//������ֵ�Զ���ļ��ϣ��õ�ÿһ�� ��ֵ��  ����
			String key = entry.getKey();
			Student stu = entry.getValue();
			System.out.println("ѧ�ţ�"+key+"��������"+stu.getName()+",���䣺"+stu.getAge());
		}
		
	}
}
