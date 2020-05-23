package homework;

import java.util.Scanner;

public class prj07 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean isError = true;
		String pwd;
		int amount;
		System.out.println("请输入您的密码：");
		for (int i = 0; i < 3; i++) {
			pwd = input.next();
			
			if (pwd.equals("111111")) {
				while (true) {
					System.out.println("请输入取款金额：");
					amount = input.nextInt();
					if (amount > 0 && amount < 1000) {
						System.out.println("取走现金"+amount+"元");
						isError = false;
						break;
					}else{
						System.out.print("您输入的取款金额有误，请重新输入：");
					}
				}
				
			}else{
				if (i == 2) {
					break;
				}
				System.out.println("密码错误，请输入您的密码：");
				//pwd = input.nextLine();
				continue;
			}
			
		}
		if (isError == true) {
			System.out.print("密码错误，请取卡");
		}else{
			System.out.print("交易完成，请取卡");
		}
	}
}
