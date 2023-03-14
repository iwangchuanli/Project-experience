package homework;

public class Phone {

	private String brand;
	private double price;
	
	public Phone() {
		// TODO Auto-generated constructor stub
	}
	public Phone(String brand,double price) {
		// TODO Auto-generated constructor stub
		this.brand = brand;
		this.price = price;
	}
	private void setter(String brand,double price) {
		// TODO Auto-generated method stub
		this.brand = brand;
		this.price = price;

	}
	private void getter() {
		// TODO Auto-generated method stub

	}
	public void call(){
		System.out.println("正在使用价格为"+price+"元的"+brand+"品牌的手机打电话....");
	}
	public void sendMessage(){
		System.out.println("正在使用价格为"+price+"元的"+brand+"品牌的手机发短信....");
	}
	public void PlayGame(){
		System.out.println("正在使用价格为"+price+"元的"+brand+"品牌的手机玩游戏....");
	}
}
