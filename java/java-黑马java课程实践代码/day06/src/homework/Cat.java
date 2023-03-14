package homework;

public class Cat {

	private String color;
	private String breed;
	
	public Cat() {
		// TODO Auto-generated constructor stub
	}
	public Cat(String color,String breed) {
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
		System.out.println(color+"的"+breed+"正在吃鱼.....");
	}
	public void catchMouse(){
		System.out.println(color+"的"+breed+"正在吃鱼.....");
	}
}
