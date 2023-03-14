package com.demo.CPtest;

import java.util.LinkedList;

/**
 * 使用Synchronized实现Storage
 * 
 * @author Administrator
 *
 */
public class StorageImpl1 implements Storage {

	// 仓库最大储存量
	private final int MAX_SIZE = 100;

	// 仓库存储的载体
	private LinkedList<Object> list = new LinkedList<Object>();

	/**
	 * 生产商品
	 * 
	 * @param num
	 */
	public void product(int num) {
		synchronized (list) {
			// 仓库容量不足
			while (list.size() + num > MAX_SIZE) {
				System.out.println(Thread.currentThread().getName()+"<爆仓> 仓库库存：" + list.size());
				try {
					list.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			// 生产条件满足情况下，生产num个产品
			for (int i = 1; i <= num; i++) {
				list.add(new Object());
			}
			System.out.println(Thread.currentThread().getName()+"[生产商品]:" + num + ";[目前库存]:" + list.size());
			list.notifyAll();
		}
	}

	/**
	 * 消费商品
	 * 
	 * @param num
	 */
	public void consume(int num) {
		synchronized (list) {
			// 库存不足
			while (list.size() < num) {
				System.out.println(Thread.currentThread().getName()+"<库存不足> 当前库存：" + list.size());
				try {
					list.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// 消费条件满足情况下，消费num个产品
			for (int i = 1; i <= num; i++) {
				list.remove();
			}
			System.out.println(Thread.currentThread().getName()+"[消费商品]:" + num + ";[目前库存]：" + list.size());
			list.notifyAll();
		}

	}

}
