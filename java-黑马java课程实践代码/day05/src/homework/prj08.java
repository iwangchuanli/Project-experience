package homework;

import java.util.Random;

public class prj08 {

	public static void main(String[] args) {
		Random ran = new Random();
		int []a = new int[10];
		int []b = new int[10];
		for (int i = 0; i < a.length; i++) {
			a[i] = ran.nextInt(100);
			b[i] = ran.nextInt(100);
		}
		str(a, b);
	}
	public static void str(int []a,int []b) {
		StringBuffer str = new StringBuffer();
		int count = 0;
		for (int i = 0; i < 10; i++) {
			if (a[i] == b[i]) {
				continue;
			}else {
				str = str.append(a[i]);
				count ++;
				str = str.append(b[i]);
				count ++;
			}
		}
		System.out.println("这个字符串的长度为："+count+"个，这个字符串是："+str);
	}
}
