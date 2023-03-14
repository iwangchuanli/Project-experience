package com.day07.demo;

public class BaoZiPu extends Thread{

	private baozi bz;

	public BaoZiPu(String name,baozi bz) {
		super(name);
		this.bz = bz;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int count =0;
		while (true) {//造包子
			synchronized (bz) {//同步
				if (bz.flag == true) {//存在包子
					try {
						bz.wait();
					} catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				//没有包子  开始造
				System.out.println("包子铺开始做包子！");
				if (count % 2 == 0) {
					bz.pier = "冰皮";
					bz.xianer = "五仁";
				}else{
					bz.pier = "薄皮";
					bz.xianer = "牛肉大葱";
				}
				count++;
				
				bz.flag = true;
				System.out.println("包子造好了"+bz.pier+bz.xianer);
				System.out.println("吃货来吃吧");
				bz.notify();
			}
		}
	}
}
