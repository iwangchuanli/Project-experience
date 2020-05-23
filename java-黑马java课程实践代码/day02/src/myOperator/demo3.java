package myOperator;

/*
 * 自增自减运算符：++,--
 * 
 * 作用：就是自己+1或者-1
 * 
 * ++和--可以放在变量的前面，也可以放在变量的后面。
 * 单独使用一个变量的时候，放在变量的前面或者后面，效果一样。
 * 参与其他操作的时候：
 * 		++在变量的后面，先把变量做操作，然后变量再++
 * 		++在变量的前面，先变量++，然后在做操作
 */
public class demo3 {
	public static void main(String[] args) {
		//定义一个int类型的变量
		int a = 10;
		System.out.println("a:"+a);
		
		//a++;
		//a--;
		//++a;
		//--a;
		//System.out.println("a:"+a);
		
		//int b = a++;
		int b = ++a;
		System.out.println("a:"+a);
		System.out.println("b:"+b);
	}
}

