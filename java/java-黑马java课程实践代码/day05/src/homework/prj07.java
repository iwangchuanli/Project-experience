package homework;

import java.util.Scanner;

public class prj07 {

	public static void shuzu(int []a) {
		int temp = a[0];
		int count = 0;
		//int [] 
		for (int i = 1; i < a.length; i++) {
			if (a[i] == temp) {
				
			}
//			temp = a[i];
//			for (int j = 0; j < a.length; j++) {
//				if (temp == a[j]) {
//					count ++;
//				}
//			}
		}
	}	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int [] arr = new int [10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = input.nextInt();
		}
		shuzu(arr);
	}
}
