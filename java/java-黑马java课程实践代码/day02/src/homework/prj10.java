package homework;

import java.util.Scanner;

public class prj10 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入银行存款(万)：");
		int saveMoney = input.nextInt();
		if (saveMoney > 500) {
			System.out.print("存款超过500万，买雪铁龙DS9");
		}else if (saveMoney > 100) {
			System.out.print("存款超过100万，则买雪铁龙C6");
		}else if (saveMoney > 50) {
			System.out.print("存款超过50万，则买雪铁龙C5");
		}else if (saveMoney > 10) {
			System.out.print("存款超过10万，则买雪铁龙爱丽舍");
		}else{
			System.out.print("买捷安特吧");
		}
	}
}
