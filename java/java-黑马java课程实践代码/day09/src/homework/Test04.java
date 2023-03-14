package homework;

import java.util.Scanner;

/***
 * 4.如果想要判断一个字符串是否以另外某个字符串开头应该用什么方法 
 例如 判断"abcqwe" 是否以 "abc" 开头
 * @author Administrator
 *
 */
public class Test04 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入一个字符串：");
		String str = input.next();
		
		System.out.println("请输入判断开头字符串：");
		String prefix = input.next();
		/* boolean 
		 * startsWith(String prefix) 
          	测试此字符串是否以指定的前缀开始。 */
		boolean flag = str.startsWith(prefix);
		if (flag == true) {
			System.out.println("这个字符串是"+prefix+"以开头的。");
		}else
			System.out.println("这个字符串不是"+prefix+"以开头的。");
	}
}
