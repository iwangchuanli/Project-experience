package com.day03;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ��������������ɴ���
	1.����¼��һ���ַ�����ȥ�������ظ��ַ�
    2.��ӡ����ͬ����Щ�ַ������뱣֤˳���������룺aaaabbbcccddd����ӡ���Ϊ��abcd��
 * @author Administrator
 *
 */
public class Test03 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("input:");
		String str = input.nextLine();
		
		ArrayList<String> ss = new ArrayList<>();
		for (int i = 0; i < str.length(); i++) {
			String temp = str.split("")[i];
			//System.out.print(temp+" ");
			if (ss.contains(temp)) {
				continue;
			}else {
				ss.add(temp);
			}
		}
		
		System.out.println(ss);
	}
}
