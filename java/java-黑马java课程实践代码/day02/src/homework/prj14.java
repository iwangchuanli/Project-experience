package homework;

import java.util.Scanner;

public class prj14 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("该同学的获奖名次为：");
		int rank = input.nextInt();
		switch (rank) {
		case 1:
			System.out.print("获得第1名，将参加清华大学组织的1个月夏令营");
			break;
		case 2:
			System.out.print("获得第2名，将奖励联想笔记本电脑一部");
			break;
		case 3:
			System.out.print("获得第3名，将奖励移动硬盘一个");
			break;

		default:
			System.out.print("没有任何奖励");
			break;
		}
	}
}
