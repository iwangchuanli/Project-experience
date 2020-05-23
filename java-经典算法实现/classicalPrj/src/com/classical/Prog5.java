package com.classical;
/***
 * 
 * @author Administrator
 *题目：利用条件运算符的嵌套来完成此题：
 *学习成绩>=90分的同学用A表示，60-89分之间的用B表示，60分以下的用C表示。
程序分析：(a>b)?a:b这是条件运算符的基本例子。 

 */
public class Prog5{
	public static void main(String[] args){
		int n = -1;
		try{
			System.out.println("请输入成绩");
			n = Integer.parseInt(args[0]);
		}catch(ArrayIndexOutOfBoundsException e){//数据溢出异常
			return;
		}
		grade(n);
	}
	
	//成绩等级计算
	private static void grade(int n){
		if(n>100 || n<0)
		  System.out.println("输入无效");//输入异常处理
		else{
		  String str = (n>=90)?"分，属于A等":((n>60)?"分，属于B等":"分，属于C等");//三元判断式
		  System.out.println(n+str);
		}
	}
}

