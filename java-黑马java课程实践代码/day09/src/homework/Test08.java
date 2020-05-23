package homework;

import java.util.Scanner;

/**8.如果想把一个字符串中的所有字母都变成小写应该用什么方法*/
public class Test08 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入一个字符串：");
		String str = input.next();
		/*String 
		 * toLowerCase() 
          	使用默认语言环境的规则将此 String 中的所有字符都转换为小写。  */
		str = str.toLowerCase();
		System.out.println("转化后："+str);
	}
}
