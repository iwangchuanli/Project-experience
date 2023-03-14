package Demo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyAviTest {
	public static void main(String[] args) throws IOException {
		//��¼��ʼʱ��
		long start = System.currentTimeMillis();
		
//		method1();
//		method2();
//		method3();
		method4();
		
		//��¼����ʱ��
		long end = System.currentTimeMillis();
		System.out.println("����ʱ��"+(end-start)+"����");
	}
	
	//�����ֽ���һ�ζ�дһ���ֽ�����
	private static void method4() throws IOException {
		//��װ����Դ
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("d:\\����ͼƬ.avi"));
		//��װĿ�ĵ�
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("copy.avi"));
		
		byte[] bys = new byte[1024];
		int len;
		while((len=bis.read(bys))!=-1) {
			bos.write(bys,0,len);
		}
		
		bos.close();
		bis.close();
	}
	
	//�����ֽ���һ�ζ�дһ���ֽ�
	private static void method3() throws IOException {
		//��װ����Դ
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("d:\\����ͼƬ.avi"));
		//��װĿ�ĵ�
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("copy.avi"));
		
		int by;
		while((by=bis.read())!=-1) {
			bos.write(by);
		}
		
		bos.close();
		bis.close();
	}
	
	//�����ֽ���һ�ζ�дһ���ֽ�����
	private static void method2() throws IOException {
		//��װ����Դ
		FileInputStream fis = new FileInputStream("d:\\����ͼƬ.avi");
		//��װĿ�ĵ�
		FileOutputStream fos = new FileOutputStream("copy.avi");
		
		byte[] bys = new byte[1024];
		int len;
		while((len=fis.read(bys))!=-1) {
			fos.write(bys,0,len);
		}
		
		fos.close();
		fis.close();
	}

	//�����ֽ���һ�ζ�дһ���ֽ�
	private static void method1() throws IOException {
		//��װ����Դ
		FileInputStream fis = new FileInputStream("d:\\����ͼƬ.avi");
		//��װĿ�ĵ�
		FileOutputStream fos = new FileOutputStream("copy.avi");
		
		int by;
		while((by=fis.read())!=-1) {
			fos.write(by);
		}
		
		fos.close();
		fis.close();
	}
}

