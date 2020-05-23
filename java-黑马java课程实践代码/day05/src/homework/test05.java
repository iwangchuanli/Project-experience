package homework;

import java.util.Random;
import java.util.Scanner;

public class test05 {

	public static int numCount(int []a,int b){
		int count =0 ;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == b) {
				count ++;
			}
		}
		return count;
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random ran = new Random();
		System.out.print("请输入数组的元素个数：");
		int key = input.nextInt();
		int []arr = new int [key];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = ran.nextInt(10);
			System.out.print(arr[i]+" ");
		}
		int sum = numCount(arr, 3);
		System.out.println('\n'+"数组中3的个数为："+sum);
	}
}
