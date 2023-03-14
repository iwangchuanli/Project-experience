package homework;

import java.util.Scanner;

public class prj05 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入员工的工龄:");
		int workAge = input.nextInt();
		System.out.print("请输入员工的基本工资:");
		int money = input.nextInt();
		System.out.print("您目前工作了"+workAge+"年，基本工资为"+money+"元,");
		int sumMoney;
		if (workAge>=0&&workAge<1) {
			sumMoney = money+200;
			System.out.println("应涨工资 200元,涨后工资"+(money+200)+"元");
		}else if (workAge>=1&&workAge<3) {
			System.out.println("应涨工资500元,涨后工资"+(money+500)+"元");
		}else if (workAge>=3&&workAge<5) {
			System.out.println("应涨工资 1000元,涨后工资"+(money+1000)+"元");
		}else if (workAge>=5&&workAge<10) {
			System.out.println("应涨工资 2500元,涨后工资"+(money+2500)+"元");
		}else if (workAge>=10&&workAge<=15) {
			System.out.println("应涨工资 5000元,涨后工资"+(money+5000)+"元");
		}
	}
}
