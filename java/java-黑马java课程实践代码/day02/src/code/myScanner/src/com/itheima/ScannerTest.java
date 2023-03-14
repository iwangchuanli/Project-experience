package code.myScanner.src.com.itheima;

import java.util.Scanner;
/*
 * 需求：键盘录入两个数据，并对这两个数据求和，输出其结果
 */
public class ScannerTest {
	public static void main(String[] args) {
		//创建键盘录入对象
		Scanner sc = new Scanner(System.in);
		
		//给出提示
		System.out.println("请输入第一个整数：");
		int a = sc.nextInt();
		
		System.out.println("请输入第二个整数：");
		int b = sc.nextInt();
		
		//对数据求和
		int sum = a + b;
		
		//输出结果
		System.out.println("sum:"+sum);
	}
}
