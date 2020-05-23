package homework;

import java.util.Scanner;

/**7.如果想把一个字符串中的所有字母都变成大写应该用什么方法*/
public class Test07 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入一个字符串：");
		String str = input.next();
		/*String 
		 * toUpperCase() 
          	使用默认语言环境的规则将此 String 中的所有字符都转换为大写。 */
		str = str.toUpperCase();
		System.out.println("转化后："+str);
	}
}
