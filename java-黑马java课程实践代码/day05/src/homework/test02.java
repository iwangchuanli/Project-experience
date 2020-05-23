package homework;

import java.util.Random;

public class test02 {

	public static void sum(int a,int b){
		int sum = a+b;
		System.out.print(a+"+"+b+"="+sum);
	}
	public static void main(String[] args) {
		Random ran = new Random();
		int num1 = ran.nextInt(100)+1;
		int num2 = ran.nextInt(100)+1;
		sum(num1,num2);
	}
}
