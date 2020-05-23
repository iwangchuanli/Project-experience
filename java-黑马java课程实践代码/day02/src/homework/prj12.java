package homework;

import java.util.Scanner;

public class prj12 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int identity = input.nextInt();
		double money = input.nextDouble();
		
		if (identity == 0) {
			if (money > 100) {
				money = money*0.9;
			}
		}
		else {
			if (money < 200) {
				money = money*0.8;
			}
			else {
				money = money*0.75;
			}
		}
	}
}
