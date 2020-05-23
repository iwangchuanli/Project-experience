package homework_2;

public class Animals {

	private String color;
	public Animals() {
		// TODO Auto-generated constructor stub
	}
	public Animals(String color) {
		// TODO Auto-generated constructor stub
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void eat(){
		System.out.println("动物会吃东西");
	}
}
