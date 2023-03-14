package homework;

public class School {

	private String name;
	private int age;
	
	
	public void eat(){
		System.out.println("我是"+getName()+"我在吃饭！");
	}
	public String get(){
		return getName();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
