package homework;

import java.util.Scanner;

public class test06 {

	public static void loca(int []a,int key){
		int count = 0;
		System.out.print("数组中的索引值：");
		for (int i = 0; i < a.length; i++) {
			if (a[i] == key) {
				count ++;
				System.out.print(i+" ");
			}
		}
		if (count == 0) {
			System.out.print("数组中没有这个数字");
		}
		System.out.println('\n'+"数字"+key+"共在数组中出现了："+count+"次。");
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入0~10之间的一个关键数字：");
		int k = input.nextInt();
		int []arr = {1,2,3,4,5,6,7,8,9};
		loca(arr, k);
	}
}
