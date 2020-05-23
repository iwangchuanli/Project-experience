package homework;

public class Student {

	private String stuId;
	private String name;
	private String sex;
	private double height;
	private int age;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Student(String stuId,String name,String sex,double height,int age) {
		// TODO Auto-generated constructor stub
		this.stuId = stuId;
		this.name = name;
		this.sex = sex;
		this.height = height;
		this.age = age;
	}
	
	public void set(String stuId,String name,String sex,double height,int age){
		this.stuId = stuId;
		this.name = name;
		this.sex = sex;
		this.height = height;
		this.age = age;
	}
	
	public void printf(){
		System.out.println("学生编号："+stuId+"，学生姓名："+name+"，学生性别："+sex+"，学生身高："+height+"，学生年龄："+age);
	}
}
