package com.day07;

/**
 * ����һ��ʵ����߳��ࣺFrontShop��ʵ��Runnable�ӿ� �������ˮ���Ķ���
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
					System.out.println("ʵ�������������" + (100 - fruit.stock) + "�ݣ���ʣ��" + fruit.stock + "��");
					fruit.flag = true;
					fruit.notify();
				}

			}
		}
	}
}
