package homework;

import java.util.Scanner;

public class test07 {

	public static int[] cal(int m,int n){
		int[] arr = new int[4];
		arr[0]= m+n;
		arr[1]= m-n;
		arr[2]= m*n;
		arr[3]= m/n;
		return arr;
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("第一个整数：");
		int num1 = input.nextInt();
		System.out.print("第二个整数：");
		int num2 = input.nextInt();
		int a[] = cal(num1,num2);
		System.out.print("这两个数的四项运算结果是：");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
	}
}
