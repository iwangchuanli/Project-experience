package homework_3;

public class Animals {

	private int age;
	private String color;
	private String something;
	
	public Animals() {
		// TODO Auto-generated constructor stub
	}
	public Animals(int age,String color,String something) {
		// TODO Auto-generated constructor stub
		this.age = age;
		this.color = color;
		this.something = something;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	public String getSomething() {
		return something;
	}

	public void setSomething(String something) {
		this.something = something;
	}

	public void eat(String something){
		//System.out.println(age+"岁的"+color+"这只动物吃"+something);
	}
}
