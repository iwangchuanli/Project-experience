package homework;

import java.util.Scanner;

public class prj05 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("������Ա���Ĺ���:");
		int workAge = input.nextInt();
		System.out.print("������Ա���Ļ�������:");
		int money = input.nextInt();
		System.out.print("��Ŀǰ������"+workAge+"�꣬��������Ϊ"+money+"Ԫ,");
		int sumMoney;
		if (workAge>=0&&workAge<1) {
			sumMoney = money+200;
			System.out.println("Ӧ�ǹ��� 200Ԫ,�Ǻ���"+(money+200)+"Ԫ");
		}else if (workAge>=1&&workAge<3) {
			System.out.println("Ӧ�ǹ���500Ԫ,�Ǻ���"+(money+500)+"Ԫ");
		}else if (workAge>=3&&workAge<5) {
			System.out.println("Ӧ�ǹ��� 1000Ԫ,�Ǻ���"+(money+1000)+"Ԫ");
		}else if (workAge>=5&&workAge<10) {
			System.out.println("Ӧ�ǹ��� 2500Ԫ,�Ǻ���"+(money+2500)+"Ԫ");
		}else if (workAge>=10&&workAge<=15) {
			System.out.println("Ӧ�ǹ��� 5000Ԫ,�Ǻ���"+(money+5000)+"Ԫ");
		}
	}
}
