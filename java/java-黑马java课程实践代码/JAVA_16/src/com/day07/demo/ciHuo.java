package com.day07.demo;
/**
 *
 * @author Administrator
 *
 */
public class ciHuo extends Thread{
	private baozi bz;

	public ciHuo(String name,baozi bz) {
		super(name);
		this.bz = bz;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			synchronized (bz) {
				if (bz.flag == false) {//没有包子
					try {
						bz.wait();
					} catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					System.out.println("吃货在吃"+bz.pier+bz.xianer+"包子");
					bz.flag = false;
					bz.notify();
				}
			}
		}
	}
	
}
