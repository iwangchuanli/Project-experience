package homework;

import java.util.Scanner;

public class prj14 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("��ͬѧ�Ļ�����Ϊ��");
		int rank = input.nextInt();
		switch (rank) {
		case 1:
			System.out.print("��õ�1�������μ��廪��ѧ��֯��1��������Ӫ");
			break;
		case 2:
			System.out.print("��õ�2��������������ʼǱ�����һ��");
			break;
		case 3:
			System.out.print("��õ�3�����������ƶ�Ӳ��һ��");
			break;

		default:
			System.out.print("û���κν���");
			break;
		}
	}
}
