package com.demo.CPtest;

public class Test {
	public static void main(String[] args) {
		// 仓库对象
		StorageImpl1 storage = new StorageImpl1();
		for(int i = 0;i <= 3; i++) {
			Producer prodecer = new Producer(storage, 10);
			Consumer consumer = new Consumer(storage, 10);
			prodecer.setName("生产者"+i);
			consumer.setName("消费者"+i);
			prodecer.start();
			consumer.start();
		}
		
		// 生产者对象
		Producer p1 = new Producer(storage, 10);
		Producer p2 = new Producer(storage, 20);
		Producer p3 = new Producer(storage, 10);
		Producer p4 = new Producer(storage, 10);
		Producer p5 = new Producer(storage, 20);

		// 消费者对象
		Consumer c1 = new Consumer(storage, 10);
		Consumer c2 = new Consumer(storage, 10);
		Consumer c3 = new Consumer(storage, 50);

		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();

		c1.start();
		c2.start();
		c3.start();
	}
}
