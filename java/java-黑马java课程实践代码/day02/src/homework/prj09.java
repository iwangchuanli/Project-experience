package homework;

import java.util.Scanner;

public class prj09 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("������һ���·�:");
		int mouth = input.nextInt();
		if (mouth>=3&&mouth<=5) {
			System.out.print(mouth+"�·��Ǵ���");
		}else if (mouth>=6&&mouth<=8) {
			System.out.print(mouth+"�·����ļ�");
		}else if (mouth>=9&&mouth<=11) {
			System.out.print(mouth+"�·����＾");
		}else if (mouth>=1&&mouth<=2||mouth==12) {
			System.out.print(mouth+"�·��Ƕ���");
		}
	}
}
