package homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Prj03 {

	public static void main(String[] args) throws IOException {
		File file = new File("E:/黑马上课材料/day13/resource/info3.txt");
		FileInputStream fileIn = new FileInputStream(file);
		
		Scanner input = new Scanner(System.in);
		System.out.print("请通过键盘录入一个字符：");
		String str = input.nextLine();
		
		StringBuilder sb = new StringBuilder();
		
		//读取字符 添加到StringBuilder
		int len;
		while ((len = fileIn.read()) != -1) {
			String s;
			sb.append((char)len);
		}
		System.out.println("在StringBuilder中存储读取到的所有字符："+sb);
		
		//判断是否存在
		if ((sb.indexOf(str)) != -1) {
			System.out.println("键盘录入的字符存在！");
			//存在拆分成字符数组 进行遍历 匹配
			char [] ch = new char[sb.length()];
			System.out.print("将StringBuilder中的字符拆分到数组中：");
			for (int i = 0; i < sb.length(); i++) {
				//System.out.println((char)sb.codePointAt(i));
				char chTemp = (char)sb.codePointAt(i);
				ch[i] = chTemp;
				System.out.print(ch[i]+" ");
			}
			//匹配统计出现的次数
			int count = 0;
			for (int i = 0; i < ch.length; i++) {
				Character index = ch[i];
				if (index.toString().equals(str)) {
					//System.out.println("has");
					count ++;
				}
			}
			System.out.println('\n'+"字符"+str+"出现的次数有："+count);
			
		}else{
			System.out.println("键盘录入的字符不存在！");
		}
		
	}
}
