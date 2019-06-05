package com.demo.CPtest;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Synchronized实现Storage 使用ReentrantLock实现Storage
 * 
 * @author Administrator
 *
 */
public class StorageImpl2 implements Storage {

	// 仓库最大储存量
	private final int MAX_SIZE = 100;

	// 仓库存储的载体
	private LinkedList<Object> list = new LinkedList<Object>();

	// 锁
	private final Lock lock = new ReentrantLock();

	// 仓库清的条件变量
	private final Condition full = lock.newCondition();

	// 仓库空的条件变量
	private final Condition empty = lock.newCondition();

	/**
	 * 生产商品
	 * 
	 * @param num
	 */
	public void product(int num) {

		// 获得锁
		lock.lock();

		// 仓库容量不足
		while (list.size() + num > MAX_SIZE) {
			System.out.println("<爆仓> 仓库库存：" + list.size());
			try {
				// 条件不满足，产生阻塞
				full.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		// 生产条件满足情况下，生产num个产品
		for (int i = 1; i <= num; i++) {
			list.add(new Object());
		}
		System.out.println("[生产商品]:" + num + ";[目前库存]:" + list.size());

		// 唤醒其他所有线程
		full.signalAll();
		empty.signalAll();

		// 释放锁
		lock.unlock();
	}

	/**
	 * 消费商品
	 * 
	 * @param num
	 */
	public void consume(int num) {
		lock.lock();
		// 库存不足
		while (list.size() < num) {
			System.out.println("<库存不足> 当前库存：" + list.size());
			try {
				// 条件不满足，产生阻塞
				empty.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 消费条件满足情况下，消费num个产品
		for (int i = 1; i <= num; i++) {
			list.remove();
		}
		System.out.println("[消费商品]:" + num + ";[目前库存]：" + list.size());

		// 唤醒其他所有线程
		full.signalAll();
		empty.signalAll();
		// 释放锁
		lock.unlock();
	}

}
