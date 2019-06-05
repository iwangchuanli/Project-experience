package com.demo.CPtest;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 使用Synchronized实现Storage
 * 
 * @author Administrator
 *
 */
public class StorageImpl3 implements Storage {

	// 仓库最大储存量
	private final int MAX_SIZE = 100;

	// 仓库存储的载体
	private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<Object>(MAX_SIZE);

	/**
	 * 生产商品
	 * 
	 * @param num
	 */
	public void product(int num) {

		// 仓库容量不足
		if (list.size() == MAX_SIZE)
			System.out.println("<爆仓> 仓库库存：" + list.size());

		// 生产条件满足情况下，生产num个产品
		for (int i = 1; i <= num; i++) {
			try {
				list.put(new Object());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("[生产商品]:" + num + ";[目前库存]:" + list.size());
		}
	}

	/**
	 * 消费商品
	 * 
	 * @param num
	 */
	public void consume(int num) {

		// 库存不足
		if (list.size() == 0) {
			System.out.println("<库存不足> 当前库存：" + list.size());
		}

		// 消费条件满足情况下，消费num个产品
		for (int i = 1; i <= num; i++) {
			try {
				list.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("[消费商品]:" + num + ";[目前库存]：" + list.size());
		}

	}
}
