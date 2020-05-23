package homework_3;

public class Cat extends Animals{

	public Cat() {
		// TODO Auto-generated constructor stub
	}
	public Cat(int age,String color,String something) {
		// TODO Auto-generated constructor stub
		super.setAge(age);
		super.setColor(color);
		super.setSomething(something);
	}
	public void eat(String something){
		System.out.println(super.getAge()+"岁的"+super.getColor()+"的猫眯着眼睛侧着头吃"+something);
	}
	public void catchMouse(){
		System.out.println("猫会抓老鼠！");
	}
}
