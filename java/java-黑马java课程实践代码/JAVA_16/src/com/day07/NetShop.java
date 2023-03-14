package com.day07;

/**
 * .定义一个官网线程类：NetShop，实现Runnable接口 完成卖出水果的动作
 * 
 * @author Administrator
 *
 */
public class NetShop extends Thread implements Runnable {

	private Fruit fruit;

	public NetShop(String name, Fruit fruit) {
		// TODO Auto-generated constructor stub
		super(name);
		this.fruit = fruit;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			synchronized (fruit) {
				if (fruit.flag == false) {
					try {
						fruit.wait();
					} catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					// fruit.setStock(fruit.getStock() - 1);
					System.out.println("官网正在卖出第" + (100 - fruit.stock) + "份，还剩余" + fruit.stock + "份");
					fruit.flag = false;
					fruit.notify();
				}

			}
		}
	}

}
