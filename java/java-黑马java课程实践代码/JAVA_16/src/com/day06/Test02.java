package com.day06;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
�ڶ��⣺ʹ�ö��߳̽����������
	��һ������������Ϊ100�����ֱ��ʵ���͹�������������
	Ҫ��ʹ�ö��̵߳ķ�ʽ���ֱ��ӡʵ���͹���������������Ϣ��
	�ֱ�ͳ�ƹ�����ʵ���������˶��ٸ����������磺
	������������45��
	ʵ��깲������55��
 * @author Administrator
 *
 */
public class Test02 {

	
	//public static Object obj = new Object();  sync   lock
	public static void main(String[] args) {
		final int num = 100;
		
		Lock lock = new ReentrantLock();
		int count = 0;
		guanwang gw = new guanwang(count);
		System.out.println(gw.count);
		sTd std = new sTd(count);
		
		
		
		while (count < 100) {
			gw.start();
			std.start();
			count = gw.count + std.count;
		}
		System.out.println(std.count);
	}
	
	
}
class guanwang extends Thread{
	int count;
	public guanwang(int count) {
		// TODO Auto-generated constructor stub
		this.count = count;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (count < 100) {
			
			try {
				count ++;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
class sTd extends Thread{
	int count;
	public sTd(int count) {
		// TODO Auto-generated constructor stub
		this.count = count;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			count ++;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
