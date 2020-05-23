package homework;

import java.util.Scanner;

/***
 * 
3.如果想要把字符串中所有的字符a替换成字符b应该用什么方法  
例如: 原字符串 "absbdaa"   替换后 "bbsbdbb"
 * @author Administrator
 *
 */
public class Test03 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入一个字符串：");
		String str = input.next();
		System.out.println("请输入想要替换的内容：");
		CharSequence target = input.next();
		//String regex = input.next();
		System.out.println("替换为：");
		CharSequence replaceStr = input.next();
		
		str = str.replace(target,replaceStr);
		/*String 
		 * replace(CharSequence target, CharSequence replacement) 
          	使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串。 */
		System.out.println("替换后的字符串为："+str);
	}
}
