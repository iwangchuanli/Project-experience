package homework;

import java.util.Scanner;

public class test04 {

	public static char fw(int a){
		if (a >= 90 && a <= 100) {
			return 'A';
		}else if (a >= 80 && a < 90) {
			return 'B';
		}else if (a >= 70 && a < 80) {
			return 'C';
		}else if (a >= 60 && a < 70) {
			return 'D';
		}else if (a >= 0 && a < 60) {
			return 'E';
		}else {
			return 'F';
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.print('\n'+"请输入一个整数：");
			int num = input.nextInt();
			char str = fw(num);
			System.out.print("返回的值是："+str);
		}
		
	}
}
