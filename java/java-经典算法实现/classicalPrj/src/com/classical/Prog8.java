package com.classical;
/***
 * 题目：求s=a+aa+aaa+aaaa+aa...a的值，其中a是一个数字。
 * 例如2+22+222+2222+22222(此时共有5个数相加)，几个数相加有键盘控制。 
程序分析：关键是计算出每一项的值。
 */
import java.util.Scanner;

public class Prog8{
	public static void main(String[] args){
		System.out.print("求s=a+aa+aaa+aaaa+...的值，请输入a的值：");
		Scanner scan = new Scanner(System.in).useDelimiter("\\s*");//以空格作为分隔符进行输入a,n
		int a = scan.nextInt();
		int n = scan.nextInt();
		scan.close();//关闭扫描器
		System.out.println(expressed(2,5)+add(2,5));
	} 
	
	//求和表达式
	private static String expressed(int a,int n){
		/*StringBuffer 线程安全的可变字符序列。一个类似于 String 的字符串缓冲区，但不能修改。
		 *虽然在任意时间点上它都包含某种特定的字符序列，但通过某些方法调用可以改变该序列的长度和内容。 
		 * */
		StringBuffer sb = new StringBuffer();//表达式
		StringBuffer subSB = new StringBuffer();//表达式的每一项
		/*主要操作是 append 和 insert 方法
		 * append 方法始终将这些字符添加到缓冲区的末端；而 insert 方法则在指定的点添加字符。
		 * */
		for(int i=1;i<n+1;i++){
		  subSB = subSB.append(a);//表达式的每一项
		  sb = sb.append(subSB);
		  if(i<n)//每一项结束后
		    sb = sb.append("+");
		}
		sb.append("=");
		return sb.toString();
	}
	//求和
	private static long add(int a,int n){
		long sum = 0;
		long subSUM = 0;
		for(int i=1;i<n+1;i++){
			subSUM = subSUM*10+a;//进一位*10，加上个位
			sum = sum+subSUM;//进行求和
		}
		return sum;
	}
}
