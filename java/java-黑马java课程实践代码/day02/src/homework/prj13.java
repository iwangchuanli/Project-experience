package homework;

import java.util.Scanner;

public class prj13 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("�������Ʊԭ�ۣ�");
		double money = input.nextInt();
		double yj = money;
		System.out.print("�����е��·�Ϊ(1~12)��");
		int month = input.nextInt();
		System.out.print("��ѡ�����Ļ�Ʊ���ͣ�0ͷ�Ȳ�/1���òգ���");
		int type = input.nextInt();
		double discount;
		
		if (month>=4&&month<=10) {
			if (type == 0) {
				money= money*0.9;
				discount = 0.9;
			}
			else
				money = money*0.8;
				discount = 0.8;
		}
		else{
			if (type == 0) {
				money= money*0.5;
				discount = 0.5;
			}
			else
				money = money*0.4;
			discount = 0.4;
		}
		if (type == 0) {
			System.out.println("��ѡ�����ͷ�Ȳ�");
		}else
			System.out.println("��ѡ����Ǿ��ò�");
		
		System.out.println("��Ʊԭ��Ϊ��"+yj+'\n'+"����е��·��ǣ�"+month+'\n'+"�ۿ���Ϊ��"+discount+'\n'+"ʵ�ʵĻ�Ʊ�۸�"+money);
	}
}
