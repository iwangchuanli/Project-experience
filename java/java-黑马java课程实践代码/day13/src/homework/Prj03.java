package homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Prj03 {

	public static void main(String[] args) throws IOException {
		File file = new File("E:/�����Ͽβ���/day13/resource/info3.txt");
		FileInputStream fileIn = new FileInputStream(file);
		
		Scanner input = new Scanner(System.in);
		System.out.print("��ͨ������¼��һ���ַ���");
		String str = input.nextLine();
		
		StringBuilder sb = new StringBuilder();
		
		//��ȡ�ַ� ��ӵ�StringBuilder
		int len;
		while ((len = fileIn.read()) != -1) {
			String s;
			sb.append((char)len);
		}
		System.out.println("��StringBuilder�д洢��ȡ���������ַ���"+sb);
		
		//�ж��Ƿ����
		if ((sb.indexOf(str)) != -1) {
			System.out.println("����¼����ַ����ڣ�");
			//���ڲ�ֳ��ַ����� ���б��� ƥ��
			char [] ch = new char[sb.length()];
			System.out.print("��StringBuilder�е��ַ���ֵ������У�");
			for (int i = 0; i < sb.length(); i++) {
				//System.out.println((char)sb.codePointAt(i));
				char chTemp = (char)sb.codePointAt(i);
				ch[i] = chTemp;
				System.out.print(ch[i]+" ");
			}
			//ƥ��ͳ�Ƴ��ֵĴ���
			int count = 0;
			for (int i = 0; i < ch.length; i++) {
				Character index = ch[i];
				if (index.toString().equals(str)) {
					//System.out.println("has");
					count ++;
				}
			}
			System.out.println('\n'+"�ַ�"+str+"���ֵĴ����У�"+count);
			
		}else{
			System.out.println("����¼����ַ������ڣ�");
		}
		
	}
}
