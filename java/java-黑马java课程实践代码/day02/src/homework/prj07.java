package homework;

import java.util.Scanner;

public class prj07 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("ÇëÊäÈë x µÄÖµ£º");
		int x = input.nextInt();
		int y = 0;
		if (x < 0) {
			y = -1;
		}else if (x == 0) {
			y = 0;
		}else if (x > 0) {
			y = 1;
		}else if (x == 2) {
			y = 1;
		}
		System.out.print("x = "+x+",y = "+y);
	}
}
