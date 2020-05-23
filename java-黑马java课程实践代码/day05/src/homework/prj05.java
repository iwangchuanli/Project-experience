package homework;

import java.util.Scanner;

public class prj05 {

	public static int weishu(int num) {
		int count = 0;
		boolean flag = true;
		while (num > 0) {
			num = num/10;
			count ++;
//			if (num < 0) {
//				flag = false;
//			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		int sum = weishu(num);
		System.out.println(num+"ÊÇ"+sum+"Î»Êı×Ö");
	}
}
