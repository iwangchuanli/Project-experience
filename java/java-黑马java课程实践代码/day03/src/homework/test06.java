package homework;

import java.util.Random;
import java.util.Scanner;

public class test06 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random ran = new Random();
		int num = ran.nextInt(100)+1;
		while (true) {
			System.out.println("������������ݣ�0�˳�����");
			int mine = input.nextInt();
			if (mine > num && mine != 0) {
				System.out.println("��µ����ݴ���");
			}
			if (mine < num && mine != 0) {
				System.out.println("��µ�����С��");
			}
			if (mine == num && mine != 0) {
				System.out.println("��ϲ�㣬������");
			}
			if (mine == 0) {
				System.exit(0);
			}
		}
	}
}
