package homework;

import java.util.Scanner;

public class test07 {
public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	System.out.print("������һ���ɼ���");
	int score = input.nextInt();
	System.out.print("����ĳɼ�Ϊ��"+score+'\n'+"�ɼ��ȼ�Ϊ��");
	if (score>=90&&score<=100) {
		System.out.println("����");
	}else if (score>=80&&score<90) {
		System.out.println("��");
	}else if (score>=70&&score<80) {
		System.out.println("��");
	}else if (score>=60&&score<70) {
		System.out.println("����");
	}else if (score<60) {
		System.out.println("������");
	}
}
}
