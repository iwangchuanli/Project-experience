package homework;

public class Dog {

	private String color;
	private String breed;
	
	public Dog() {
		// TODO Auto-generated constructor stub
	}
	public Dog(String color,String breed) {
		// TODO Auto-generated constructor stub
		this.color = color;
		this.breed = breed;
	}
	private void setter(String color,String breed) {
		// TODO Auto-generated method stub
		this.color = color;
		this.breed = breed;
	}
	private void getter() {
		// TODO Auto-generated method stub
	}
	
	public void eat(){
		System.out.println(color+"的"+breed+"正在啃骨头.....");
	}
	public void lookHome(){
		System.out.println(color+"的"+breed+"正在看家.....");
	}
}
