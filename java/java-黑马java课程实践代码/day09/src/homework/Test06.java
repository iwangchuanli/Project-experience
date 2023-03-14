package homework;

import java.util.Scanner;

/**
 * 6.如果想要判断一个字符串是否包含某个字符串应该用什么方法  
 *  例如 判断"abcqwe" 是否包含  "cq" 
 * */

public class Test06 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入一个字符串：");
		String str = input.next();
		
		System.out.println("请输入判断字符串：");
		CharSequence regex = input.next();
		/*boolean 
		 * contains(CharSequence s) 
          	当且仅当此字符串包含指定的 char 值序列时，返回 true。
           */
		boolean flag = str.contains(regex);
		
		if (flag == true) {
			System.out.println(str+"包含"+regex);
		}else
			System.out.println(str+"不包含"+regex);
	}
}
