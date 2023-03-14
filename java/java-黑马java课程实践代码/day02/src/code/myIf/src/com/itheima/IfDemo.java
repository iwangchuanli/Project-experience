package code.myIf.src.com.itheima;
/*
 * if语句有三种格式。
 * 
 * 格式1：
 * 		if(关系表达式) {
 * 			语句体;
 * 		}
 * 
 * 执行流程：
 * 		A:首先计算关系表达式的值，看是true还是false
 * 		B:如果是true，就执行语句体
 * 		C:如果是false，就不执行语句体
 */
public class IfDemo {
	public static void main(String[] args) {
		System.out.println("开始");
		
		//定义两个int类型的变量
		int a = 10;
		int b = 20;
		
		//判断两个变量是否相等
		if(a == b) {
			System.out.println("a等于b");
		}
		
		//定义变量
		int c = 10;
		if(a == c) {
			System.out.println("a等于c");
		}
		
		System.out.println("结束");
	}
}
