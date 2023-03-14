package com.day07;
/**
 * 1.定义一个Fruit水果类
		成员变量：stock库存
		构造方法、set和get
 * @author Administrator
 *
 */
public class Fruit {

	int stock ;
	boolean flag = false;
	public Fruit(int stock) {
		this.stock = stock;
	}
	public Fruit(){
		
	}
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
