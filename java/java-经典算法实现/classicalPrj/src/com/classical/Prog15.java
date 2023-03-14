package com.classical;
/***
 * 题目：输入三个整数x,y,z，请把这三个数由小到大输出。
程序分析：我们想办法把最小的数放到x上，先将x与y进行比较，如果x>y则将x与y的值进行交换，
然后再用x与z进行比较，如果x>z则将x与z的值进行交换，这样能使x最小。
 */
import java.util.Scanner;
public class Prog15{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in).useDelimiter("\\D");//匹配非数字
		System.out.print("请输入三个数：");
		int x = scan.nextInt();
		int y = scan.nextInt();
		int z = scan.nextInt();
		scan.close();
		System.out.println("排序结果："+sort(x,y,z));
	}
	//比较两个数的大小
	private static String sort(int x,int y,int z){
		String s = null;
		if(x>y){//两两比较交换顺序
			int t = x;
			x = y;
			y = t;
		}
		if(x>z){
			int t = x;
			x = z;
			z = t;
		}
		if(y>z){
			int t = z;
			z = y;
			y = t;
		}
		s = x+" "+y+" "+z;
		return s;
	}
}

