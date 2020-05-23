package homework;

import java.util.Scanner;

public class prj08 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int a = input.nextInt();
		int b = input.nextInt();
		int c = input.nextInt();
		int temp;
		
		if (a > b) {
			temp = a;
			a = b;
			b = temp;
		}
		if( b > c){
			temp = b;
			b = c;
			c = temp;
		}
		if( a > b){
			temp = a;
			a = b;
			b = temp;
		}
		System.out.print(""+a+b+c);
		
	}
}
