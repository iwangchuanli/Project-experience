package homework;

import java.util.Scanner;

class test05 {
	public static void area(int l,int h){
		int area = l*h;
		System.out.println("该长方形的面积是："+area);
	}
	public static void zhouchang(int l,int h){
		int zc =  (l+h)*2;
		System.out.println("该长方形的周长为："+zc);
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入长方形的长度：");
		int length = input.nextInt();
		System.out.println("请输入长方形的宽度：");
		int height = input.nextInt();
		System.out.println("该长方形的长和宽分别为："+length+","+height);
		area(length,height);
		zhouchang(length,height);
	}
}
