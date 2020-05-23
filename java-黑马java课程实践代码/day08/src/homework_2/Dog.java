package homework_2;

public class Dog extends Animals{

	public Dog(String color) {
		// TODO Auto-generated constructor stub
		super.setColor(color);
	}
	
	public void eat(){
		System.out.println(super.getColor()+"的狗啃骨头！");
	}
	public void lookHome(){
		System.out.println(super.getColor()+"的狗会看家！");
	}
}
