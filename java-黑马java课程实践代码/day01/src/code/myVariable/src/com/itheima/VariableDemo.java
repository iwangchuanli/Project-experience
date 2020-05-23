package code.myVariable.src.com.itheima;
/*
 * 变量的定义格式：
 * 		数据类型 变量名 = 初始化值;
 * 
 * 基本数据类型：
 * 		byte,short,int,long,float,double,char,boolean
 * 
 * 注意事项：
 * 		A:整数默认是int类型，定义long类型变量的时候，建议加L或l。
 * 		B:浮点数默认是double类型，定义float类型变量的时候，建议加F或f。
 */
public class VariableDemo {
	public static void main(String[] args) {
		//byte类型的变量
		byte b = 10;
		System.out.println(10);
		System.out.println(b);
		
		//short类型的变量
		short s = 100;
		System.out.println(s);
		
		//int类型的变量
		int i = 1000;
		System.out.println(i);
		
		//long类型的变量
		//long l = 10000;
		//System.out.println(l);
		long l = 10000000000L;
		System.out.println(l);
		
		//float类型的变量
		float f = 12.34F;
		System.out.println(f);
		
		//double类型的变量
		double d = 12.34;
		System.out.println(d);
		
		//char类型的变量
		char ch = 'a';
		System.out.println(ch);
		
		//boolean类型的变量
		boolean bb = true;
		System.out.println(bb);
	}
}
