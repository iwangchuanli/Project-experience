package com.day07;
/**
 * 1.����һ��Fruitˮ����
		��Ա������stock���
		���췽����set��get
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
