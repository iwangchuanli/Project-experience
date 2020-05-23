package homework;

import java.util.Scanner;

public class test02 {
public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	int num;
	System.out.println("请输入一个成绩：");
	num = input.nextInt();
	boolean flag;
	flag = (num >= 60)?true:false;
	if(flag == true)
		System.out.println(num+"这个成绩及格了！");
	else
		System.out.println(num+"这个成绩不及格！");
}
}
