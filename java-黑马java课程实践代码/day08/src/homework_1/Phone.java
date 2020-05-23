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
		System.out.println("用"+brand+"给"+name+"打电话！");
	}
	public void sendMessage(String name){
		System.out.println("用"+brand+"给"+name+"发短信！");
	}
}
