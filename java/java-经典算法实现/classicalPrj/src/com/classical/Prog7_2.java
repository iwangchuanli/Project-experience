package com.classical;
/***
 * ����һ���ַ���ͨ��ͳ��
 * ������ͬ���ַ��ĸ���
 */
import java.util.*;
public class Prog7_2{
	public static void main(String[] args){
	  System.out.println("������һ���ַ���");
	  Scanner scan = new Scanner(System.in);
	  String str = scan.nextLine();
	  scan.close();
	  count(str);
	}
	//ͳ��������ַ�
	private static void count(String str){
		List<String> list = new ArrayList<String>();
		char[] array_Char = str.toCharArray();
		for(char c:array_Char)
		  list.add(String.valueOf(c));//���ַ���Ϊ�ַ�����ӵ�list����
		Collections.sort(list);//����
								//Collections.sort����Ԫ�ص���Ȼ˳�� ��ָ���б������������
		for(String s:list){
			int begin = list.indexOf(s);
			int end = list.lastIndexOf(s);
			//��������ͳ���ַ���
			if(list.get(end)==s)
			  System.out.println("�ַ���"+s+"����"+(end-begin+1)+"��");
		}
	}
}

