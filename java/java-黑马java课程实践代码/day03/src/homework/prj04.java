package homework;

import java.util.Scanner;

public class prj04 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int sum = 0,avg = 0;
		int score;
		System.out.print("��������λͬѧ��������");
		String name = input.nextLine();
		boolean flag = false;
		System.out.println("����������5���ɼ���");
		for (int i = 0; i < 5; i++) {
			score = input.nextInt();
			if(score < 0){
				flag = true;
				System.out.println("¼����󣡣���");
				break;
			}
			else
				sum += score;
		}
		if (flag == false) {
			System.out.println("��λ"+name+"ͬѧ���ܷ��ǣ�"+sum+'\n'+"ƽ�����ǣ�"+sum/5);
		}
	}
}
