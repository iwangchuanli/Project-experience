package Demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyJavaTest {
	public static void main(String[] args) throws IOException {
		//��װ����Դ
		BufferedReader br = new BufferedReader(new FileReader("BufferedStreamDemo.java"));
		//��װĿ�ĵ�
		BufferedWriter bw = new BufferedWriter(new FileWriter("Copy.java"));
		
		//��д����
		String line;
		while((line=br.readLine())!=null) {
			bw.write(line);
			bw.newLine();
			bw.flush();
		}
		
		//�ͷ���Դ
		bw.close();
		br.close();
		
	}
}

