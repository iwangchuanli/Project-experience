package code.myIf.src.com.itheima;

import java.util.Scanner;

/*
 * 需求：键盘录入两个数据，获取这两个数据的较大值
 * 
 * 分析：
 * 		A:看到了键盘录入，我们就应该想到键盘录入的三个步骤
 * 			导包，创建键盘录入对象，获取数据
 * 		B:获取两个数据的较大值，用if语句的格式2就可以实现判断
 * 		C:把较大的结果输出即可
 * 
 * 导包：
 * 		A:手动导包
 * 		B:点击鼠标自动生成
 * 		C:快捷键
 * 			ctrl+shift+o
 */
public class IfTest {
	public static void main(String[] args) {
		//创建键盘录入对象
		Scanner sc = new Scanner(System.in);
		
		//给出提示
		System.out.println("请输入第一个整数：");
		int a = sc.nextInt();
		
		System.out.println("请输入第二个整数：");
		int b = sc.nextInt();
		
		//if格式2实现判断
		/*
		if(a > b) {
			System.out.println("较大的值是："+a);
		}else {
			System.out.println("较大的值是："+b);
		}
		*/
		
		//我们获取到较大的值未必是输出，可能还会进行其他的操作，所以我们要改进代码
		//定义一个变量用于保存较大的值
		int max;
		
		if(a > b) {
			max = a;
		}else {
			max = b;
		}
		
		//我可以做
		//max += 10;
		
		System.out.println("较大的值是："+max);
	}
}
