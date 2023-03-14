package homework;

import java.util.Random;

public class prj06 {

	public static void main(String[] args) {
		Random ran = new Random();
		int [] a =new int [10];
		for (int i = 0; i < a.length; i++) {
			a[i] =ran.nextInt(100)+1;
			System.out.print(a[i]+",");
		}
	}
}
