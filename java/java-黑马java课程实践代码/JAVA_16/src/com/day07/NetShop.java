package com.day07;

/**
 * .����һ�������߳��ࣺNetShop��ʵ��Runnable�ӿ� �������ˮ���Ķ���
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
					System.out.println("��������������" + (100 - fruit.stock) + "�ݣ���ʣ��" + fruit.stock + "��");
					fruit.flag = false;
					fruit.notify();
				}

			}
		}
	}

}
