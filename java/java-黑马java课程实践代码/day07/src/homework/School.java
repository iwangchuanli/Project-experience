package homework;

public class School {

	private String name;
	private int age;
	
	
	public void eat(){
		System.out.println("����"+getName()+"���ڳԷ���");
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
