package homework;

import java.util.Random;
import java.util.Scanner;

public class prj05 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random ran = new Random();
		System.out.print("����������4λ��Ա�ţ�");
		int id = input.nextInt();
		int num = (id/100)%10;
		int lucky = ran.nextInt(10);
		if (num == lucky) {
			System.out.print("���������ǣ�"+lucky+'\n'+"��λ��Ա�İ�λ���ǣ�"+num+"����Ϊ���˻�Ա����");
		}
		else
			System.out.print("���������ǣ�"+lucky+'\n'+"��λ��Ա�İ�λ���ǣ�"+num+"���������˻�Ա������");
	}
}
