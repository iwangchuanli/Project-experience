package myOperator;

/*
 * 关系运算符：
 * 		==,!=,>,>=,<,<=
 * 		关系运算符操作完毕的结果是boolean类型。
 * 
 * 注意事项：
 * 		千万不要把==写成了=
 */
public class demo5 {
	public static void main(String[] args) {
		//定义三个变量
		int a = 10;
		int b = 20;
		int c = 10;
	
		//==
		System.out.println(a == b);
		System.out.println(a == c);
		System.out.println("------------");
		
		//!=
		System.out.println(a != b);
		System.out.println(a != c);
		System.out.println("------------");
		
		//>
		System.out.println(a > b);
		System.out.println(a > c);
		System.out.println("------------");
		
		//>=
		System.out.println(a >= b);
		System.out.println(a >= c);
		System.out.println("------------");
		
		System.out.println(a == b);
		System.out.println(a = b);//20,把b的值赋值给了a，并把a作为结果输出了
	}
}
