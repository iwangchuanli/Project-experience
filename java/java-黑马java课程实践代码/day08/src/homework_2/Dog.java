package homework_2;

public class Dog extends Animals{

	public Dog(String color) {
		// TODO Auto-generated constructor stub
		super.setColor(color);
	}
	
	public void eat(){
		System.out.println(super.getColor()+"�Ĺ��й�ͷ��");
	}
	public void lookHome(){
		System.out.println(super.getColor()+"�Ĺ��ῴ�ң�");
	}
}
