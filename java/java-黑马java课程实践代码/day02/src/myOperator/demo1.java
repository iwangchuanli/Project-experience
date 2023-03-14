package myOperator;

/*
 * 常用的运算符：
 * 		算术运算符
 * 		自增自减运算符
 * 		赋值运算符
 * 		关系运算符
 * 		逻辑运算符
 * 		三元运算符
 * 
 * 算术运算符：
 * 		+,-,*,/,%
 * 
 * 		/和%的区别
 * 			/：获取两个数据相除的商
 * 			%：获取两个数据相除的余数
 * 
 * 注意：
 * 		整数相除只能得到整数。要想得到小数，就必须有浮点数参与运算。
 */
public class demo1 {
	public static void main(String[] args) {
		//定义两个int类型的变量
		int a = 5;
		int b = 3;
		
		System.out.println(a+b);
		System.out.println(a-b);
		System.out.println(a*b);
		System.out.println(a/b);
		System.out.println(a%b);
		System.out.println("------------");
		
		System.out.println(5/4);
		System.out.println(5.0/4);
		System.out.println(5/4.0);
	}
}
