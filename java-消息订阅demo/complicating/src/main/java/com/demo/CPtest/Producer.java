package com.demo.CPtest;

/**
 * 生产者类
 * 
 * @author Administrator
 *
 */
public class Producer extends Thread {

	// 每次生产的数量
	private int num;

	// 仓库
	private Storage storage;

	public Producer(Storage storage, int num) {
		this.num = num;
		this.storage = storage;
	}

	/**
	 * run方法进行生产
	 */
	@Override
	public void run() {
		storage.product(num);
	}
}