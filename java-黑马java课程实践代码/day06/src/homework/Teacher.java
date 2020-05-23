package homework;

public class Teacher {
	
	private String id;
	private String name;
	private String sex;
	private int age;
	private String course;
	
	public Teacher() {
		// TODO Auto-generated constructor stub
	}
	public Teacher(String id,String name,String sex,int age,String course){
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.course = course;
	}
	public void set(String id,String name,String sex,int age,String course){
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.course = course;
	}
	public String getId(){
		return id;
	}
	
}
