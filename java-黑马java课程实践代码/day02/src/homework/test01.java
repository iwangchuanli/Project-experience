package homework;

import java.util.Scanner;

public class test01 {
public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	int num;
	System.out.println("请输入一个整数：");
	num = input.nextInt();
	boolean flag;
	flag = (num%2==0)?true:false;
	if(flag == true)
		System.out.println(num+"这个数是偶数！");
	else
		System.out.println(num+"这个数是奇数！");
}
}
