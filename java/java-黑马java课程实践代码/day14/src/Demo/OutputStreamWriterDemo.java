package Demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputStreamWriterDemo {
	public static void main(String[] args) throws IOException {
		//�����ַ����������
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("b.txt"));
		
		//public void write(int c):дһ���ַ�
//		osw.write(97);
//		osw.write('a');
		//д�����ݺ�û�з������ݣ�Ϊʲô��?
		//1�ַ�=2�ֽ�
		//�ļ��е����ݴ洢�Ļ�����λ���ֽ�
		
		//public void write(char[] cbuf):дһ���ַ�����
//		char[] chs = {'a','b','c','d','e'};
//		osw.write(chs);
		
		//public void write(char[] cbuf,int off,int len):дһ���ַ������һ����
//		char[] chs = {'a','b','c','d','e'};
//		osw.write(chs, 1, 3);
		
		//public void write(String str):дһ���ַ���
//		osw.write("hello");
		
		//public void write(String str,int off,int len):дһ���ַ�����һ����
		osw.write("hello", 0, 3);
		
//		//void flush():ˢ�¸����Ļ���
//		osw.flush();
//		
//		//�ͷ���Դ
		osw.close(); //�رմ�������Ҫ��ˢ����
	}
}

