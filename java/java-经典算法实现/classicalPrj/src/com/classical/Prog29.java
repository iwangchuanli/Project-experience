package com.classical;

import java.util.Scanner;

/**
 * ��Ŀ����һ��3*3����Խ���Ԫ��֮�� �������������˫��forѭ�����������ά���飬�ٽ�a[i][i]�ۼӺ������
 * 
 * @author Administrator
 *
 */
public class Prog29 {

	public static void main(String[] args) {
		int a[][] = new int[3][3];
		input(a);
		cal(a);
	}

	public static void input(int a[][]) {
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				System.out.print("��" + (i + 1) + "�е�" + (j + 1) + "�е�Ԫ��Ϊ��");
				a[i][j] = input.nextInt();
			}
		}
	}

	public static void cal(int a[][]) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i][i];
		}
		System.out.println("sum=" + sum);
	}
}
