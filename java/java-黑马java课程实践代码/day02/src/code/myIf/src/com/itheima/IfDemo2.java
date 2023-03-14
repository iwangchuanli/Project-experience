package code.myIf.src.com.itheima;
/*
 * 格式2：
 * 		if(关系表达式) {
 * 			语句体1;
 * 		}else {
 * 			语句体2;
 * 		}
 * 
 * 执行流程：
 * 		A:首先计算关系表达式的值，看结果是true还是false		
 * 		B:如果是true，就执行语句体1
 * 		C:如果是false，就执行语句体2
 */
public class IfDemo2 {
	public static void main(String[] args) {
		System.out.println("开始");
		
		//判断一个数据是奇数还是偶数
		//思路：如果一个数据对2取余的结果是0，说明该数是偶数
		//定义一个变量
		int a = 100;
		//重新给a赋值
		a = 99;
		
		if(a%2 == 0) {
			System.out.println("a是偶数");
		}else {
			System.out.println("a是奇数");
		}
		
		System.out.println("结束");
	}
}
