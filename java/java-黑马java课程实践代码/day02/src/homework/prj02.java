package homework;

import java.util.Scanner;

public class prj02 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("�����뱾��");
		double money = input.nextDouble();
		System.out.println("����Ϊ��"+money);
		
		System.out.println("��ȡһ���ı�Ϣ�ǣ�"+(money+(money*0.0225)));
		System.out.println("��ȡ�����ı�Ϣ�ǣ�"+(money+(money*0.027)));
		System.out.println("��ȡ�����ı�Ϣ�ǣ�"+(money+(money*0.0324)));
		System.out.println("��ȡ�����ı�Ϣ�ǣ�"+(money+(money*0.036)));
	}
}
