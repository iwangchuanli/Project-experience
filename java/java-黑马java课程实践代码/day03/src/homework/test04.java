package homework;

import java.util.Scanner;

public class test04 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println("������һ�����֣�1~5����");
			int num = input.nextInt();
			switch (num) {
			case 1:
				System.out.println("�½�");
				break;
			case 2:
				System.out.println("���ļ�");
				break;
			case 3:
				System.out.println("����");
				break;
			case 4:
				System.out.println("ˢ��");
				break;
			case 5:
				System.out.println("�˳�");
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}
}
