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
		System.out.println("ѧ����ţ�"+stuId+"��ѧ��������"+name+"��ѧ���Ա�"+sex+"��ѧ����ߣ�"+height+"��ѧ�����䣺"+age);
	}
}
