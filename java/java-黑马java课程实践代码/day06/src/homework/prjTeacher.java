package homework;

public class prjTeacher {

	private String name;
	private int age;
	private String content;
	
	public prjTeacher() {
		// TODO Auto-generated constructor stub
	}
	public prjTeacher(String name,int age,String content) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.age = age;
		this.content = content;
	}
	
	public void eat(){
		System.out.println("年龄为"+age+"的"+name+"老师正在吃饭....");
	}
	
	public void teach(){
		System.out.println("年龄为"+age+"的"+name+"老师正在亢奋的讲着"+content+"的知识........");
	}
	
	private void setter(String name,int age,String content) {
		// TODO Auto-generated method stub
		this.name = name;
		this.age = age;
		this.content = content;
	}
	private void getter() {
		// TODO Auto-generated method stub

	}
}
