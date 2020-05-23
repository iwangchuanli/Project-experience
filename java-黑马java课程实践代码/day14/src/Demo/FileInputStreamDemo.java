package Demo;

import java.io.IOException;
import java.util.Arrays;

public class FileInputStreamDemo {
	public static void main(String[] args) throws IOException {
		//基本字节流一次读取一个字节
//		FileInputStream fis = new FileInputStream("a.txt");
//		
//		int by;
//		while((by=fis.read())!=-1) {
//			System.out.print((char)by);
//		}
//		
//		fis.close();
		
		//String s = "hello";
		//[104, 101, 108, 108, 111]
		String s = "你好";
		//[-60, -29, -70, -61]
		byte[] bys = s.getBytes();
		System.out.println(Arrays.toString(bys));
	}
}

