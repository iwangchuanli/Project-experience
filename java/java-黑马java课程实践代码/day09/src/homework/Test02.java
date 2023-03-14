package homework;

import java.util.Scanner;

/***
 * 
 * @author Administrator
 *如果想要知道  'a'  在 字符串"sbwesawer"中出现的索引位置应该用什么方法
 */
public class Test02 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入一个字符串：");
		String str = input.next();
		
		System.out.println("请输入一个想要查找的字符：");
		String x = input.next();
		
		int a ;
		int fromIndex = 0 ;
		System.out.println("字符所在的位置有：");

		while (fromIndex < str.length()) {
			a = str.indexOf(x, fromIndex);
			System.out.print((a+fromIndex+1)+" ");
			fromIndex = a;
		}
		
		
	}

}
