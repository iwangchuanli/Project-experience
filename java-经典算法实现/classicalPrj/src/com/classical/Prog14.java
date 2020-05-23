package com.classical;
/***
 * 题目：输入某年某月某日，判断这一天是这一年的第几天？
程序分析：以3月5日为例，应该先把前两个月的加起来，然后再加上5天即本年的第几天，特殊情况，闰年且输入月份大于3时需考虑多加一天。
 */
import java.util.Scanner;
public class Prog14{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in).useDelimiter("\\D");//匹配非数字
		System.out.print("请输入当前日期（年-月-日）:");
		int year = scan.nextInt();
		int month = scan.nextInt();
		int date = scan.nextInt();
		scan.close();
		analysis(year,month,date);
		//System.out.println("今天是"+year+"年的第"++"天");
	}
	//判断天数
	private static void analysis(int year, int month, int date){
		int n = 0;
		int[] month_date = new int[] {0,31,28,31,30,31,30,31,31,30,31,30,31};
		if((year%400)==0 || ((year%4)==0)&&((year%100)!=0))//判断闰年还是平年
		  month_date[2] = 29;//二月份日期不同
		for(int i=0;i <= month-1;i++)
		  n += month_date[i];//整月的天数相加
		if (date <= month_date[month]) {
			n = n+date;//最后加上该月的天数
			System.out.println("今天是"+year+"年的第"+n+"天");
		}
		else
			System.out.println("输入日期异常");
	}
}

