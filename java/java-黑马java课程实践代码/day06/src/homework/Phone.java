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
		System.out.println("����ʹ�ü۸�Ϊ"+price+"Ԫ��"+brand+"Ʒ�Ƶ��ֻ���绰....");
	}
	public void sendMessage(){
		System.out.println("����ʹ�ü۸�Ϊ"+price+"Ԫ��"+brand+"Ʒ�Ƶ��ֻ�������....");
	}
	public void PlayGame(){
		System.out.println("����ʹ�ü۸�Ϊ"+price+"Ԫ��"+brand+"Ʒ�Ƶ��ֻ�����Ϸ....");
	}
}
