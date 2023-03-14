package code.mySwitch.src.com.itheima;

import java.util.Scanner;

/*
 * switch语句格式：
 * 		switch(表达式) {
 * 			case 值1:
 * 				语句体1;
 * 				break;
 * 			case 值2:
 * 				语句体2;
 * 				break;
 * 			case 值3:
 * 				语句体3;
 * 				break;
 * 			...
 * 			default:
 * 				语句体n+1;
 * 				break;
 * 		}
 * 
 * 格式解释：
 * 		表达式：byte,short,int,char
 * 			JDK5以后可以是枚举,JDK7以后可以是字符串
 * 		case后面的值：是用来和表达式的值进行匹配的
 * 		break：表示中断的意思
 * 		default：所有的值都和表达式不匹配，就执行default对应的内容
 * 
 * 执行流程：
 * 		A:计算表达式的值
 * 		B:拿着这个值依次和case后面的值进行比对，一旦有匹配的，就执行对应的语句，在执行的过程中，遇到break就结束。
 * 		C:如果所有的case都不匹配，就执行语句体n+1
 * 
 * 案例：
 * 		根据键盘录入的数据1-7，输出对应的星期一到星期日
 * 
 * 快捷键：对代码进行格式化
 * 		ctrl+shift+f
 */
public class SwitchDemo {
	public static void main(String[] args) {
		// 创建键盘录入数据
		Scanner sc = new Scanner(System.in);

		// 给出提示
		System.out.println("请输入一个整数(1-7)：");
		int weekDay = sc.nextInt();

		// 用switch语句实现判断
		switch (weekDay) {
		case 1:
			System.out.println("星期一");
			break;
		case 2:
			System.out.println("星期二");
			break;
		case 3:
			System.out.println("星期三");
			break;
		case 4:
			System.out.println("星期四");
			break;
		case 5:
			System.out.println("星期五");
			break;
		case 6:
			System.out.println("星期六");
			break;
		case 7:
			System.out.println("星期日");
			break;
		default:
			System.out.println("你输入的数据有误");
			break;
		}
	}
}
