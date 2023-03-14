package com.day02;
/**
 * 
 * @author Administrator
 *第一题：练习今日的代码

第二题：分析以下需求并实现
	1.以下代码是否会有问题？为什么？ 
	正确，int类型是4个字节，举个例子，小壶的水可以倒进大的壶里，小壶的水倒入大壶内
		public class Test {
			public static void main(String[] args) {
				int i1 = 5;
				byte b1 = 4;
				int sum = i1+ b1;
				System.out.println(sum);
			}
		}

第三题：分析以下需求并实现
	1.以下代码是否会有问题？为什么？
	错误，精度错误，可能导致损失精度，byte类型1个字节，
	int类型4个类型
		public class Test {
			public static void main(String[] args) {
				int i1 = 5;
				byte b1 = 4;
				byte sum = i1+ b1;//此处错误
				System.out.println(sum);
			}
		}

		byte,short,char--int--long--float--double
		无boolean型
		
		
第四题：分析以下需求并实现
	1.以下代码是否会有问题？为什么？
	错误，精度错误，可能导致损失精度，5是int类型
		public class Test {
			public static void main(String[] args) {
				byte b1 = 5;
				byte sum = b1 + 5;
				System.out.println(sum);
			}
		}

第五题：分析以下需求并实现
	1.查看以下代码，并写出结果
	false
	6 //进行了一次++运算，左边已经是false右边不运行,属于短路
	(i1++ > 5)//false ,后++，先运行后++
	(++i1 > 4)//true，先++，先++后运行
		public class Test {
			public static void main(String[] args) {
				int i1 = 5;
				boolean result = (i1++ > 5) && (++i1 > 4);
				System.out.println(result);
				System.out.println(i1);
			}
		}

第六题：分析以下需求并实现
	1.查看以下代码，并写出结果
	true
	7 //进行了两次++运算，左边已经是false右边需要判断右边是否正确
	(i1++ > 5)//false ,后++，先运行后++  5>5
	(++i1 > 4)//true，先++，先++后运行  6>4	

		public class Test {
			public static void main(String[] args) {
				int i1 = 5;
				boolean result = (i1++ > 5) || (++i1 > 4);
				System.out.println(result);
				System.out.println(i1);
			}
		}

第七题：分析以下需求并实现
	1.请使用三元运算符计算出两个整数中的最大值。
		例如：20 40     打印结果：40是最大值
		
public class Test {
			public static void main(String[] args) {
				boolean i = (20 > 40 ? true : false);
				//System.out.println(result);
				System.out.println(i);
			}
		}
第八题：分析以下需求并实现
	1.请使用三元运算符计算出三个整数中的最大值。
		例如：20 40 30     打印结果：40是最大值			
		public class Test {
			public static void main(String[] args) {
				int i = (20 > 40)&&( 20 > 30) ? 20 : (40 > 30 ? 40 :30);
				//System.out.println(result);
				System.out.println(i + "是最大值");
			}
		}
	
第九题：分析以下需求并实现
	1.定义一个方法，方法名为：show()
	2.在show()方法中输出以下几句话：
		我爱黑马
		我爱java
		我要好好学习
	3.在主方法中调用show()方法并运行，输出内容
	public class Test {
			public static void main(String[] args) {
				show();
			}
			public static void show (){
				System.out.print("我爱黑马"+"\n"+"我爱java"+"\n"+"我要好好学习");
			}
			
		}
第十题：分析以下需求并实现
	1.定义一个方法，方法名为：show()
	2.在show()方法中输出以下几句话：
		我爱黑马
		我爱java
		我要好好学习
	3.再定义一个方法，方法名为：print()
	4.在print()方法中调用show()方法
	5.在主方法中调用print()方法并运行，输出内容

	public class Test {
			public static void main(String[] args) {
				System.out.print("\n"+"print方法执行");
				print();
				
			}
			public static void show (){
				System.out.print("我爱黑马"+"\n"+"我爱java"+"\n"+"我要好好学习");
			}
			public static void print(){
				System.out.print("\n"+"show方法执行");
				show();
				
			}
			
		}
 */
public class Test {

}
