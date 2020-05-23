package com.day07.demo;

public class demo {

	public static void main(String[] args) {
		baozi bz = new baozi();
		
		ciHuo ch = new ciHuo("³Ô»õ", bz);
		BaoZiPu bzp = new BaoZiPu("°ü×ÓÆÌ", bz);
		
		ch.start();
		bzp.start();
	}
}
