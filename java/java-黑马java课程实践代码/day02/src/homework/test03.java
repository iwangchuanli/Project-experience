package homework;

import java.util.Scanner;

public class test03 {
public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	int num;
	System.out.println("������һ������(<=999)��");
	num = input.nextInt();
	System.out.println(""+num);
	int a = num%10;
	int b = (num/10)%10;
	int c = num/100;
	System.out.println("��λ����"+a);
	System.out.println("ʮλ����"+b);
	System.out.println("��λ����"+c);
}
}
