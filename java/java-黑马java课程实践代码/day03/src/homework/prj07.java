package homework;

import java.util.Scanner;

public class prj07 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean isError = true;
		String pwd;
		int amount;
		System.out.println("�������������룺");
		for (int i = 0; i < 3; i++) {
			pwd = input.next();
			
			if (pwd.equals("111111")) {
				while (true) {
					System.out.println("������ȡ���");
					amount = input.nextInt();
					if (amount > 0 && amount < 1000) {
						System.out.println("ȡ���ֽ�"+amount+"Ԫ");
						isError = false;
						break;
					}else{
						System.out.print("�������ȡ�����������������룺");
					}
				}
				
			}else{
				if (i == 2) {
					break;
				}
				System.out.println("��������������������룺");
				//pwd = input.nextLine();
				continue;
			}
			
		}
		if (isError == true) {
			System.out.print("���������ȡ��");
		}else{
			System.out.print("������ɣ���ȡ��");
		}
	}
}
