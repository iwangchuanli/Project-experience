package com.classical;
/***
 * ��Ŀ������һ���ַ����ֱ�ͳ�Ƴ�����Ӣ����ĸ���ո����ֺ������ַ��ĸ�����
�������������while���,����Ϊ������ַ���Ϊ'\n'.
 */
import java.util.Scanner;
public class Prog7_1{
	public static void main(String[] args){
		System.out.print("������һ���ַ���");
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();//��һ���ַ�ת��Ϊ�ַ���
		scan.close();//�ر�������
		
		count(str);//����
	}
	//ͳ��������ַ���
	private static void count(String str){
		String E1 = "[\u4e00-\u9fa5]";//����
		String E2 = "[a-zA-Z]";//Ӣ�Ĵ�Сд
		String E3 = "[0-9]";//����
		String E4 = "\\s";//�ո�
		int countChinese = 0;//����ͳ��
		int countLetter = 0;//Ӣ��ͳ��
		int countNumber = 0;//����ͳ��
		int countSpace = 0;//�ո�ͳ��
		int countOther = 0;//�����ַ�ͳ��
		
		char[] array_Char = str.toCharArray();//���ַ���ת��Ϊ�ַ����� ʵ��һ�������� Writer ���ַ�������������������������д�����ݶ��Զ�����
												//��toCharArray() �� toString() ��ȡ���ݡ���
		String[] array_String = new String[array_Char.length];//����ֻ����Ϊ�ַ�������
		for(int i=0;i<array_Char.length;i++)
		  array_String[i] = String.valueOf(array_Char[i]);
		//�����ַ��������е�Ԫ��
		for(String s:array_String){//s����array_String�����е��ַ�����ƥ��
			if(s.matches(E1))//matches�������Խ���������������ģʽ����ƥ��
			  countChinese++;
			else if(s.matches(E2))
			  countLetter++;
			else if(s.matches(E3))
			  countNumber++;
			else if(s.matches(E4))
			  countSpace++;
			else
			  countOther++;
		}
		System.out.println("����ĺ��ָ�����"+countChinese);
		System.out.println("�������ĸ������"+countLetter);
		System.out.println("��������ָ�����"+countNumber);
		System.out.println("����Ŀո������"+countSpace);
		System.out.println("����������ַ�������"+countSpace);
	}
}

