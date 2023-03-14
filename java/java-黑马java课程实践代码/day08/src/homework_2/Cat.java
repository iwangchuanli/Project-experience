package homework_2;

public class Cat extends Animals{

	public Cat(String color) {
		// TODO Auto-generated constructor stub
		super.setColor(color);
	}
	
	public void eat(){
		System.out.println(super.getColor()+"的猫吃鱼！");
	}
	
	public void catchMouse(){
		System.out.println(super.getColor()+"的猫抓老鼠！");
	}
}
