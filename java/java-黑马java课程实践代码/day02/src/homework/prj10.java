package homework;

import java.util.Scanner;

public class prj10 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("���������д��(��)��");
		int saveMoney = input.nextInt();
		if (saveMoney > 500) {
			System.out.print("����500����ѩ����DS9");
		}else if (saveMoney > 100) {
			System.out.print("����100������ѩ����C6");
		}else if (saveMoney > 50) {
			System.out.print("����50������ѩ����C5");
		}else if (saveMoney > 10) {
			System.out.print("����10������ѩ����������");
		}else{
			System.out.print("��ݰ��ذ�");
		}
	}
}
