package homework;

import java.util.Scanner;

public class prj06 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("�������һ������:");
		int num1 = input.nextInt();
		System.out.print("������ڶ�������:");
		int num2 = input.nextInt();
		System.out.print("��������Ҫ���е�����(0:��ʾ�ӷ�����,1:��ʾ��������,2:��ʾ�˷�����,3:��ʾ��������):");
		int opt = input.nextInt();
		System.out.print("����̨���:");
		switch (opt) {
		case 0:
			System.out.println(num1+"+"+num2+"="+(num1+num2));
			break;
		case 1:
			System.out.println(num1+"-"+num2+"="+(num1-num2));
			break;
		case 2:
			System.out.println(num1+"*"+num2+"="+(num1*num2));
			break;
		case 3:
			if (num2 != 0) {
				System.out.println(num1+"/"+num2+"="+(num1/num2));
			}
			else
				System.out.println("0������������");
			break;
		default:
			break;
		}
		
	}
}
