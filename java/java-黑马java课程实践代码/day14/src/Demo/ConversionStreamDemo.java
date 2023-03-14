package Demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * ת�����еı���ͽ�������
2.4.1 ת����ָ����?
	ת������ʵ����һ���ַ�����
ת���� = �ֽ��� + �����
2.4.2 ת�����Ĺ��췽��
OutputStreamWriter �ַ������
  public OutputStreamWriter(OutputStream out)
  		����Ĭ�ϱ�����ֽ���������ת��Ϊ�ַ���
  public OutputStreamWriter(OutputStream out,String charsetName)
  		����ָ��������ֽ�������ת��Ϊ�ַ���
  
InputStreamReader �ַ�������
  public InputStreamReader(InputStream in)
  		��Ĭ�ϵı��������
  public InputStreamReader(InputStream in,String charsetName)
*/
public class ConversionStreamDemo {
	public static void main(String[] args) throws IOException {
		//public OutputStreamWriter(OutputStream out):Ĭ�ϱ���GBK
		//OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("osw.txt"));
		//public OutputStreamWriter(OutputStream out,String charsetName)
		//OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("osw.txt"),"GBK");
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("osw.txt"),"UTF-8");
		//����д���ݵķ���
		osw.write("���");
		//�ͷ���Դ
		osw.close();
		System.out.println("------------------------");
		
		//public InputStreamReader(InputStream in):Ĭ�ϱ���GBK
		//InputStreamReader isr = new InputStreamReader(new FileInputStream("osw.txt"));
		//public InputStreamReader(InputStream in,String charsetName)
		//InputStreamReader isr = new InputStreamReader(new FileInputStream("osw.txt"),"GBK");
		InputStreamReader isr = new InputStreamReader(new FileInputStream("osw.txt"),"UTF-8");
		//�����ݣ�һ�ζ�ȡһ���ַ�����
		int ch;
		while((ch=isr.read())!=-1) {
			System.out.print((char)ch);
		}
		//�ͷ���Դ
		isr.close();
		
	}
}
