package homework;
/**1. 定义一个类,类名Test04
2. 在类中定main方法
3. 在main方法中,使用输出语句输出如下图形:
*/
public class test04 {

public static void main(String[] args) {
		
		for (int i = 5; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				System.out.print(" ");
			}
			System.out.print("*");
			for (int j = 0; j < 2*(5-i); j++) {
				System.out.print(" ");
			}
			System.out.println("*");
		}

		for (int i = 0; i < 6; i++) {
			
			for (int j = 0; j < i; j++) {
				System.out.print(" ");
			}
			System.out.print("*");
			for (int j = 2*5; j > 2*i; j--) {
				System.out.print(" ");
			}
			System.out.println("*");
		}
	}
}
