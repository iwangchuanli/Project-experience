package homework;

import java.util.Scanner;

public class prj04 {

	public static void isYear(int fromY,int endY) {
		for (int i = fromY; i <= endY; i++) {
			if (i % 100 == 0 && i % 400 == 0) {
				System.out.println(i+"�����ꡣ");
			}else if (i % 4 ==0) {
				System.out.println(i+"�����ꡣ");
			}
		}
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("��������ʼ��ݣ�");
		int fromY = input.nextInt();
		System.out.println("�����������ݣ�");
		int endY = input.nextInt();
		isYear(fromY, endY);
	}
}
