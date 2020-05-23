package code.myScanner.src.com.itheima;
import java.util.Scanner;
/*
 * 为了提高程序的灵活性，我们把数据改进为键盘录入。
 * 如何实现键盘录入数据呢?目前使用JDK提供的类Scanner。
 * 如何使用Scanner来获取数据呢?目前大家记住使用步骤即可。
 * 
 * 使用步骤：
 * 		A:导包
 * 			import java.util.Scanner;
 * 			注意：在一个类中，有这样的顺序关系
 * 				package > import > class
 * 		B:创建键盘录入对象
 * 			Scanner sc = new Scanner(System.in);
 * 		C:获取数据
 * 			int i = sc.nextInt();
 */
public class ScannerDemo {
	public static void main(String[] args) {
		//创建键盘录入对象
		Scanner sc = new Scanner(System.in);
		
		//给出提示
		System.out.println("请输入一个整数：");
		//获取数据
		int i = sc.nextInt();
		
		//把获取的数据输出
		System.out.println("i:"+i);
	}
}
