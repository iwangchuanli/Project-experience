package homework;

import java.util.Scanner;

public class prj06 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入第一个整数:");
		int num1 = input.nextInt();
		System.out.print("请输入第二个整数:");
		int num2 = input.nextInt();
		System.out.print("请输入您要进行的运算(0:表示加法运算,1:表示减法运算,2:表示乘法运算,3:表示除法运算):");
		int opt = input.nextInt();
		System.out.print("控制台输出:");
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
				System.out.println("0不能做除数！");
			break;
		default:
			break;
		}
		
	}
}
