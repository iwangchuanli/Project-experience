package com.day05;
/**
 * ������ʹ�ü̳�Thread��ķ�ʽ���������߳�
		(1)��һ���̵߳���������Ϊ��a   �ڶ����̵߳���������Ϊ��b
		(2)��һ���߳�����ʵ�ּ���1+2+3+4+....+100�ĺ�
		(3)�ڶ����߳�����ʵ�ּ���1+2+3+4+....+200�ĺ�
		�������մ�ӡ�����
				a:5050
				b:20100   (a��b�Ĵ�ӡ˳����Ҫ��)
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