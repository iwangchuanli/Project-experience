package com.day07.demo;

public class demo {

	public static void main(String[] args) {
		baozi bz = new baozi();
		
		ciHuo ch = new ciHuo("�Ի�", bz);
		BaoZiPu bzp = new BaoZiPu("������", bz);
		
		ch.start();
		bzp.start();
	}
}
