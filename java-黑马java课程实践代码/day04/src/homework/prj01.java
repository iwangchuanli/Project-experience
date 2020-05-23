package homework;

import java.util.Random;

public class prj01 {

	public static void main(String[] args) {
		Random ran = new Random();
		int [] num = {1,2,3,4,5,6,7,8,9,10};
		int [] x = new int [3];
		int sum = 0;
		for (int i = 0; i < x.length; i++) {
			int a = ran.nextInt(10)+1;
			x[i] = num[a];
			sum += x[i];
			System.out.println("x["+i+"]="+x[i]);
		}
		System.out.println("sum="+sum);
	}
}
