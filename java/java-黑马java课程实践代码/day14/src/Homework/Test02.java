package Homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test02 {

	public static void main(String[] args) throws IOException {
		//����Դ��װ
		BufferedReader bufR = new BufferedReader(new FileReader("E:/�����Ͽβ���/day14/resource/test02.txt"));
		//Ŀ���ļ���װ
		BufferedWriter bufW = new BufferedWriter(new FileWriter("E:/�����Ͽβ���/day14/resource/test02copy.txt"));
		
		//���ݶ�д
		String line;
		while ((line = bufR.readLine()) != null) {
			bufW.write(line);
			bufW.newLine();
			bufW.flush();
		}
		//�ͷ���Դ
		bufR.close();
		bufW.close();
		
	}
}
