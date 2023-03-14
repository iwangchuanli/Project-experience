package com.day06;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
第二题：使用多线程解决以下问题
	有一个包包的数量为100个。分别从实体店和官网进行售卖！
	要求使用多线程的方式，分别打印实体店和官网卖出包包的信息！
	分别统计官网和实体店各卖出了多少个包包，例如：
	官网共卖出了45个
	实体店共卖出了55个
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
