package homework;

import java.util.Scanner;

public class MainApp02 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("������ѧԱ��ţ�");
		String stuId = input.nextLine();
		System.out.print("������ѧԱ������");
		String name = input.nextLine();
		System.out.print("������ѧԱ�Ա�");
		String sex = input.nextLine();
		System.out.print("������ѧԱ��ߣ�");
		double height = input.nextDouble();
		System.out.print("������ѧԱ���䣺");
		int age = input.nextInt();
		
		Student stu1 = new Student(stuId, name, sex, height, age);
		stu1.printf();
	}
}
