package homework;

import java.util.Scanner;

public class test05 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int []score =new int[6];
		int sum = 0,avg = 0;
		int max = 0;
		int min = 0;
		for (int i = 0; i < score.length; i++) {
			System.out.print("请输入第"+(i+1)+"位评委的分数");
			score[i] = input.nextInt();
			//System.out.print(score[i]);
		}
		for (int i = 0; i < score.length; i++) {
			if (score[i] > max) {
				max = score[i];
			}
			if (score[i] < min) {
				min = score[i];
			}
			sum += score[i];
		}
		avg = (sum-max-min)/(score.length-2);
		System.out.print("这位选手的总分为:"+sum+'\n'+"这位选手的最后得分为:"+avg);
	}
}
