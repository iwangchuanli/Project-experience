package homework;

import java.util.Scanner;

public class prj01 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("������С�������е�ֽ�ƣ�");
		int left = input.nextInt();
		System.out.print("������С�������е�ֽ�ƣ�");
		int right = input.nextInt();
		
		System.out.print("����ǰС�����е�ֽ�ƣ�"+'\n'+
				"�����е�ֽ�ƣ�"+left+'\n'+"�����е�ֽ�ƣ�"+right+'\n');
		
		int temp = left;
		left = right;right = temp;
		System.out.print("������С�����е�ֽ�ƣ�"+'\n'+
				"�����е�ֽ�ƣ�"+left+'\n'+"�����е�ֽ�ƣ�"+right+'\n');
	}
}
