package code.myVariable.src.com.itheima;
/*
 * 变量的注意事项：
 * 		A:变量未赋值，不能直接使用
 * 		B:变量只在它所属的范围内有效
 * 			变量属于它所在的那对大括号
 * 		C:一行上可以定义多个变量，但是不建议
 */
public class VariableDemo2 {
	public static void main(String[] args) {
		//定义一个变量
		int a = 10;
		System.out.println(a);
		
		int b;
		b = 20;
		System.out.println(b);
		
		{
			//代码块
			int c = 30;
			System.out.println(c);
		}
		//System.out.println(c);
		System.out.println(b);
		
		
		/*
		int aa,bb;
		aa = 10;
		bb = 20;
		System.out.println(aa);
		System.out.println(bb);
		*/
		int aa = 10;
		int bb = 20;
		System.out.println(aa);
		System.out.println(bb);
	}
}
