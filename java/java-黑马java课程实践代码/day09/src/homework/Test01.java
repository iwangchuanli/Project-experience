package homework;

import java.util.Scanner;

/***
 * 
 * @author Administrator
 *
1.如果想要获得某个字符串的一部分应该用什么方法   
 例如  字符串"abcde"  想要得到"bc"
 */
public class Test01 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String str;
		System.out.println("请输入一个字符串：");
		str = input.next();
		
		int beginIndex,endIndex;
		System.out.println("新字符串开始的位置：");
		beginIndex = input.nextInt();
		System.out.println("新字符串结束的位置：");
		endIndex = input.nextInt();
		
		int getStrCode = str.codePointCount(beginIndex, endIndex);
		String getNewStr = str.substring(beginIndex, endIndex);
		//substring(int beginIndex, int endIndex) 
        	//返回一个新字符串，它是此字符串的一个子字符串。String 类型的
		System.out.println(getStrCode);
		System.out.println(getNewStr);
	}
}
