package homework;

import java.util.HashSet;
import java.util.Scanner;

public class prj02 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("������һ���ַ�����");
		String str = input.next();
		HashSet<String> oldstr = new HashSet<>();
		//newstr.add(str.substring(0));
		for (int i = 1; i < str.length()+1; i++) {
			oldstr.add(str.substring((i-1), i));
		}
		System.out.println("old:"+oldstr);
		//set����Ԫ��Ψһ
//		HashSet<String> newstr = new HashSet<>();
//		for (String oldStr : oldstr) {
//			if (newstr.contains(oldStr)) {
//				continue;
//			}else{
//				newstr.add(oldStr);
//			}
//		}
//		System.out.println("new:"+newstr);
	}
}
