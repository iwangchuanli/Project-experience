package com.classical;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ��Ŀ��ȡһ������a���Ҷ˿�ʼ��4��7λ�� ��������������������ǣ� (1)��ʹa����4λ��
 * (2)����һ����4λȫΪ1,����ȫΪ0����������~(~0<<4) (3)��������߽���&���㡣
 * 
 * @author Administrator
 *
 */

//import java.util.Scanner;
//public class Prog32{
//	public static void main(String[] msg){
//		//����һ��������
//		Scanner scan = new Scanner(System.in);
//		long l = scan.nextLong();
//		scan.close();
//		//���½�ȡ�ַ�
//		String str = Long.toString(l);
//		char[] ch = str.toCharArray();
//		int n = ch.length;
//		if(n<7)
//		  System.out.println("�������С��7λ��");
//		else
//		  System.out.println("��ȡ��4~7λ���֣�"+ch[n-7]+ch[n-6]+ch[n-5]+ch[n-4]);
//		}	  
//}

public class Prog32 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		//Integer num = new Integer(str);
		int count = 0;
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i <= str.length(); i++) {
			list.add(str.split("")[i]);
			System.out.print(list.get(i)+" ");
		}
		System.out.print('\n'+"ȡ�Ҷ˿�ʼ��4��7λ��");
		for (int i = 0; i < list.size(); i++) {
			if (i > list.size()-8 && i < list.size()-3) {
				System.out.print(list.get(i)+" ");
			}
		}
	}
}
