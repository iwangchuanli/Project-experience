package homework_1;

public class Phone {

	private String brand;
	public Phone() {
		// TODO Auto-generated constructor stub
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void call(String name){
		System.out.println("��"+brand+"��"+name+"��绰��");
	}
	public void sendMessage(String name){
		System.out.println("��"+brand+"��"+name+"�����ţ�");
	}
}
