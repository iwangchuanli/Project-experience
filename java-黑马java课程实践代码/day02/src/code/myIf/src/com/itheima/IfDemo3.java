package code.myIf.src.com.itheima;
/*
 * 格式3：
 * 		if(关系表达式1) {
 * 			语句体1;
 * 		}else if(关系表达式2) {
 * 			语句体2;
 * 		}
 * 		...
 * 		else {
 * 			语句体n+1;
 * 		}
 * 
 * 执行流程：
 * 		A:计算关系表达式1的值，看是true还是false
 * 		B:如果是true，就执行语句体1
 * 		C:如果是false，就继续计算关系表达式2的值，看是true还是false		
 * 		D:如果是true，就执行语句体2
 * 		E:如果是false，就继续计算...
 * 		F:所有的关系表达式的结果都是false，执行语句体n+1
 */
public class IfDemo3 {
	public static void main(String[] args) {
		System.out.println("开始");
		
		//假如x和y满足如下的关系：
		//x>=3	y=2x+1
		//-1<=x<3	y=2x
		//x<-1	y=2x-1
		
		//根据给定的x的值，计算出y的值
		int x = 5;
		x = 0;
		x = -5;
		
		//定义变量y
		int y;
		
		if(x >= 3) {
			y = 2*x+1;
		}else if(x>=-1 && x<3) {
			y = 2*x;
		}else if(x<-1) {
			y = 2*x-1;
		}else {
			y = 0;
			System.out.println("不存在这样的x的值");
		}
		
		System.out.println("y:"+y);
		
		System.out.println("结束");
	}
}
