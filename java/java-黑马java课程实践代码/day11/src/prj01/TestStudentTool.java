package prj01;

import java.util.ArrayList;
import java.util.Collection;

public class TestStudentTool {

	public static void main(String[] args) {
		
		Collection<Student> collStu = new ArrayList<>();
		Student stu1 = new Student("java", 1, 90);
		Student stu2 = new Student("phython", 2, 80);
		Student stu3 = new Student("C#", 3, 70);
		Student stu4 = new Student("Android", 4, 60);
		Student stu5 = new Student("IOS", 5, 50);
		
		collStu.add(stu1);
		collStu.add(stu2);
		collStu.add(stu3);
		collStu.add(stu4);
		collStu.add(stu5);
		
		StudentTool stuTools = new StudentTool();
		Student[] stu = new Student[5];
		stu[0] = new Student(" java ", 1, 90);
		stu[1] = new Student("phython", 2, 80);
		stu[2] = new Student("  C#  ", 3, 70);
		stu[3] = new Student("Android", 4, 60);
		stu[4] = new Student("  IOS ", 5, 50);
		stuTools.listStudents(stu);
		//stuTools.listStudents(collStu);
		System.out.println("-----------------------");
		System.out.println("班级最高成绩为："+stuTools.getMaxScore(stu));
		System.out.println("-----------------------");
		System.out.println("成绩最高的学员信息为："+'\n'+
				"姓名:"+stuTools.getMaxStudent(stu).getName()+'\t'+
				"年龄:"+stuTools.getMaxStudent(stu).getAge()+'\t'+
				"成绩:"+stuTools.getMaxStudent(stu).getScore());
		System.out.println("-----------------------");
		System.out.println("学生成绩的平均值为："+stuTools.getAverageScore(stu));
		System.out.println("-----------------------");
		System.out.println("不及格学员的数量为："+stuTools.getCount(stu));
		
	}
}
