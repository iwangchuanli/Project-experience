package homework;

import java.util.Scanner;

public class prj04 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int sum = 0,avg = 0;
		int score;
		System.out.print("请输入这位同学的姓名：");
		String name = input.nextLine();
		boolean flag = false;
		System.out.println("请依次输入5个成绩：");
		for (int i = 0; i < 5; i++) {
			score = input.nextInt();
			if(score < 0){
				flag = true;
				System.out.println("录入错误！！！");
				break;
			}
			else
				sum += score;
		}
		if (flag == false) {
			System.out.println("这位"+name+"同学的总分是："+sum+'\n'+"平均分是："+sum/5);
		}
	}
}
