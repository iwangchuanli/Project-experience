package homework;

import java.util.Scanner;

public class test01 {
public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	int num;
	System.out.println("������һ��������");
	num = input.nextInt();
	boolean flag;
	flag = (num%2==0)?true:false;
	if(flag == true)
		System.out.println(num+"�������ż����");
	else
		System.out.println(num+"�������������");
}
}
