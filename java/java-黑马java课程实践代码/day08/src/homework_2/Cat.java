package homework_2;

public class Cat extends Animals{

	public Cat(String color) {
		// TODO Auto-generated constructor stub
		super.setColor(color);
	}
	
	public void eat(){
		System.out.println(super.getColor()+"��è���㣡");
	}
	
	public void catchMouse(){
		System.out.println(super.getColor()+"��èץ����");
	}
}
