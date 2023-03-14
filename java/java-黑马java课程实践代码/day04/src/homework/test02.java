package homework;

import java.util.Random;

public class test02 {

	public static void main(String[] args) {
		Random ran = new Random();
		int x[] = new int [3];
		for (int i = 0; i < x.length; i++) {
			x[i] = ran.nextInt(10);
			System.out.println("x["+i+"]="+x[i]);
		}
	}
}
