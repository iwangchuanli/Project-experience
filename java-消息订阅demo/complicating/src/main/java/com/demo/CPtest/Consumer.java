package com.demo.CPtest;

/**
 * 
 * 消费者类
 * 
 * @author Administrator
 *
 */
public class Consumer extends Thread {

	// 每次消费的产品数量
	private int num;

	// 仓库
	private Storage storage;

	public Consumer(Storage storage, int num) {
		this.num = num;
		this.storage = storage;
	}

	/**
	 * run方法进行商品消费
	 */
	@Override
	public void run() {
		storage.consume(num);
	}
}
