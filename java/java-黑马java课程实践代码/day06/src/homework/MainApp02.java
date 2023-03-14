package homework;

import java.util.Scanner;

public class MainApp02 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入学员编号：");
		String stuId = input.nextLine();
		System.out.print("请输入学员姓名：");
		String name = input.nextLine();
		System.out.print("请输入学员性别：");
		String sex = input.nextLine();
		System.out.print("请输入学员身高：");
		double height = input.nextDouble();
		System.out.print("请输入学员年龄：");
		int age = input.nextInt();
		
		Student stu1 = new Student(stuId, name, sex, height, age);
		stu1.printf();
	}
}
