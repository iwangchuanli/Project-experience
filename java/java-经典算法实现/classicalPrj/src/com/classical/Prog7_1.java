package com.classical;
/***
 * 题目：输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数。
程序分析：利用while语句,条件为输入的字符不为'\n'.
 */
import java.util.Scanner;
public class Prog7_1{
	public static void main(String[] args){
		System.out.print("请输入一串字符：");
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();//将一行字符转化为字符串
		scan.close();//关闭输入流
		
		count(str);//调用
	}
	//统计输入的字符数
	private static void count(String str){
		String E1 = "[\u4e00-\u9fa5]";//汉字
		String E2 = "[a-zA-Z]";//英文大小写
		String E3 = "[0-9]";//数字
		String E4 = "\\s";//空格
		int countChinese = 0;//汉字统计
		int countLetter = 0;//英文统计
		int countNumber = 0;//数字统计
		int countSpace = 0;//空格统计
		int countOther = 0;//其他字符统计
		
		char[] array_Char = str.toCharArray();//将字符串转化为字符数组 实现一个可用作 Writer 的字符缓冲区。缓冲区会随向流中写入数据而自动增长
												//（toCharArray() 和 toString() 获取数据。）
		String[] array_String = new String[array_Char.length];//汉字只能作为字符串处理
		for(int i=0;i<array_Char.length;i++)
		  array_String[i] = String.valueOf(array_Char[i]);
		//遍历字符串数组中的元素
		for(String s:array_String){//s接收array_String数组中的字符进行匹配
			if(s.matches(E1))//matches方法尝试将整个输入序列与模式进行匹配
			  countChinese++;
			else if(s.matches(E2))
			  countLetter++;
			else if(s.matches(E3))
			  countNumber++;
			else if(s.matches(E4))
			  countSpace++;
			else
			  countOther++;
		}
		System.out.println("输入的汉字个数："+countChinese);
		System.out.println("输入的字母个数："+countLetter);
		System.out.println("输入的数字个数："+countNumber);
		System.out.println("输入的空格个数："+countSpace);
		System.out.println("输入的其它字符个数："+countSpace);
	}
}

