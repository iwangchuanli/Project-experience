package homework;

import java.util.Scanner;

public class test07 {
public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	System.out.print("请输入一个成绩：");
	int score = input.nextInt();
	System.out.print("输入的成绩为："+score+'\n'+"成绩等级为：");
	if (score>=90&&score<=100) {
		System.out.println("优秀");
	}else if (score>=80&&score<90) {
		System.out.println("好");
	}else if (score>=70&&score<80) {
		System.out.println("良");
	}else if (score>=60&&score<70) {
		System.out.println("及格");
	}else if (score<60) {
		System.out.println("不及格");
	}
}
}
