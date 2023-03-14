package homework;

import java.util.Scanner;

public class test03 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入一个三位数：");
		int num = input.nextInt();
		int sum = 0;
		for (int i = 100; i < num; i++) {
			int a = i%10;
			int b = (i/10)%10;
			int c = i/100;
			if (c != 3 && b != 5 && a != 7) {
				System.out.print(i+" ");
				sum += i;
			}
		}
		System.out.println(" "+'\n'+sum);
	}
}
