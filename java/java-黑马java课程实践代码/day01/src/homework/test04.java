package homework;
/**1. ����һ����,����Test04
2. �����ж�main����
3. ��main������,ʹ���������������ͼ��:
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
