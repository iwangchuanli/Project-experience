package Demo;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class StringDemo {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "ÄãºÃ£¡";
		
		byte []by = str.getBytes();
		System.out.println(Arrays.toString(by));
		String s1 = new String(by);
		System.out.println(s1);
		
		byte []by1 = str.getBytes("GBK");
		System.out.println(Arrays.toString(by1));
		String s2 = new String(by1,"GBK");
		System.out.println(s2);
		
		byte []by2 = str.getBytes("UTF-8");
		System.out.println(Arrays.toString(by2));
		String s3 = new String(by2);
		System.out.println(s3);
	}
}
