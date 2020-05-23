package homework_3;

public class Dog extends Animals{

	public Dog() {
		// TODO Auto-generated constructor stub
	}
	public Dog(int age,String color,String something) {
		// TODO Auto-generated constructor stub
		super.setAge(age);
		super.setColor(color);
		super.setSomething(something);
	}
	public void eat(String something){
		System.out.println(super.getAge()+"岁的"+super.getColor()+"的狗两只前腿死死的抱住"+something+"猛吃");
	}
	public void lookHome(){
		System.out.println("狗会看家！");
	}
}
