package homework;

import java.util.Scanner;

public class test06 {
public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	System.out.println("请分别输入a和b的值");
	int a = input.nextInt();
	int b = input.nextInt();
	System.out.println("a和b的值分别是："+a+","+b);
	boolean flag = (a>b)?true:false;
	int c = (a>b)?(a+b):(a*b);
	if (flag == true) {
		System.out.println("a大于b，而且a+b="+c);
	}
	else
		System.out.println("a小于b，而且a*b="+c);
	
}
}
