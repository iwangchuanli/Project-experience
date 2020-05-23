package homework;

import java.util.Scanner;

public class prj13 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入机票原价：");
		double money = input.nextInt();
		double yj = money;
		System.out.print("您出行的月份为(1~12)：");
		int month = input.nextInt();
		System.out.print("请选择您的机票类型（0头等舱/1经济舱）：");
		int type = input.nextInt();
		double discount;
		
		if (month>=4&&month<=10) {
			if (type == 0) {
				money= money*0.9;
				discount = 0.9;
			}
			else
				money = money*0.8;
				discount = 0.8;
		}
		else{
			if (type == 0) {
				money= money*0.5;
				discount = 0.5;
			}
			else
				money = money*0.4;
			discount = 0.4;
		}
		if (type == 0) {
			System.out.println("您选择的是头等舱");
		}else
			System.out.println("您选择的是经济舱");
		
		System.out.println("机票原价为："+yj+'\n'+"你出行的月份是："+month+'\n'+"折扣率为："+discount+'\n'+"实际的机票价格："+money);
	}
}
