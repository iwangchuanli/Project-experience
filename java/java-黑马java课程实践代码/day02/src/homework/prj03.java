package homework;

import java.util.Scanner;

public class prj03 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("������Java�ĳɼ���");
		int java = input.nextInt();
		System.out.print("������SQL�ĳɼ���");
		int sql = input.nextInt();
		System.out.print("������Web�ĳɼ���");
		int web = input.nextInt();
		System.out.println("---------------------------");
		System.out.println("Java"+'\t'+"SQL"+'\t'+"Web"+'\t');
		System.out.println(java+""+'\t'+sql+'\t'+web+'\t');
		System.out.println("---------------------------");
		
		int jsc = (java > sql)?java-sql:sql-java;
		System.out.println("Java��SQL�ĳɼ��"+jsc);
		
		System.out.println("���ſε�ƽ�����ǣ�"+((java+sql+web)/3));
		
	}
}
