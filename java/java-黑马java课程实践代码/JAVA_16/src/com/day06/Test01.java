package com.day06;
/**
 * 第一题：分析以下需求，并用代码实现
	一共有1000张电影票,可以在两个窗口领取,假设每次领取的时间为3000毫秒,
	要求:请用多线程模拟卖票过程并打印剩余电影票的数量
 * @author Administrator
 *
 */
public class Test01 {

	public static void main(String[] args) {
		sellTicket st = new sellTicket("变形金刚", 1000);
		st.start();
	}
}
class sellTicket extends Thread{
	private int count;
	public sellTicket(String name,int count) {
		// TODO Auto-generated constructor stub
		super(name);
		this.count = count;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (count > 0) {
			try {
				sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			count --;
			System.out.println(super.getName()+"卖出去1张，还剩余"+count+"张。");
		}
	}
}
