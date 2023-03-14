package homework;

import java.util.Random;
import java.util.Scanner;

public class prj02 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random ran = new Random();
		System.out.print("请输入班级人数：");
		int num = input.nextInt();
		
		int score [] = new int [num];
		int count = 0,sum = 0,avg =0;
		//System.out.println("请依次输入班级同学的分数：");
		for (int i = 0; i < score.length; i++) {
			score [i] = ran.nextInt(101);
			sum += score[i];
			if (score[i] < 60) {
				count ++;
			}
		}
		avg = sum/num;
		System.out.println("班级总人数："+num+'\n'+"不及格的人数："+count+'\n'+"班级平均分："+avg);
		
	}
}
