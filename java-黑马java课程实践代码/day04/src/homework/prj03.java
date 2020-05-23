package homework;

import java.util.Scanner;

public class prj03 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int x [] =new int [10];
		int count1 = 0,count2 = 0,count3 = 0,count4 = 0;
		System.out.println("请依次输入10个数字：");
		for (int i = 0; i < x.length; i++) {
			x[i] = input.nextInt();
			if (x[i] == 1) {
				count1 ++;
			}else if (x[i] == 2) {
				count2 ++;
			}
			else if (x[i] == 3) {
				count3 ++;
			}
			else{
				count4 ++;
			}
		}
		System.out.println("数字1的个数："+count1);
		System.out.println("数字2的个数："+count2);
		System.out.println("数字3的个数："+count3);
		System.out.println("非法数字的个数："+count4);
	}
}
