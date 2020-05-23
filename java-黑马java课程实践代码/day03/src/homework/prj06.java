package homework;

import java.util.Scanner;

public class prj06 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入班级学员的人数:");
		int total = input.nextInt();
		int score;
		int count = 0;
		double rate;
		System.out.print("请输入班级学员的分数:");
		for (int i = 0; i < total; i++) {
			score = input.nextInt();
			if (score < 80) {
				continue;
			}
			else{
				count++;
			}
		}
		
//		switch (true) {
//		case  score < 80:
//			
//			break;
//
//		default:
//			break;
//		}
		
//		int i = 0;
//		while (i < total) {
//			
//			i++;
//		}
		rate = (double)count/total;
		System.out.println("本班级学员的人数为："+total+'\n'+"学员80分以上的人员有："+count+'\n'+"80分以上的比例为："+rate);
	}
}
