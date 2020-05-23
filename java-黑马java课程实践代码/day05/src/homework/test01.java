package homework;

import java.util.Scanner;

public class test01 {

	public static void print(int a){
		System.out.print("0~"+a+"之间的偶数:");
		for (int i = 1; i <= a; i++) {
			if (i % 2 ==0) {
				System.out.print(i+" ");
			}
		}
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入这个整数：");
		int num = input.nextInt();
		print(num);
	}
}
