package homework;

import java.util.Random;
import java.util.Scanner;

public class test06 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random ran = new Random();
		int num = ran.nextInt(100)+1;
		while (true) {
			System.out.println("请输入你的数据（0退出）：");
			int mine = input.nextInt();
			if (mine > num && mine != 0) {
				System.out.println("你猜的数据大了");
			}
			if (mine < num && mine != 0) {
				System.out.println("你猜的数据小了");
			}
			if (mine == num && mine != 0) {
				System.out.println("恭喜你，猜中了");
			}
			if (mine == 0) {
				System.exit(0);
			}
		}
	}
}
