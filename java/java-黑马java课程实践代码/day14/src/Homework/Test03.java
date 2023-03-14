package Homework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Test03 {

	public static void main(String[] args) throws IOException {
		//����һ�����ϴ��Student����
		HashSet<Student> hsSet = new HashSet<>();
		Student stu1 = new Student("it001","������",35,"����");
		Student stu2 = new Student("it002","JAVA",36,"�Ϻ�");
		hsSet.add(stu1);hsSet.add(stu2);
		//stringbulider ��ƴװ��������
		StringBuilder strB = new StringBuilder();
		StringBuilder result = null;
		for (Student stu : hsSet) {
			result = strB.append(stu.getId()).append(",").
					append(stu.getName()).append(",").
					append(stu.getAge()).append(",").
					append(stu.getAddress());
		}
		System.out.println(result);
		//outputstreamwriter ��д���ļ�
		OutputStreamWriter outW = new OutputStreamWriter(new FileOutputStream("E:/�����Ͽβ���/day14/resource/student.txt"));
		String s = new String(result);
		outW.write(s);
		outW.flush();
		outW.close();
		//bufferedreader ����ȡ�ļ�
		BufferedReader bufR = new BufferedReader(new FileReader("E:/�����Ͽβ���/day14/resource/student.txt"));
		String line;
		Student stuTemp = new Student();
		while((line=bufR.readLine())!=null) {
			System.out.println(line);
			System.out.println(line.split(","));
			String [] ss = null;
			//split�и�
			for (int i = 0;i< line.split(",").length;i++) {
				ss[i] = line.split(",")[i];
				System.out.println(ss[i]+" ");
			}
		}
		
		
	}
}
