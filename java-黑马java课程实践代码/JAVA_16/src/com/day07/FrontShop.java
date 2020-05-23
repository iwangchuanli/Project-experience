package com.day07;

/**
 * 定义一个实体店线程类：FrontShop，实现Runnable接口 完成卖出水果的动作
 * 
 * @author Administrator
 *
 */
public class FrontShop extends Thread implements Runnable {

	private Fruit fruit;

	public FrontShop(String name, Fruit fruit) {
		super(name);
		this.fruit = fruit;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			synchronized (fruit) {
				if (fruit.flag == true) {
					try {
						fruit.wait();
					} catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					// fruit.setStock(fruit.getStock() - 1);
					System.out.println("实体店正在卖出第" + (100 - fruit.stock) + "份，还剩余" + fruit.stock + "份");
					fruit.flag = true;
					fruit.notify();
				}

			}
		}
	}
}
