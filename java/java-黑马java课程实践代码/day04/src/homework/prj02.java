package homework;

import java.util.Random;
import java.util.Scanner;

public class prj02 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Random ran = new Random();
		System.out.print("������༶������");
		int num = input.nextInt();
		
		int score [] = new int [num];
		int count = 0,sum = 0,avg =0;
		//System.out.println("����������༶ͬѧ�ķ�����");
		for (int i = 0; i < score.length; i++) {
			score [i] = ran.nextInt(101);
			sum += score[i];
			if (score[i] < 60) {
				count ++;
			}
		}
		avg = sum/num;
		System.out.println("�༶��������"+num+'\n'+"�������������"+count+'\n'+"�༶ƽ���֣�"+avg);
		
	}
}
