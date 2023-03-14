package Demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * ����ĿĿ¼�µ�a.txt���ݸ��Ƶ���ĿĿ¼�µ�b.txt��
 * 
 * ����Դ��
 * 		a.txt---������---�ַ���---InputStreamReader---FileReader---BufferedReader
 * Ŀ�ĵأ�
 * 		b.txt---д����---�ַ���---OutputStreamWriter---FileWriter---BufferedWriter
 */
public class CopyTxtTest {
	public static void main(String[] args) throws IOException {
		//��װ����Դ
		BufferedReader br = new BufferedReader(new FileReader("a.txt"));
		//��װĿ�ĵ�
		BufferedWriter bw = new BufferedWriter(new FileWriter("b.txt"));
		
		//��д����
		//һ�ζ�дһ���ַ�����
		char[] chs = new char[1024];
		int len;
		while((len=br.read(chs))!=-1) {
			bw.write(chs, 0, len);
		}
		
		//�ͷ���Դ
		bw.close();
		br.close();
	}
}

