package com.classical;

/**
 * 题目：请输入星期几的第一个字母来判断一下是星期几，如果第一个字母一样，则继续 判断第二个字母。
程序分析：用情况语句比较好，如果第一个字母一样，则判断用情况语句或if语句判断第二个字母。

 * @author Administrator
 *
 */
import java.io.*;
public class Prog26{
	public static void main(String[] args){
		String str = new String();
	  BufferedReader bufIn = new BufferedReader(new InputStreamReader(System.in));
	  System.out.print("请输入星期的英文单词前两至四个字母）：");
	  try{
		  str = bufIn.readLine();
	  }catch(IOException e){
		  e.printStackTrace();
	  }finally{
		  try{
			  bufIn.close();
		  }catch(IOException e){
			  e.printStackTrace();
		  }
	  }
	  week(str);
	}
	
	
	private static void week(String str){
		int n = -1;
		if(str.trim().equalsIgnoreCase("Mo") || str.trim().equalsIgnoreCase("Mon") || str.trim().equalsIgnoreCase("Mond"))
		  n = 1;
		if(str.trim().equalsIgnoreCase("Tu") || str.trim().equalsIgnoreCase("Tue") || str.trim().equalsIgnoreCase("Tues"))
		  n = 2; 
		if(str.trim().equalsIgnoreCase("We") || str.trim().equalsIgnoreCase("Wed") || str.trim().equalsIgnoreCase("Wedn"))
		  n = 3;
		if(str.trim().equalsIgnoreCase("Th") || str.trim().equalsIgnoreCase("Thu") || str.trim().equalsIgnoreCase("Thur"))
		  n = 4; 
		if(str.trim().equalsIgnoreCase("Fr") || str.trim().equalsIgnoreCase("Fri") || str.trim().equalsIgnoreCase("Frid"))
		  n = 5;
		if(str.trim().equalsIgnoreCase("Sa") || str.trim().equalsIgnoreCase("Sat") || str.trim().equalsIgnoreCase("Satu"))
		  n = 2; 
		if(str.trim().equalsIgnoreCase("Su") || str.trim().equalsIgnoreCase("Sun") || str.trim().equalsIgnoreCase("Sund"))
		  n = 0; 
		switch(n){
			case 1:
			  System.out.println("星期一");
			  break;
			case 2:
			  System.out.println("星期二");
			  break;
			case 3:
			  System.out.println("星期三");
			  break;
			case 4:
			  System.out.println("星期四");
			  break;
			case 5:
			  System.out.println("星期五");
			  break;
			case 6:
			  System.out.println("星期六");
			  break;
			case 0:
			  System.out.println("星期日");
			  break;
			default:
			  System.out.println("输入有误！");
			  break;
		}
	}
}
