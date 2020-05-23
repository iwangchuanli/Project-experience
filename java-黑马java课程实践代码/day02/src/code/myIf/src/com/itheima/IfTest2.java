package code.myIf.src.com.itheima;

import java.util.Scanner;

/*
 * 键盘录入学生考试成绩，请根据成绩判断该学生属于哪个级别
 * 90-100	优秀
 * 80-90	好
 * 70-80	良
 * 60-70	及格
 * 60以下	不及格
 * 
 * 分析：
 * A:键盘录入学生考试成绩,想到键盘录入数据的步骤
 * B:通过简单的分析，我们知道了该使用if语句的格式3进行判断
 * 		根据判断直接输出对应的级别
 * 
 * 写程序的时候，做数据测试，应该测试这样的几种情况：
 * 		正确数据
 * 		边界数据
 * 		错误数据
 */
public class IfTest2 {
	public static void main(String[] args) {
		//创建键盘录入对象
		Scanner sc = new Scanner(System.in);
		
		//给个提示
		System.out.println("请输入学生的考试成绩：");
		int score = sc.nextInt();
		
		//if语句格式3实现
		/*
		if(score>=90 && score<=100) {
			System.out.println("优秀");
		}else if(score>=80 && score<90) {
			System.out.println("好");
		}else if(score>=70 && score<80) {
			System.out.println("良");
		}else if(score>=60 && score<70) {
			System.out.println("及格");
		}else {
			System.out.println("不及格");
		}
		*/
		
		//通过数据的测试，我们发现程序不够严谨，未加入非法数据的判断
		if(score>100 || score<0) {
			System.out.println("你输入的成绩有误");
		}else if(score>=90 && score<=100) {
			System.out.println("优秀");
		}else if(score>=80 && score<90) {
			System.out.println("好");
		}else if(score>=70 && score<80) {
			System.out.println("良");
		}else if(score>=60 && score<70) {
			System.out.println("及格");
		}else {
			System.out.println("不及格");
		}
	}
}
