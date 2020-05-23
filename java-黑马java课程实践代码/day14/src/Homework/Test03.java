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
		//创建一个集合存放Student对象
		HashSet<Student> hsSet = new HashSet<>();
		Student stu1 = new Student("it001","张曼玉",35,"北京");
		Student stu2 = new Student("it002","JAVA",36,"上海");
		hsSet.add(stu1);hsSet.add(stu2);
		//stringbulider 来拼装对象数据
		StringBuilder strB = new StringBuilder();
		StringBuilder result = null;
		for (Student stu : hsSet) {
			result = strB.append(stu.getId()).append(",").
					append(stu.getName()).append(",").
					append(stu.getAge()).append(",").
					append(stu.getAddress());
		}
		System.out.println(result);
		//outputstreamwriter 来写入文件
		OutputStreamWriter outW = new OutputStreamWriter(new FileOutputStream("E:/黑马上课材料/day14/resource/student.txt"));
		String s = new String(result);
		outW.write(s);
		outW.flush();
		outW.close();
		//bufferedreader 来读取文件
		BufferedReader bufR = new BufferedReader(new FileReader("E:/黑马上课材料/day14/resource/student.txt"));
		String line;
		Student stuTemp = new Student();
		while((line=bufR.readLine())!=null) {
			System.out.println(line);
			System.out.println(line.split(","));
			String [] ss = null;
			//split切割
			for (int i = 0;i< line.split(",").length;i++) {
				ss[i] = line.split(",")[i];
				System.out.println(ss[i]+" ");
			}
		}
		
		
	}
}
