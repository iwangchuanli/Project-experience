package homework;

import java.util.Scanner;

public class test03 {
public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	int num;
	System.out.println("请输入一个整数(<=999)：");
	num = input.nextInt();
	System.out.println(""+num);
	int a = num%10;
	int b = (num/10)%10;
	int c = num/100;
	System.out.println("个位数："+a);
	System.out.println("十位数："+b);
	System.out.println("百位数："+c);
}
}
