package com.day06;

public class Test02_1 {
	public static Object obj = new Object();
	public static void main(String[] args) {
		
		int count1 = 0,count2 = 0;
		
		new Thread(){
			public void run() {
				while (true) {
					synchronized (obj) {
						System.out.println("��ʼ1�߳�");
						try {
							obj.wait();
						} catch (InterruptedException e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						//����֮��
						System.out.println("1�߳�����");
						//count1 ++;
					}
				}
			}
		}.start();
		
		
		new Thread(){
			public void run() {
				while (true) {
					try {
						System.out.println("2�߳����к�����3��");
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO: handle exception
					}
					synchronized (obj) {
						System.out.println("����1����");
						obj.notify();
					}
				}
			}
		}.start();
		count2 ++;
		
		System.out.println(count1+"  "+count2);
	}
}
