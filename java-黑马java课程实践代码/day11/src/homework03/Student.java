package homework03;

public class Student {

	private String name;
	private int age;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	public Student(String name,int age) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void show(){
		System.out.println("ĞÕÃû£º"+name+"£¬ÄêÁä"+age);
	}
}
