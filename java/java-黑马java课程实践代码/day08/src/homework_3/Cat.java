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
		System.out.println(super.getAge()+"���"+super.getColor()+"��è�����۾�����ͷ��"+something);
	}
	public void catchMouse(){
		System.out.println("è��ץ����");
	}
}
