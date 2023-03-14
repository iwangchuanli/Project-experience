package homework;

import java.util.Scanner;

public class prj03 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入Java的成绩：");
		int java = input.nextInt();
		System.out.print("请输入SQL的成绩：");
		int sql = input.nextInt();
		System.out.print("请输入Web的成绩：");
		int web = input.nextInt();
		System.out.println("---------------------------");
		System.out.println("Java"+'\t'+"SQL"+'\t'+"Web"+'\t');
		System.out.println(java+""+'\t'+sql+'\t'+web+'\t');
		System.out.println("---------------------------");
		
		int jsc = (java > sql)?java-sql:sql-java;
		System.out.println("Java和SQL的成绩差："+jsc);
		
		System.out.println("三门课的平均分是："+((java+sql+web)/3));
		
	}
}
