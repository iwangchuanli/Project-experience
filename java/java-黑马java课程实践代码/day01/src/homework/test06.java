package homework;
/**1. 定义一个类,类名Test06
2. 在类中定main方法
3. 在main方法中,使用输出语句输出如下图形:

*
**
***
****
*/
public class test06 {
public static void main(String[] args) {
	for (int i = 0; i <= 4; i++) {
		System.out.print('\n');
		for (int j = 1; j <= i; j++) {
			System.out.print("*");
		}
	}
}
}
