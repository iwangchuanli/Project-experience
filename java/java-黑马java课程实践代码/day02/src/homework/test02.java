package homework;

import java.util.Scanner;

public class test02 {
public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	int num;
	System.out.println("������һ���ɼ���");
	num = input.nextInt();
	boolean flag;
	flag = (num >= 60)?true:false;
	if(flag == true)
		System.out.println(num+"����ɼ������ˣ�");
	else
		System.out.println(num+"����ɼ�������");
}
}
