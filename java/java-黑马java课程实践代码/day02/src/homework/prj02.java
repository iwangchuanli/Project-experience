package homework;

import java.util.Scanner;

public class prj02 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入本金：");
		double money = input.nextDouble();
		System.out.println("本金为："+money);
		
		System.out.println("存取一年后的本息是："+(money+(money*0.0225)));
		System.out.println("存取两年后的本息是："+(money+(money*0.027)));
		System.out.println("存取三年后的本息是："+(money+(money*0.0324)));
		System.out.println("存取五年后的本息是："+(money+(money*0.036)));
	}
}
