package homework;

public class schoolTeacher extends School{

	public schoolTeacher(String name,int age) {
		// TODO Auto-generated constructor stub
		//this.name = name;
		super.setName(name);
		age = age;
	}
	public void teaher(){
		System.out.println("老师能上课！");
	}
	
}
