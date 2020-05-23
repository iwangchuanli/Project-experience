package homework;

import java.util.Random;
import java.util.Scanner;

public class test04 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random ran = new Random();
		int nums [] = new int[5];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = ran.nextInt(10);
			if (nums[i] > 5 && nums[i] % 2 ==0) {
				System.out.println("nums["+i+"]="+nums[i]);
			}
		}
	}
}
