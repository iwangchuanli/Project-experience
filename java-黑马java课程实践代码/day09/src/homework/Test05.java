package homework;

import java.util.Scanner;

/***
 * 5.如果想要判断一个字符串是否以另外某个字符串结尾应该用什么方法  
 *  例如 判断"abcqwe" 是否以 "abc" 结尾
 * @author Administrator
 *
 */
public class Test05 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入一个字符串：");
		String str = input.next();
		
		System.out.println("请输入判断结尾字符串：");
		String suffix = input.next();
		
		boolean flag = str.endsWith(suffix);
		
		if (flag == true) {
			System.out.println("这个字符串是以"+suffix+"结尾的。");
		}else
			System.out.println("这个字符串不是以"+suffix+"结尾的。");
	}
}
