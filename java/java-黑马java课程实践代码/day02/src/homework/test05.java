package homework;

import java.util.Scanner;

class test05 {
	public static void area(int l,int h){
		int area = l*h;
		System.out.println("�ó����ε�����ǣ�"+area);
	}
	public static void zhouchang(int l,int h){
		int zc =  (l+h)*2;
		System.out.println("�ó����ε��ܳ�Ϊ��"+zc);
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("�����볤���εĳ��ȣ�");
		int length = input.nextInt();
		System.out.println("�����볤���εĿ�ȣ�");
		int height = input.nextInt();
		System.out.println("�ó����εĳ��Ϳ�ֱ�Ϊ��"+length+","+height);
		area(length,height);
		zhouchang(length,height);
	}
}
