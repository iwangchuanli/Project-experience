package homework;

import java.util.Scanner;

public class prj04 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("������4λ��Ա���ţ�");
		int id = input.nextInt();
		int a = id%10;
		int b = (id/10)%10;
		int c = (id/100)%10;
		int d = id/1000;
		System.out.println("��Ա����"+id+"��λ֮�ͣ�"+(a+b+c+d));
		boolean flag = ((a+b+c+d)>20)?true:false;
		if (flag == true) {
			System.out.println("��Ա����"+id+"�����˿ͻ�");
		}
		else
			System.out.println("��Ա����"+id+"�������˿ͻ�");
	}
}
