package myOperator;

/*
 * 字符参与加法运算：其实是拿该字符在计算机中存储所表示的数据值来运算的。
 * 		'a'	97
 * 		'A'	65
 * 		'0'	48	
 * 
 * 字符串参与加法运算：其实这里不是加法，而是字符串的拼接。
 */
public class demo2 {
	public static void main(String[] args) {
		//定义两个变量，一个int类型，一个char类型
		int a = 10;
		char ch = 'a';
		System.out.println(a + ch);
		System.out.println("----------------");
		
		//字符串做加法
		System.out.println("hello"+"world");
		System.out.println("hello"+10);
		System.out.println("hello"+10+20);
		System.out.println(10+20+"hello");
	}
}

