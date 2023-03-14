package myOperator;

/*
 * 逻辑运算符：
 * &&:有false则false
 * ||:有true则true
 * !:true则false,false则true
 */
public class demo6 {
	public static void main(String[] args) {
		//定义变量
		int a = 3;
		int b = 4;
		int c = 5;
		
		//&&逻辑与
		System.out.println((a>b) && (a>c)); //false && false
		System.out.println((a<b) && (a>c)); //true && false
		System.out.println((a>b) && (a<c)); //false && true
		System.out.println((a<b) && (a<c)); //true && true
		System.out.println("------------");
		
		//||逻辑或
		System.out.println((a>b) || (a>c)); //false || false
		System.out.println((a<b) || (a>c)); //true || false
		System.out.println((a>b) || (a<c)); //false || true
		System.out.println((a<b) || (a<c)); //true || true
		System.out.println("------------");
		
		//!逻辑非
		System.out.println((a>b));
		System.out.println(!(a>b));
		System.out.println(!!(a>b));
	}
}

