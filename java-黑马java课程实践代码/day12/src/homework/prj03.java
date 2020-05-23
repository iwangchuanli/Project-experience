package homework;

import java.util.HashSet;
import java.util.Random;

public class prj03 {

	
	public static void main(String[] args) {
		Random ran = new Random();
		HashSet<Integer> num = new HashSet<>();
		while (num.size() < 10) {
			num.add(ran.nextInt(20)+1);
		}
		System.out.println(num);
		
		HashSet<String> str = new HashSet<>();
		while (str.size() < 10) {
			String s = String.valueOf(ran.nextInt(120));
			str.add(s);
		}
		System.out.println(str);
		
		/*
		 * 2.	产生10个长度为10的不能重复的字符串(里面只能出现大写字母、小写字母、0-9的数字)，并遍历打印输出
		 */
		StringBuilder st = new StringBuilder();
		char ch = 0;
		HashSet<String> haSet = new HashSet<>();
		while (haSet.size() < 10) {
			while (st.length() < 10) {
			Integer temp = ran.nextInt(74)+48;
			if (temp > 48 && temp < 57 || temp > 65 && temp < 90 || temp > 97 && temp < 122) {
				ch = (char) temp.byteValue();
			}
			st = st.append(ch);
			}
			haSet.add(st.toString());
			st.delete(0, st.length());
		}
 		System.out.println(haSet);
		
		
		
		
		
		HashSet<Character> cha = new HashSet<>();
		while (cha.size() <10) {
			cha.add((char) (ran.nextInt(91)+33));
		}
		System.out.println(cha);
	}
}
