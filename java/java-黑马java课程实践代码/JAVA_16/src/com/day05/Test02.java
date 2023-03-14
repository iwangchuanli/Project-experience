package com.day05;
/**
 * 二、请使用继承Thread类的方式开启两个线程
		(1)第一个线程的名字设置为：a   第二个线程的名字设置为：b
		(2)第一个线程里面实现计算1+2+3+4+....+100的和
		(3)第二个线程里面实现计算1+2+3+4+....+200的和
		程序最终打印结果：
				a:5050
				b:20100   (a和b的打印顺序不作要求)
 * @author Administrator
 *
 */
public class Test02 {
	public static void main(String[] args) {
		myThread1 a = new myThread1();
		myThread2 b = new myThread2();
		a.start();
		b.start();
	}	
}
class myThread1 extends Thread{
		public myThread1() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			int sum = 0;
			for (int i = 0; i < 100; i++) {
				sum += i;
			}
			System.out.println("a:"+" "+sum);
		}
}

class myThread2 extends Thread{
	public myThread2() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int sum = 0;
		for (int i = 0; i < 200; i++) {
			sum += i;
		}
		System.out.println("b:"+" "+sum);
	}
}