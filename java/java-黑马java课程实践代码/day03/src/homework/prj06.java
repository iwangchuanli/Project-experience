package homework;

import java.util.Scanner;

public class prj06 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("������༶ѧԱ������:");
		int total = input.nextInt();
		int score;
		int count = 0;
		double rate;
		System.out.print("������༶ѧԱ�ķ���:");
		for (int i = 0; i < total; i++) {
			score = input.nextInt();
			if (score < 80) {
				continue;
			}
			else{
				count++;
			}
		}
		
//		switch (true) {
//		case  score < 80:
//			
//			break;
//
//		default:
//			break;
//		}
		
//		int i = 0;
//		while (i < total) {
//			
//			i++;
//		}
		rate = (double)count/total;
		System.out.println("���༶ѧԱ������Ϊ��"+total+'\n'+"ѧԱ80�����ϵ���Ա�У�"+count+'\n'+"80�����ϵı���Ϊ��"+rate);
	}
}
