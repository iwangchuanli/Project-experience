package homework;

import java.util.Scanner;

public class test06 {
public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	System.out.println("��ֱ�����a��b��ֵ");
	int a = input.nextInt();
	int b = input.nextInt();
	System.out.println("a��b��ֵ�ֱ��ǣ�"+a+","+b);
	boolean flag = (a>b)?true:false;
	int c = (a>b)?(a+b):(a*b);
	if (flag == true) {
		System.out.println("a����b������a+b="+c);
	}
	else
		System.out.println("aС��b������a*b="+c);
	
}
}
