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
		System.out.println(super.getAge()+"���"+super.getColor()+"�Ĺ���ֻǰ�������ı�ס"+something+"�ͳ�");
	}
	public void lookHome(){
		System.out.println("���ῴ�ң�");
	}
}
