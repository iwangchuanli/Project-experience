package myOperator;

/*
 * 赋值运算符：
 * 		基本的赋值运算符：=
 * 		扩展的赋值运算符：+=,-=,...
 */
public class demo4 {
	public static void main(String[] args) {
		//定义变量
		int a = 10; //把10赋值给int类型的变量a
		System.out.println("a:"+a);
		
		//扩展的赋值运算符：+=
		//把运算符左边的数据和右边的数据进行运算，然后把结果赋值给左边
		//a = a + 20;
		a += 20;
		System.out.println("a:"+a);
		
		//short s = 1;
		//s = s + 1;
		
		//扩展的赋值运算符隐含了强制类型转换。
		//a+=20
		//等价于
		//a =(a的数据类型)(a+20);
		short s = 1;
		s += 1;
		System.out.println("s:"+s);
	}
}

