package Homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Prj01 {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		
		BufferedWriter bufW = new BufferedWriter(new FileWriter("E:/�����Ͽβ���/day14/resource/data.txt"));
		BufferedReader bufR = new BufferedReader(new FileReader("E:/�����Ͽβ���/day14/resource/data.txt"));
		
		for (int i = 0; i < 3; i++) {
			System.out.print("����������֤�����ݣ�");
			String yzmD = input.nextLine();
			bufW.write(yzmD);
			bufW.newLine();
			bufW.flush();
		}
		bufW.close();
		//��֤
		System.out.print("��������Ҫ��֤����֤�룺");
		String yzmY = input.nextLine();
		HashSet<String> hsSet = new HashSet<>();
		String yzmline;
		while ((yzmline = bufR.readLine()) != null) {
			hsSet.add(yzmline);
		}
		if (hsSet.contains(yzmY)) {
			System.out.println("��֤ͨ��������");
		}else {
			System.out.println("��֤��ͨ��������");
		}
		//System.out.println(hsSet);
		bufR.close();
				
	}
}
