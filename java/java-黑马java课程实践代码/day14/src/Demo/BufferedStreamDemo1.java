package Demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedStreamDemo1 {
	public static void main(String[] args) throws IOException {
//		//�����ַ��������������
//		BufferedWriter bw = new BufferedWriter(new FileWriter("bw.txt"));
//		
//		//д����
//		for(int x=0; x<3; x++) {
//			bw.write("hello");
////			bw.write("\r\n");
//			bw.newLine();
//			bw.flush();
//		}
//		
//		//�ͷ���Դ
//		bw.close();
		
		//�����ַ���������������
		BufferedReader br = new BufferedReader(new FileReader("bw.txt"));
		
//		//��ȡһ��
//		String line = br.readLine();
//		System.out.println(line);
//		//�ڶ�ȡһ��
//		line = br.readLine();
//		System.out.println(line);
//		line = br.readLine();
//		System.out.println(line);
//		//���ȡ����
//		line = br.readLine();
//		System.out.println(line);
//		line = br.readLine();
//		System.out.println(line);
		
		//���հ����
		String line;
		while((line=br.readLine())!=null) {
			System.out.println(line);
		}
		
		//�ͷ���Դ
		br.close();
	}
}

